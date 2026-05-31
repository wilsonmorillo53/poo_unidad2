package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Temporada;

public class SerieDeTVTest {
    private SerieDeTV serie;

    @BeforeEach
    public void setUp() {
        serie = new SerieDeTV("Breaking Bad", 49, "Drama", 5);
    }

    @Test
    public void testConstructor() {
        assertEquals("Breaking Bad", serie.getTitulo());
        assertEquals(49, serie.getDuracionEnMinutos());
        assertEquals("Drama", serie.getGenero());
        assertEquals(5, serie.getTemporadas());
        assertTrue(serie.getListaTemporadas().isEmpty());
    }

    @Test
    public void testSetTemporadas() {
        serie.setTemporadas(6);
        assertEquals(6, serie.getTemporadas());
    }

    @Test
    public void testAgregarTemporada() {
        Temporada temp = new Temporada(1, 7, serie);
        serie.agregarTemporada(temp);
        assertEquals(1, serie.getListaTemporadas().size());
        assertSame(temp, serie.getListaTemporadas().get(0));
    }

    @Test
    public void testAgregarMultiplesTemporadas() {
        serie.agregarTemporada(new Temporada(1, 7, serie));
        serie.agregarTemporada(new Temporada(2, 8, serie));
        assertEquals(2, serie.getListaTemporadas().size());
    }

    @Test
    public void testHerencia() {
        assertTrue(serie instanceof uni1a.ContenidoAudiovisual);
    }

    @Test
    public void testMostrarDetallesNoLanzaExcepcion() {
        assertDoesNotThrow(() -> serie.mostrarDetalles());
    }
}
