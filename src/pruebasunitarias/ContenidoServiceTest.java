package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ContenidoService;
import uni1a.ContenidoAudiovisual;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.Documental;
import uni1a.ups.clases.adicionales.Actor;
import java.util.List;

public class ContenidoServiceTest {
    private ContenidoService service;

    @BeforeEach
    public void setUp() {
        service = new ContenidoService();
    }

    // ==================== CRUD Contenido ====================

    @Test
    public void testServiceIniciaVacio() {
        assertEquals(0, service.totalContenidos());
        assertEquals(0, service.totalActores());
    }

    @Test
    public void testCrearPelicula() {
        service.crearPelicula("Inception", 148, "Ciencia Ficcion", "Warner");
        assertEquals(1, service.totalContenidos());
        ContenidoAudiovisual c = service.obtenerTodosLosContenidos().get(0);
        assertTrue(c instanceof Pelicula);
        assertEquals("Inception", c.getTitulo());
    }

    @Test
    public void testCrearSerie() {
        service.crearSerie("Breaking Bad", 49, "Drama", 5);
        assertEquals(1, service.totalContenidos());
        assertTrue(service.obtenerTodosLosContenidos().get(0) instanceof SerieDeTV);
    }

    @Test
    public void testCrearDocumental() {
        service.crearDocumental("Planet Earth", 50, "Naturaleza", "Vida Salvaje");
        assertEquals(1, service.totalContenidos());
        assertTrue(service.obtenerTodosLosContenidos().get(0) instanceof Documental);
    }

    @Test
    public void testCrearVideoNeflix() {
        service.crearVideoNeflix("Stranger Things", 60, "Terror", true, "4K");
        assertEquals(1, service.totalContenidos());
    }

    @Test
    public void testCrearVideoStriming() {
        service.crearVideoStriming("The Boys", 60, "Accion", "Amazon Prime", 5000000);
        assertEquals(1, service.totalContenidos());
    }

