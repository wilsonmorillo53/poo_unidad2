package view;

import java.util.List;
import java.util.Scanner;
import uni1a.ContenidoAudiovisual;
import uni1a.Documental;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Actor;
import ups.expancion.VideoNeflix;
import ups.expancion.VideoStriming;

/**
 * Vista de consola para la gestión de contenido audiovisual
 * Maneja la presentación e interacción con el usuario
 */
public class ConsoleView {
    private Scanner scanner;
    private boolean activo;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
        this.activo = true;
    }

    /**
     * Muestra el menú principal
     */
    public int mostrarMenuPrincipal() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN AUDIOVISUAL - MVC    ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        System.out.println("1. Gestionar Contenido");
        System.out.println("2. Gestionar Actores");
        System.out.println("3. Gestionar Temporadas (Series)");
        System.out.println("4. Búsqueda y Filtrado");
        System.out.println("5. Ver Estadísticas");
        System.out.println("6. Listar Todo el Contenido");
        System.out.println("0. Salir\n");
        return leerOpcion();
    }

    /**
     * Menú para gestionar contenido
     */
    public int mostrarMenuContenido() {
        limpiarPantalla();
        System.out.println("━━━━━ GESTIÓN DE CONTENIDO ━━━━━\n");
        System.out.println("1. Crear Película");
        System.out.println("2. Crear Serie de TV");
        System.out.println("3. Crear Documental");
        System.out.println("4. Crear Video Netflix");
        System.out.println("5. Crear Video Streaming");
        System.out.println("6. Actualizar Contenido");
        System.out.println("7. Eliminar Contenido");
        System.out.println("0. Volver\n");
        return leerOpcion();
    }

    /**
     * Menú para buscar y filtrar
     */
    public int mostrarMenuBusqueda() {
        limpiarPantalla();
        System.out.println("━━━━━ BÚSQUEDA Y FILTRADO ━━━━━\n");
        System.out.println("1. Buscar por Título");
        System.out.println("2. Buscar por Género");
        System.out.println("3. Buscar por Tipo de Contenido");
        System.out.println("4. Obtener Contenido por ID");
        System.out.println("0. Volver\n");
        return leerOpcion();
    }

    /**
     * Menú para gestionar actores
     */
    public int mostrarMenuActores() {
        limpiarPantalla();
        System.out.println("━━━━━ GESTIÓN DE ACTORES ━━━━━\n");
        System.out.println("1. Crear Actor");
        System.out.println("2. Listar Actores");
        System.out.println("3. Añadir Actor a Película");
        System.out.println("0. Volver\n");
        return leerOpcion();
    }

    /**
     * Menú para gestionar temporadas
     */
    public int mostrarMenuTemporadas() {
        limpiarPantalla();
        System.out.println("━━━━━ GESTIÓN DE TEMPORADAS ━━━━━\n");
        System.out.println("1. Añadir Temporada a Serie");
        System.out.println("2. Ver Temporadas de una Serie");
        System.out.println("0. Volver\n");
        return leerOpcion();
    }

    // ==================== Entrada de Datos ====================

    private int leerOpcion() {
        try {
            System.out.print("Selecciona opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();
            mostrarError("Opción inválida");
            return -1;
        }
    }

    public String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        try {
            System.out.print(mensaje);
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (Exception e) {
            scanner.nextLine();
            mostrarError("Entrada inválida");
            return -1;
        }
    }

    public int leerLargo(String mensaje) {
        try {
            System.out.print(mensaje);
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (Exception e) {
            scanner.nextLine();
            mostrarError("Entrada inválida");
            return -1;
        }
    }

    public boolean leerBooleano(String mensaje) {
        String respuesta = leerCadena(mensaje + " (sí/no): ").toLowerCase();
        return respuesta.equals("sí") || respuesta.equals("si") || respuesta.equals("s") || respuesta.equals("true");
    }

    // ==================== Mostrar Información ====================

    public void mostrarContenidos(List<ContenidoAudiovisual> contenidos) {
        if (contenidos.isEmpty()) {
            mostrarAdvertencia("No hay contenidos para mostrar");
            return;
        }

        limpiarPantalla();
        System.out.println("════════════════════════════════════════");
        System.out.println("           LISTADO DE CONTENIDOS");
        System.out.println("════════════════════════════════════════\n");

        for (ContenidoAudiovisual contenido : contenidos) {
            mostrarResumenContenido(contenido);
            System.out.println();
        }
        pausa();
    }

    private void mostrarResumenContenido(ContenidoAudiovisual contenido) {
        String tipo = contenido.getClass().getSimpleName();
        System.out.printf("[ID: %d] %s (%s)%n", contenido.getId(), contenido.getTitulo(), tipo);
        System.out.printf("   Duración: %d min | Género: %s%n", contenido.getDuracionEnMinutos(), contenido.getGenero());

        if (contenido instanceof Pelicula) {
            Pelicula p = (Pelicula) contenido;
            System.out.printf("   Estudio: %s | Actores: %d%n", p.getEstudio(), p.getActores().size());
        } else if (contenido instanceof SerieDeTV) {
            SerieDeTV s = (SerieDeTV) contenido;
            System.out.printf("   Temporadas: %d%n", s.getTemporadas());
        } else if (contenido instanceof Documental) {
            Documental d = (Documental) contenido;
            System.out.printf("   Tema: %s%n", d.getTema());
        } else if (contenido instanceof VideoNeflix) {
            VideoNeflix vn = (VideoNeflix) contenido;
            System.out.printf("   Original: %s | Resolución: %s%n", vn.isEsOriginal(), vn.getResolucion());
        } else if (contenido instanceof VideoStriming) {
            VideoStriming vs = (VideoStriming) contenido;
            System.out.printf("   Plataforma: %s | Visualizaciones: %d%n", vs.getPlataforma(), vs.getVisualizaciones());
        }
    }

    public void mostrarDetallesContenido(ContenidoAudiovisual contenido) {
        limpiarPantalla();
        if (contenido != null) {
            System.out.println("════════════════════════════════════════");
            System.out.println("        DETALLES DEL CONTENIDO");
            System.out.println("════════════════════════════════════════\n");
            contenido.mostrarDetalles();
            System.out.println();
        }
        pausa();
    }

    public void mostrarActores(List<Actor> actores) {
        if (actores.isEmpty()) {
            mostrarAdvertencia("No hay actores registrados");
            return;
        }

        limpiarPantalla();
        System.out.println("════════════════════════════════════════");
        System.out.println("          LISTADO DE ACTORES");
        System.out.println("════════════════════════════════════════\n");

        for (int i = 0; i < actores.size(); i++) {
            Actor actor = actores.get(i);
            System.out.printf("%d. %s %s%n", i + 1, actor.getNombre(), actor.getApellido());
        }
        System.out.println();
        pausa();
    }

    // ==================== Mensajes ====================

    public void mostrarExito(String mensaje) {
        System.out.println("\n✓ " + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("\n✗ " + mensaje);
    }

    public void mostrarAdvertencia(String mensaje) {
        System.out.println("\n! " + mensaje);
        pausa();
    }

    public void mostrarInfo(String mensaje) {
        System.out.println("\nℹ " + mensaje);
    }

    // ==================== Utilidades ====================

    private void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void pausa() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

    public void cerrar() {
        scanner.close();
        activo = false;
    }

    public boolean estaActivo() {
        return activo;
    }
}
