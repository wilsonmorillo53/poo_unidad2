package Main;

import service.ContenidoService;
import uni1a.ContenidoAudiovisual;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.Documental;
import uni1a.ups.clases.adicionales.Actor;
import ups.expancion.VideoNeflix;
import ups.expancion.VideoStriming;
import java.io.File;

public class TestManejoArchivos {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Prueba de Lectura y Escritura de Archivos ===");
        ContenidoService servicio = new ContenidoService();

        // 1. Crear contenidos
        System.out.println("\nCreando contenidos en el sistema...");
        servicio.crearPelicula("El Gran Viaje", 120, "Aventura", "Universal Pictures");
        servicio.crearSerie("Misterios de la Noche", 45, "Suspenso", 3);
        servicio.crearDocumental("Planeta Azul", 90, "Ciencia", "Océanos");
        servicio.crearVideoNeflix("Stranger Days", 50, "Ciencia Ficción", true, "4K");
        servicio.crearVideoStriming("Live Concert", 180, "Música", "YouTube Live", 50000);

        // 2. Asociar Actores
        System.out.println("Creando y asociando actores...");
        servicio.crearActor("Juan", "Pérez");
        servicio.crearActor("Ana", "Gómez");
        
        Actor juan = servicio.buscarActor("Juan", "Pérez");
        Actor ana = servicio.buscarActor("Ana", "Gómez");
        
        // Asociar Juan a la película con ID 0
        servicio.agregarActorAPelicula(0, juan);
        
        // Asociar Ana a un Video Netflix (ID 3)
        ContenidoAudiovisual videoNf = servicio.obtenerContenidoPorId(3);
        if (videoNf instanceof VideoNeflix) {
            ((VideoNeflix) videoNf).agregarActor(ana);
        }

        // 3. Asociar Temporadas a la serie (ID 1)
        System.out.println("Asociando temporadas...");
        servicio.agregarTemporadaASerie(1, 1, 10);
        servicio.agregarTemporadaASerie(1, 2, 8);

        // Imprimir estadísticas iniciales
        System.out.println(servicio.obtenerEstadisticas());

        // 4. Guardar sistema
        String carpeta = "test_data_run";
        System.out.println("Guardando sistema en carpeta '" + carpeta + "'...");
        boolean guardado = servicio.guardarSistema(carpeta);
        if (!guardado) {
            System.err.println("ERROR: No se pudo guardar el sistema.");
            System.exit(1);
        }
        System.out.println("Guardado exitoso.");

        // Verificar archivos
        System.out.println("Contenido de la carpeta:");
        File dir = new File(carpeta);
        for (File f : dir.listFiles()) {
            System.out.println(" - " + f.getName() + " (" + f.length() + " bytes)");
        }

        // 5. Cargar sistema en una nueva instancia del servicio
        System.out.println("\nInstanciando nuevo servicio y cargando datos...");
        ContenidoService nuevoServicio = new ContenidoService();
        boolean cargado = nuevoServicio.cargarSistema(carpeta);
        if (!cargado) {
            System.err.println("ERROR: No se pudo cargar el sistema.");
            System.exit(1);
        }
        System.out.println("Carga exitosa.");

        // Imprimir estadísticas de la nueva instancia
        System.out.println(nuevoServicio.obtenerEstadisticas());

        // 6. Validar datos
        System.out.println("Validando integridad de objetos y relaciones cargadas...");
        
        // Validar película (ID 0)
        ContenidoAudiovisual p = nuevoServicio.obtenerContenidoPorId(0);
        if (p instanceof Pelicula) {
            Pelicula pelicula = (Pelicula) p;
            System.out.println("Película cargada: " + pelicula.getTitulo() + ", Estudio: " + pelicula.getEstudio() + ", ID: " + pelicula.getId());
            if (pelicula.getActores().size() == 1) {
                Actor act = pelicula.getActores().get(0);
                System.out.println(" ✓ Actor asociado correctamente: " + act.getNombre() + " " + act.getApellido());
            } else {
                System.err.println(" ✗ ERROR: La película debería tener 1 actor, tiene: " + pelicula.getActores().size());
            }
        } else {
            System.err.println(" ✗ ERROR: No se encontró la película con ID 0.");
        }

        // Validar serie (ID 1)
        ContenidoAudiovisual s = nuevoServicio.obtenerContenidoPorId(1);
        if (s instanceof SerieDeTV) {
            SerieDeTV serie = (SerieDeTV) s;
            System.out.println("Serie cargada: " + serie.getTitulo() + ", Temporadas declaradas: " + serie.getNumeroDeTemporadas() + ", ID: " + serie.getId());
            System.out.println("Lista de temporadas asociadas:");
            for (var temp : serie.getTemporadas()) {
                System.out.println(" - Temporada " + temp.getNumero() + " con " + temp.getEpisodios() + " episodios. Serie asociada ID: " + (temp.getSerie() != null ? temp.getSerie().getId() : "null"));
            }
            if (serie.getTemporadas().size() == 2) {
                System.out.println(" ✓ Temporadas asociadas correctamente.");
            } else {
                System.err.println(" ✗ ERROR: Se esperaban 2 temporadas, se encontraron: " + serie.getTemporadas().size());
            }
        } else {
            System.err.println(" ✗ ERROR: No se encontró la serie con ID 1.");
        }

        // Validar Video Netflix (ID 3)
        ContenidoAudiovisual vn = nuevoServicio.obtenerContenidoPorId(3);
        if (vn instanceof VideoNeflix) {
            VideoNeflix video = (VideoNeflix) vn;
            System.out.println("Video Netflix cargado: " + video.getTitulo() + ", Resolución: " + video.getResolucion() + ", ID: " + video.getId());
            if (video.getActores().size() == 1) {
                Actor act = video.getActores().get(0);
                System.out.println(" ✓ Actor asociado correctamente: " + act.getNombre() + " " + act.getApellido());
            } else {
                System.err.println(" ✗ ERROR: El video Netflix debería tener 1 actor, tiene: " + video.getActores().size());
            }
        } else {
            System.err.println(" ✗ ERROR: No se encontró el video Netflix con ID 3.");
        }

        // Validar creación de nuevos contenidos mantiene IDs incrementales
        System.out.println("\nCreando nuevo contenido después de cargar...");
        nuevoServicio.crearPelicula("El Retorno", 140, "Aventura", "Warner Bros");
        ContenidoAudiovisual p2 = nuevoServicio.obtenerTodosLosContenidos().get(nuevoServicio.obtenerTodosLosContenidos().size() - 1);
        System.out.println("Nuevo contenido ID creado: " + p2.getId() + " (debería ser 5)");
        if (p2.getId() == 5) {
            System.out.println(" ✓ ID incrementado correctamente sin duplicados.");
        } else {
            System.err.println(" ✗ ERROR: ID incorrecto, se esperaba 5 pero obtuvo: " + p2.getId());
        }

        // Limpiar directorio de test
        System.out.println("\nLimpiando archivos de prueba...");
        for (File f : dir.listFiles()) {
            f.delete();
        }
        dir.delete();
        System.out.println("Limpieza completada.");
        System.out.println("\n=== PRUEBA COMPLETADA CON ÉXITO ===");
    }
}
