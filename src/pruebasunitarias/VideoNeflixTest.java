package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ups.expancion.VideoNeflix;
import uni1a.ups.clases.adicionales.Actor;

public class VideoNeflixTest {
    private VideoNeflix video;

    @BeforeEach
    public void setUp() {
        video = new VideoNeflix("Stranger Things", 60, "Terror", true, "4K");
    }

    @Test
    public void testConstructor() {
        assertEquals("Stranger Things", video.getTitulo());
        assertEquals(60, video.getDuracionEnMinutos());
        assertEquals("Terror", video.getGenero());
        assertTrue(video.isEsOriginal());
        assertEquals("4K", video.getResolucion());
        assertTrue(video.getActores().isEmpty());
    }

    @Test
    public void testSetEsOriginal() {
        video.setEsOriginal(false);
        assertFalse(video.isEsOriginal());
    }

    @Test
    public void testSetResolucion() {
        video.setResolucion("1080p");
        assertEquals("1080p", video.getResolucion());
    }

    @Test
    public void testAgregarActor() {
        Actor actor = new Actor("Millie", "Brown", null);
        video.agregarActor(actor);
        assertEquals(1, video.getActores().size());
        assertSame(actor, video.getActores().get(0));
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