    @Test
    public void testCrearMultiplesContenidos() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearSerie("S1", 30, "G2", 3);
        service.crearDocumental("D1", 60, "G3", "T1");
        service.crearVideoNeflix("V1", 45, "G4", true, "1080p");
        service.crearVideoStriming("VS1", 50, "G5", "P1", 1000);
        assertEquals(5, service.totalContenidos());
    }

    // ==================== Lectura ====================

    @Test
    public void testObtenerTodosLosContenidos() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearSerie("S1", 30, "G2", 3);
        List<ContenidoAudiovisual> todos = service.obtenerTodosLosContenidos();
        assertEquals(2, todos.size());
    }

    @Test
    public void testObtenerContenidoPorIdExistente() {
        service.crearPelicula("P1", 100, "G1", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertNotNull(service.obtenerContenidoPorId(id));
    }

    @Test
    public void testObtenerContenidoPorIdInexistente() {
        assertNull(service.obtenerContenidoPorId(999));
    }

    // ==================== Búsqueda ====================

    @Test
    public void testBuscarPorTitulo() {
        service.crearPelicula("Inception", 148, "CF", "W");
        service.crearPelicula("Interstellar", 169, "CF", "W");
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("Inception");
        assertEquals(1, resultados.size());
        assertEquals("Inception", resultados.get(0).getTitulo());
    }

    @Test
    public void testBuscarPorTituloParcial() {
        service.crearPelicula("Inception", 148, "CF", "W");
        service.crearPelicula("Interstellar", 169, "CF", "W");
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("in");
        assertEquals(2, resultados.size());
    }

    @Test
    public void testBuscarPorTituloSinResultados() {
        service.crearPelicula("Inception", 148, "CF", "W");
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("XYZ");
        assertTrue(resultados.isEmpty());
    }

    @Test
    public void testBuscarPorGenero() {
        service.crearPelicula("P1", 100, "Accion", "E1");
        service.crearPelicula("P2", 110, "Accion", "E2");
        service.crearPelicula("P3", 120, "Drama", "E3");
        List<ContenidoAudiovisual> resultados = service.buscarPorGenero("Accion");
        assertEquals(2, resultados.size());
    }

    @Test
    public void testBuscarPorGeneroCaseInsensitive() {
        service.crearPelicula("P1", 100, "Accion", "E1");
        List<ContenidoAudiovisual> resultados = service.buscarPorGenero("accion");
        assertEquals(1, resultados.size());
    }

    @Test
    public void testBuscarPorTipo() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearSerie("S1", 30, "G2", 3);
        List<ContenidoAudiovisual> resultados = service.buscarPorTipo("Pelicula");
        assertEquals(1, resultados.size());
        assertTrue(resultados.get(0) instanceof Pelicula);
    }

    // ==================== Actualización ====================

    @Test
    public void testActualizarTitulo() {
        service.crearPelicula("Original", 100, "G1", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertTrue(service.actualizarTitulo(id, "Actualizado"));
        assertEquals("Actualizado", service.obtenerContenidoPorId(id).getTitulo());
    }

    @Test
    public void testActualizarTituloIdInexistente() {
        assertFalse(service.actualizarTitulo(999, "Nuevo"));
    }

    @Test
    public void testActualizarDuracion() {
        service.crearPelicula("P1", 100, "G1", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertTrue(service.actualizarDuracion(id, 200));
        assertEquals(200, service.obtenerContenidoPorId(id).getDuracionEnMinutos());
    }

    @Test
    public void testActualizarGenero() {
        service.crearPelicula("P1", 100, "Accion", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertTrue(service.actualizarGenero(id, "Comedia"));
        assertEquals("Comedia", service.obtenerContenidoPorId(id).getGenero());
    }

    // ==================== Eliminación ====================

    @Test
    public void testEliminarContenidoExistente() {
        service.crearPelicula("P1", 100, "G1", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertTrue(service.eliminarContenido(id));
        assertEquals(0, service.totalContenidos());
    }

    @Test
    public void testEliminarContenidoInexistente() {
        assertFalse(service.eliminarContenido(999));
    }

    // ==================== Gestión de Actores ====================

    @Test
    public void testCrearActor() {
        service.crearActor("Leonardo", "DiCaprio");
        assertEquals(1, service.totalActores());
        assertNotNull(service.buscarActor("Leonardo", "DiCaprio"));
    }

    @Test
    public void testCrearMultiplesActores() {
        service.crearActor("A", "B");
        service.crearActor("C", "D");
        assertEquals(2, service.totalActores());
    }

    @Test
    public void testObtenerTodosLosActores() {
        service.crearActor("A1", "B1");
        service.crearActor("A2", "B2");
        List<Actor> actores = service.obtenerTodosLosActores();
        assertEquals(2, actores.size());
    }

    @Test
    public void testBuscarActorExistente() {
        service.crearActor("Juan", "Perez");
        Actor actor = service.buscarActor("Juan", "Perez");
        assertNotNull(actor);
        assertEquals("Juan", actor.getNombre());
        assertEquals("Perez", actor.getApellido());
    }

    @Test
    public void testBuscarActorCaseInsensitive() {
        service.crearActor("Juan", "Perez");
        assertNotNull(service.buscarActor("juan", "perez"));
    }

    @Test
    public void testBuscarActorInexistente() {
        assertNull(service.buscarActor("No", "Existe"));
    }

    @Test
    public void testAgregarActorAPelicula() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearActor("Actor", "Uno");
        int idPeli = service.obtenerTodosLosContenidos().get(0).getId();
        Actor actor = service.buscarActor("Actor", "Uno");

        assertTrue(service.agregarActorAPelicula(idPeli, actor));

        Pelicula peli = (Pelicula) service.obtenerContenidoPorId(idPeli);
        assertEquals(1, peli.getActores().size());
        assertSame(actor, peli.getActores().get(0));
    }

    @Test
    public void testAgregarActorAPeliculaInexistente() {
        service.crearActor("A", "B");
        Actor actor = service.buscarActor("A", "B");
        assertFalse(service.agregarActorAPelicula(999, actor));
    }

    @Test
    public void testAgregarActorAContenidoQueNoEsPelicula() {
        service.crearSerie("S1", 30, "Drama", 3);
        service.crearActor("A", "B");
        int idSerie = service.obtenerTodosLosContenidos().get(0).getId();
        Actor actor = service.buscarActor("A", "B");
        assertFalse(service.agregarActorAPelicula(idSerie, actor));
    }

    // ==================== Gestión de Temporadas ====================

    @Test
    public void testAgregarTemporadaASerie() {
        service.crearSerie("S1", 30, "Drama", 3);
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertTrue(service.agregarTemporadaASerie(id, 1, 10));

        SerieDeTV serie = service.obtenerSerie(id);
        assertEquals(1, serie.getListaTemporadas().size());
        assertEquals(1, serie.getListaTemporadas().get(0).getNumero());
        assertEquals(10, serie.getListaTemporadas().get(0).getEpisodios());
    }

    @Test
    public void testAgregarTemporadaASerieInexistente() {
        assertFalse(service.agregarTemporadaASerie(999, 1, 10));
    }

    @Test
    public void testAgregarTemporadaAPeliculaNoFunciona() {
        service.crearPelicula("P1", 100, "G1", "E1");
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertFalse(service.agregarTemporadaASerie(id, 1, 10));
    }

    @Test
    public void testObtenerSerieExistente() {
        service.crearSerie("S1", 30, "Drama", 3);
        int id = service.obtenerTodosLosContenidos().get(0).getId();
        assertNotNull(service.obtenerSerie(id));
    }

    @Test
    public void testObtenerSerieInexistente() {
        assertNull(service.obtenerSerie(999));
    }

    // ==================== Estadísticas ====================

    @Test
    public void testObtenerEstadisticas() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearSerie("S1", 30, "G2", 3);
        String stats = service.obtenerEstadisticas();
        assertTrue(stats.contains("Total de contenidos: 2"));
        assertTrue(stats.contains("Películas: 1"));
        assertTrue(stats.contains("Series: 1"));
    }

    // ==================== Persistencia ====================

    @Test
    public void testGuardarYCargarSistema() {
        service.crearPelicula("P1", 100, "G1", "E1");
        service.crearActor("Actor", "Uno");
        int idPeli = service.obtenerTodosLosContenidos().get(0).getId();
        service.agregarActorAPelicula(idPeli, service.buscarActor("Actor", "Uno"));

        String carpeta = "test_data_temp";
        assertTrue(service.guardarSistema(carpeta));

        ContenidoService service2 = new ContenidoService();
        assertTrue(service2.cargarSistema(carpeta));
        assertEquals(1, service2.totalContenidos());
        assertEquals(1, service2.totalActores());

        java.io.File dir = new java.io.File(carpeta);
        for (java.io.File f : dir.listFiles()) {
            f.delete();
        }
        dir.delete();
    }

    @Test
    public void testCargarSistemaCarpetaInexistente() {
        assertFalse(service.cargarSistema("carpeta_inexistente_xyz"));
    }
}
