package controller;

import java.util.List;
import service.ContenidoService;
import uni1a.ContenidoAudiovisual;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Actor;
import view.ConsoleView;

/**
 * Controlador principal del sistema
 * Coordina las interacciones entre la Vista (View) y el Servicio (Service)
 * Implementa el patrón MVC
 */
public class ContenidoController {
    private ContenidoService servicio;
    private ConsoleView vista;

    public ContenidoController() {
        this.servicio = new ContenidoService();
        this.vista = new ConsoleView();
    }

    /**
     * Inicia el flujo principal de la aplicación
     */
    public void iniciar() {
        autoCargarDatos();
        while (vista.estaActivo()) {
            int opcion = vista.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    manejarContenido();
                    break;
                case 2:
                    manejarActores();
                    break;
                case 3:
                    manejarTemporadas();
                    break;
                case 4:
                    manejarBusqueda();
                    break;
                case 5:
                    mostrarEstadisticas();
                    break;
                case 6:
                    listarTodoContenido();
                    break;
                case 7:
                    guardarDatos();
                    break;
                case 8:
                    cargarDatos();
                    break;
                case 0:
                    vista.cerrar();
                    vista.mostrarExito("¡Hasta luego!");
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        }
    }

    private void autoCargarDatos() {
        String[] carpetasPosibles = {"data", "datosp"};
        for (String carpeta : carpetasPosibles) {
            java.io.File dir = new java.io.File(carpeta);
            java.io.File archivoContenidos = new java.io.File(dir, "contenidos.csv");
            if (archivoContenidos.exists()) {
                if (servicio.cargarSistema(carpeta)) {
                    vista.mostrarInfo("Se cargaron los datos automáticamente desde la carpeta: " + carpeta);
                    vista.pausa();
                    return;
                }
            }
        }
    }

    // ==================== GESTIÓN DE CONTENIDO ====================

    private void manejarContenido() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuContenido();

