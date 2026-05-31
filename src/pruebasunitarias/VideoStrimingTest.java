package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ups.expancion.VideoStriming;
import uni1a.ups.clases.adicionales.Actor;
import uni1a.ups.clases.adicionales.Temporada;

public class VideoStrimingTest {
    private VideoStriming video;

    @BeforeEach
    public void setUp() {
        video = new VideoStriming("The Boys", 60, "Accion", "Amazon Prime", 5000000);
    }

    @Test
    public void testConstructor() {
        assertEquals("The Boys", video.getTitulo());
        assertEquals(60, video.getDuracionEnMinutos());
        assertEquals("Accion", video.getGenero());
        assertEquals("Amazon Prime", video.getPlataforma());
        assertEquals(5000000, video.getVisualizaciones());
        assertTrue(video.getActores().isEmpty());
        assertTrue(video.getTemporadas().isEmpty());
    }

    @Test
    public void testSetPlataforma() {
        video.setPlataforma("Disney+");
        assertEquals("Disney+", video.getPlataforma());
    }

    @Test
    public void testSetVisualizaciones() {
        video.setVisualizaciones(10000000);
        assertEquals(10000000, video.getVisualizaciones());
    }

    @Test
    public void testAgregarActor() {
        Actor actor = new Actor("Karl", "Urban", null);
        video.agregarActor(actor);
        assertEquals(1, video.getActores().size());
    }

    @Test
    public void testAgregarTemporada() {
        Temporada temp = new Temporada(1, 8, null);
        video.agregarTemporada(temp);
        assertEquals(1, video.getTemporadas().size());
        assertSame(temp, video.getTemporadas().get(0));
    }

    @Test
    public void testAgregarMultiplesTemporadas() {
        video.agregarTemporada(new Temporada(1, 8, null));
        video.agregarTemporada(new Temporada(2, 8, null));
        assertEquals(2, video.getTemporadas().size());
    }

    @Test
    public void testHerencia() {
        assertTrue(video instanceof uni1a.ContenidoAudiovisual);
    }

    @Test
    public void testMostrarDetallesNoLanzaExcepcion() {
        assertDoesNotThrow(() -> video.mostrarDetalles());
    }
}
