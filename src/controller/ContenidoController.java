package controller;

import java.util.List;
import service.ContenidoService;
import uni1a.ContenidoAudiovisual;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Actor;
import view.ConsoleView;

public class ContenidoController {
    private ContenidoService servicio;
    private ConsoleView vista;

    public ContenidoController() {
        this.servicio = new ContenidoService();
        this.vista = new ConsoleView();
    }

    public void iniciar() {
        autoCargarDatos();
        while (vista.estaActivo()) {
            int opcion = vista.mostrarMenuPrincipal();
            procesarOpcionPrincipal(opcion);
        }
    }

    private void procesarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1 -> manejarContenido();
            case 2 -> manejarActores();
            case 3 -> manejarTemporadas();
            case 4 -> manejarBusqueda();
            case 5 -> mostrarEstadisticas();
            case 6 -> listarTodoContenido();
            case 7 -> guardarDatos();
            case 8 -> cargarDatos();
            case 0 -> salir();
            default -> vista.mostrarError("Opcion no valida");
        }
    }

    private void salir() {
        vista.cerrar();
        vista.mostrarExito("¡Hasta luego!");
    }

    private void autoCargarDatos() {
        String[] carpetasPosibles = {"data", "datosp"};
        for (String carpeta : carpetasPosibles) {
            java.io.File dir = new java.io.File(carpeta);
            java.io.File archivoContenidos = new java.io.File(dir, "contenidos.csv");
            if (archivoContenidos.exists() && servicio.cargarSistema(carpeta)) {
                vista.mostrarInfo("Se cargaron los datos automaticamente desde la carpeta: " + carpeta);
                vista.pausa();
                return;
            }
        }
    }

    private void manejarContenido() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuContenido();
            enMenu = procesarOpcionContenido(opcion);
        }
    }

    private boolean procesarOpcionContenido(int opcion) {
        switch (opcion) {
            case 1 -> crearPelicula();
            case 2 -> crearSerie();
            case 3 -> crearDocumental();
            case 4 -> crearVideoNeflix();
            case 5 -> crearVideoStreaming();
            case 6 -> actualizarContenido();
            case 7 -> eliminarContenido();
            case 0 -> {
                return false;
            }
            default -> vista.mostrarError("Opcion no valida");
        }
        return true;
    }

    private void crearPelicula() {
        String titulo = vista.leerCadena("Titulo de la pelicula: ");
        int duracion = vista.leerEntero("Duracion en minutos: ");
        String genero = vista.leerCadena("Genero: ");
        String estudio = vista.leerCadena("Estudio productor: ");
        servicio.crearPelicula(titulo, duracion, genero, estudio);
        mostrarExitoPausa("Pelicula creada exitosamente");
    }

    private void crearSerie() {
        String titulo = vista.leerCadena("Titulo de la serie: ");
        int duracion = vista.leerEntero("Duracion promedio por episodio (minutos): ");
        String genero = vista.leerCadena("Genero: ");
        int numTemporadas = vista.leerEntero("Numero de temporadas: ");
        servicio.crearSerie(titulo, duracion, genero, numTemporadas);
        mostrarExitoPausa("Serie creada exitosamente");
    }

    private void crearDocumental() {
        String titulo = vista.leerCadena("Titulo del documental: ");
        int duracion = vista.leerEntero("Duracion en minutos: ");
        String genero = vista.leerCadena("Genero: ");
        String tema = vista.leerCadena("Tema principal: ");
        servicio.crearDocumental(titulo, duracion, genero, tema);
        mostrarExitoPausa("Documental creado exitosamente");
    }

    private void crearVideoNeflix() {
        String titulo = vista.leerCadena("Titulo del video Netflix: ");
        int duracion = vista.leerEntero("Duracion en minutos: ");
        String genero = vista.leerCadena("Genero: ");
        boolean esOriginal = vista.leerBooleano("¿Es original de Netflix?");
        String resolucion = vista.leerCadena("Resolucion (4K, 1080p, 720p, etc.): ");
        servicio.crearVideoNeflix(titulo, duracion, genero, esOriginal, resolucion);
        mostrarExitoPausa("Video Netflix creado exitosamente");
    }

    private void crearVideoStreaming() {
        String titulo = vista.leerCadena("Titulo del video Streaming: ");
        int duracion = vista.leerEntero("Duracion en minutos: ");
        String genero = vista.leerCadena("Genero: ");
        String plataforma = vista.leerCadena("Plataforma (Amazon Prime, Disney+, etc.): ");
        int visualizaciones = vista.leerEntero("Numero de visualizaciones: ");
        servicio.crearVideoStriming(titulo, duracion, genero, plataforma, visualizaciones);
        mostrarExitoPausa("Video Streaming creado exitosamente");
    }

    private void mostrarExitoPausa(String mensaje) {
        vista.mostrarExito(mensaje);
        vista.pausa();
    }

    private void actualizarContenido() {
        listarTodoContenido();
        int id = vista.leerEntero("Ingresa el ID del contenido a actualizar: ");
        ContenidoAudiovisual contenido = servicio.obtenerContenidoPorId(id);

        if (contenido == null) {
            vista.mostrarError("Contenido no encontrado");
            return;
        }

        int opcion = mostrarMenuActualizacion();
        boolean exito = ejecutarActualizacion(id, opcion);

        if (exito) {
            vista.mostrarExito("Contenido actualizado exitosamente");
        } else {
            vista.mostrarError("No se pudo actualizar el contenido");
        }
        vista.pausa();
    }

    private int mostrarMenuActualizacion() {
        System.out.println("\n¿Qué deseas actualizar?");
        System.out.println("1. Título");
        System.out.println("2. Duración");
        System.out.println("3. Género");
        return vista.leerEntero("Selecciona: ");
    }

    private boolean ejecutarActualizacion(int id, int opcion) {
        return switch (opcion) {
            case 1 -> servicio.actualizarTitulo(id, vista.leerCadena("Nuevo titulo: "));
            case 2 -> servicio.actualizarDuracion(id, vista.leerEntero("Nueva duracion (minutos): "));
            case 3 -> servicio.actualizarGenero(id, vista.leerCadena("Nuevo genero: "));
            default -> false;
        };
    }

    private void eliminarContenido() {
        listarTodoContenido();
        int id = vista.leerEntero("Ingresa el ID del contenido a eliminar: ");
        if (servicio.eliminarContenido(id)) {
            vista.mostrarExito("Contenido eliminado exitosamente");
        } else {
            vista.mostrarError("Contenido no encontrado");
        }
        vista.pausa();
    }

    private void manejarActores() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuActores();
            enMenu = procesarOpcionActores(opcion);
        }
    }

    private boolean procesarOpcionActores(int opcion) {
        switch (opcion) {
            case 1 -> crearActor();
            case 2 -> listarActores();
            case 3 -> agregarActorAPelicula();
            case 0 -> {
                return false;
            }
            default -> vista.mostrarError("Opcion no valida");
        }
        return true;
    }

    private void crearActor() {
        String nombre = vista.leerCadena("Nombre del actor: ");
        String apellido = vista.leerCadena("Apellido del actor: ");
        servicio.crearActor(nombre, apellido);
        mostrarExitoPausa("Actor creado exitosamente");
    }

    private void listarActores() {
        List<Actor> actores = servicio.obtenerTodosLosActores();
        vista.mostrarActores(actores);
    }

    private void agregarActorAPelicula() {
        listarTodoContenido();
        int idPelicula = vista.leerEntero("Ingresa el ID de la pelicula: ");

        if (!esPelicula(idPelicula)) {
            vista.mostrarError("El contenido seleccionado no es una pelicula");
            vista.pausa();
            return;
        }

        listarActores();
        String nombre = vista.leerCadena("Nombre del actor a añadir: ");
        String apellido = vista.leerCadena("Apellido del actor: ");
        Actor actor = servicio.buscarActor(nombre, apellido);

        if (actor == null) {
            vista.mostrarAdvertencia("Actor no encontrado. Cralo primero");
            return;
        }

        if (servicio.agregarActorAPelicula(idPelicula, actor)) {
            vista.mostrarExito("Actor añadido a la pelicula exitosamente");
        } else {
            vista.mostrarError("No se pudo añadir el actor");
        }
        vista.pausa();
    }

    private boolean esPelicula(int id) {
        return servicio.obtenerContenidoPorId(id) instanceof Pelicula;
    }

    private void manejarTemporadas() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuTemporadas();
            enMenu = procesarOpcionTemporadas(opcion);
        }
    }

    private boolean procesarOpcionTemporadas(int opcion) {
        switch (opcion) {
            case 1 -> agregarTemporada();
            case 2 -> verTemporadas();
            case 0 -> {
                return false;
            }
            default -> vista.mostrarError("Opcion no valida");
        }
        return true;
    }

    private void agregarTemporada() {
        listarTodoContenido();
        int idSerie = vista.leerEntero("Ingresa el ID de la serie: ");
        SerieDeTV serie = servicio.obtenerSerie(idSerie);

        if (serie == null) {
            vista.mostrarError("La serie no existe o el contenido no es una serie");
            vista.pausa();
            return;
        }

        int numeroTemporada = vista.leerEntero("Numero de temporada: ");
        int episodios = vista.leerEntero("Numero de episodios: ");

        if (servicio.agregarTemporadaASerie(idSerie, numeroTemporada, episodios)) {
            vista.mostrarExito("Temporada añadida exitosamente");
        } else {
            vista.mostrarError("No se pudo añadir la temporada");
        }
        vista.pausa();
    }

    private void verTemporadas() {
        listarTodoContenido();
        int idSerie = vista.leerEntero("Ingresa el ID de la serie: ");
        SerieDeTV serie = servicio.obtenerSerie(idSerie);

        if (serie == null) {
            vista.mostrarError("La serie no existe o el contenido no es una serie");
            vista.pausa();
            return;
        }

        vista.mostrarDetallesContenido(serie);
    }

    private void manejarBusqueda() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuBusqueda();
            enMenu = procesarOpcionBusqueda(opcion);
        }
    }

    private boolean procesarOpcionBusqueda(int opcion) {
        switch (opcion) {
            case 1 -> buscarPorTitulo();
            case 2 -> buscarPorGenero();
            case 3 -> buscarPorTipo();
            case 4 -> obtenerPorId();
            case 0 -> {
                return false;
            }
            default -> vista.mostrarError("Opcion no valida");
        }
        return true;
    }

    private void buscarPorTitulo() {
        String titulo = vista.leerCadena("Ingresa el titulo a buscar: ");
        List<ContenidoAudiovisual> resultados = servicio.buscarPorTitulo(titulo);
        vista.mostrarContenidos(resultados);
    }

    private void buscarPorGenero() {
        String genero = vista.leerCadena("Ingresa el genero a buscar: ");
        List<ContenidoAudiovisual> resultados = servicio.buscarPorGenero(genero);
        vista.mostrarContenidos(resultados);
    }

    private void buscarPorTipo() {
        int opcion = mostrarMenuTipos();
        String tipo = seleccionarTipo(opcion);
        if (tipo == null) {
            vista.mostrarError("Tipo no va  lido");
            return;
        }
        List<ContenidoAudiovisual> resultados = servicio.buscarPorTipo(tipo);
        vista.mostrarContenidos(resultados);
    }

    private int mostrarMenuTipos() {
        System.out.println("\nTipos disponibles:");
        System.out.println("1. Pelicula");
        System.out.println("2. SerieDeTV");
        System.out.println("3. Documental");
        System.out.println("4. VideoNeflix");
        System.out.println("5. VideoStriming");
        return vista.leerEntero("Selecciona el tipo: ");
    }

    private String seleccionarTipo(int opcion) {
        return switch (opcion) {
            case 1 -> "Pelicula";
            case 2 -> "SerieDeTV";
            case 3 -> "Documental";
            case 4 -> "VideoNeflix";
            case 5 -> "VideoStriming";
            default -> null;
        };
    }

    private void obtenerPorId() {
        int id = vista.leerEntero("Ingresa el ID del contenido: ");
        ContenidoAudiovisual contenido = servicio.obtenerContenidoPorId(id);

        if (contenido != null) {
            vista.mostrarDetallesContenido(contenido);
        } else {
            vista.mostrarError("Contenido no encontrado");
            vista.pausa();
        }
    }

    private void listarTodoContenido() {
        List<ContenidoAudiovisual> contenidos = servicio.obtenerTodosLosContenidos();
        vista.mostrarContenidos(contenidos);
    }

    private void mostrarEstadisticas() {
        System.out.println(servicio.obtenerEstadisticas());
        vista.pausa();
    }

    private void guardarDatos() {
        String carpeta = vista.leerCadena("Ingresa el nombre de la carpeta para guardar los datos (ej: data): ");
        if (carpeta.isBlank()) {
            carpeta = "data";
        }
        if (servicio.guardarSistema(carpeta)) {
            vista.mostrarExito("Datos guardados exitosamente en la carpeta: " + carpeta);
        } else {
            vista.mostrarError("Error al guardar los datos en la carpeta: " + carpeta);
        }
        vista.pausa();
    }

    private void cargarDatos() {
        String carpeta = vista.leerCadena("Ingresa el nombre de la carpeta de donde cargar los datos (ej: data): ");
        if (carpeta.isBlank()) {
            carpeta = "data";
        }
        if (servicio.cargarSistema(carpeta)) {
            vista.mostrarExito("Datos cargados exitosamente desde la carpeta: " + carpeta);
        } else {
            vista.mostrarError("Error al cargar los datos desde la carpeta (puede que no exista o falten archivos): " + carpeta);
        }
        vista.pausa();
    }
}