            switch (opcion) {
                case 1:
                    crearPelicula();
                    break;
                case 2:
                    crearSerie();
                    break;
                case 3:
                    crearDocumental();
                    break;
                case 4:
                    crearVideoNeflix();
                    break;
                case 5:
                    crearVideoStreaming();
                    break;
                case 6:
                    actualizarContenido();
                    break;
                case 7:
                    eliminarContenido();
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        }
    }

    private void crearPelicula() {
        String titulo = vista.leerCadena("Título de la película: ");
        int duracion = vista.leerEntero("Duración en minutos: ");
        String genero = vista.leerCadena("Género: ");
        String estudio = vista.leerCadena("Estudio productor: ");

        servicio.crearPelicula(titulo, duracion, genero, estudio);
        vista.mostrarExito("Película creada exitosamente");
        vista.pausa();
    }

    private void crearSerie() {
        String titulo = vista.leerCadena("Título de la serie: ");
        int duracion = vista.leerEntero("Duración promedio por episodio (minutos): ");
        String genero = vista.leerCadena("Género: ");
        int numTemporadas = vista.leerEntero("Número de temporadas: ");

        servicio.crearSerie(titulo, duracion, genero, numTemporadas);
        vista.mostrarExito("Serie creada exitosamente");
        vista.pausa();
    }

    private void crearDocumental() {
        String titulo = vista.leerCadena("Título del documental: ");
        int duracion = vista.leerEntero("Duración en minutos: ");
        String genero = vista.leerCadena("Género: ");
        String tema = vista.leerCadena("Tema principal: ");

        servicio.crearDocumental(titulo, duracion, genero, tema);
        vista.mostrarExito("Documental creado exitosamente");
        vista.pausa();
    }

    private void crearVideoNeflix() {
        String titulo = vista.leerCadena("Título del video Netflix: ");
        int duracion = vista.leerEntero("Duración en minutos: ");
        String genero = vista.leerCadena("Género: ");
        boolean esOriginal = vista.leerBooleano("¿Es original de Netflix?");
        String resolucion = vista.leerCadena("Resolución (4K, 1080p, 720p, etc.): ");

        servicio.crearVideoNeflix(titulo, duracion, genero, esOriginal, resolucion);
        vista.mostrarExito("Video Netflix creado exitosamente");
        vista.pausa();
    }

    private void crearVideoStreaming() {
        String titulo = vista.leerCadena("Título del video Streaming: ");
        int duracion = vista.leerEntero("Duración en minutos: ");
        String genero = vista.leerCadena("Género: ");
        String plataforma = vista.leerCadena("Plataforma (Amazon Prime, Disney+, etc.): ");
        int visualizaciones = vista.leerEntero("Número de visualizaciones: ");

        servicio.crearVideoStriming(titulo, duracion, genero, plataforma, visualizaciones);
        vista.mostrarExito("Video Streaming creado exitosamente");
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

        System.out.println("\n¿Qué deseas actualizar?");
        System.out.println("1. Título");
        System.out.println("2. Duración");
        System.out.println("3. Género");

        int opcion = vista.leerEntero("Selecciona: ");
        boolean exito = false;

        switch (opcion) {
            case 1:
                String nuevoTitulo = vista.leerCadena("Nuevo título: ");
                exito = servicio.actualizarTitulo(id, nuevoTitulo);
                break;
            case 2:
                int nuevaDuracion = vista.leerEntero("Nueva duración (minutos): ");
                exito = servicio.actualizarDuracion(id, nuevaDuracion);
                break;
            case 3:
                String nuevoGenero = vista.leerCadena("Nuevo género: ");
                exito = servicio.actualizarGenero(id, nuevoGenero);
                break;
        }

        if (exito) {
            vista.mostrarExito("Contenido actualizado exitosamente");
        } else {
            vista.mostrarError("No se pudo actualizar el contenido");
        }
        vista.pausa();
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

    // ==================== GESTIÓN DE ACTORES ====================

    private void manejarActores() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuActores();

            switch (opcion) {
                case 1:
                    crearActor();
                    break;
                case 2:
                    listarActores();
                    break;
                case 3:
                    agregarActorAPelicula();
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        }
    }

    private void crearActor() {
        String nombre = vista.leerCadena("Nombre del actor: ");
        String apellido = vista.leerCadena("Apellido del actor: ");

        servicio.crearActor(nombre, apellido);
        vista.mostrarExito("Actor creado exitosamente");
        vista.pausa();
    }

    private void listarActores() {
        List<Actor> actores = servicio.obtenerTodosLosActores();
        vista.mostrarActores(actores);
    }

    private void agregarActorAPelicula() {
        listarTodoContenido();
        int idPelicula = vista.leerEntero("Ingresa el ID de la película: ");

        ContenidoAudiovisual contenido = servicio.obtenerContenidoPorId(idPelicula);
        if (!(contenido instanceof Pelicula)) {
            vista.mostrarError("El contenido seleccionado no es una película");
            vista.pausa();
            return;
        }

        listarActores();
        String nombre = vista.leerCadena("Nombre del actor a añadir: ");
        String apellido = vista.leerCadena("Apellido del actor: ");

        Actor actor = servicio.buscarActor(nombre, apellido);
        if (actor == null) {
            vista.mostrarAdvertencia("Actor no encontrado. Créalo primero");
            return;
        }

        if (servicio.agregarActorAPelicula(idPelicula, actor)) {
            vista.mostrarExito("Actor añadido a la película exitosamente");
        } else {
            vista.mostrarError("No se pudo añadir el actor");
        }
        vista.pausa();
    }

    // ==================== GESTIÓN DE TEMPORADAS ====================

    private void manejarTemporadas() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuTemporadas();

            switch (opcion) {
                case 1:
                    agregarTemporada();
                    break;
                case 2:
                    verTemporadas();
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        }
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

        int numeroTemporada = vista.leerEntero("Número de temporada: ");
        int episodios = vista.leerEntero("Número de episodios: ");

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

    // ==================== BÚSQUEDA Y FILTRADO ====================

    private void manejarBusqueda() {
        boolean enMenu = true;
        while (enMenu) {
            int opcion = vista.mostrarMenuBusqueda();

            switch (opcion) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    buscarPorGenero();
                    break;
                case 3:
                    buscarPorTipo();
                    break;
                case 4:
                    obtenerPorId();
                    break;
                case 0:
                    enMenu = false;
                    break;
                default:
                    vista.mostrarError("Opción no válida");
            }
        }
    }

    private void buscarPorTitulo() {
        String titulo = vista.leerCadena("Ingresa el título a buscar: ");
        List<ContenidoAudiovisual> resultados = servicio.buscarPorTitulo(titulo);
        vista.mostrarContenidos(resultados);
    }

    private void buscarPorGenero() {
        String genero = vista.leerCadena("Ingresa el género a buscar: ");
        List<ContenidoAudiovisual> resultados = servicio.buscarPorGenero(genero);
        vista.mostrarContenidos(resultados);
    }

    private void buscarPorTipo() {
        System.out.println("\nTipos disponibles:");
        System.out.println("1. Pelicula");
        System.out.println("2. SerieDeTV");
        System.out.println("3. Documental");
        System.out.println("4. VideoNeflix");
        System.out.println("5. VideoStriming");

        int opcion = vista.leerEntero("Selecciona el tipo: ");
        String tipo = "";

        switch (opcion) {
            case 1:
                tipo = "Pelicula";
                break;
            case 2:
                tipo = "SerieDeTV";
                break;
            case 3:
                tipo = "Documental";
                break;
            case 4:
                tipo = "VideoNeflix";
                break;
            case 5:
                tipo = "VideoStriming";
                break;
            default:
                vista.mostrarError("Tipo no válido");
                return;
        }

        List<ContenidoAudiovisual> resultados = servicio.buscarPorTipo(tipo);
        vista.mostrarContenidos(resultados);
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

    // ==================== UTILIDADES ====================

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
