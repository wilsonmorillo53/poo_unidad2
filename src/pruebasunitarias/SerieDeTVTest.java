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
        assertEquals(5, serie.getNumeroDeTemporadas());
        assertTrue(serie.getTemporadas().isEmpty());
    }

    @Test
    public void testSetNumeroDeTemporadas() {
        serie.setNumeroDeTemporadas(6);
        assertEquals(6, serie.getNumeroDeTemporadas());
    }

    @Test
    public void testAgregarTemporada() {
        Temporada temp = new Temporada(1, 7, serie);
        serie.agregarTemporada(temp);
        assertEquals(1, serie.getTemporadas().size());
        assertSame(temp, serie.getTemporadas().get(0));
    }

    @Test
    public void testAgregarMultiplesTemporadas() {
        serie.agregarTemporada(new Temporada(1, 7, serie));
        serie.agregarTemporada(new Temporada(2, 8, serie));
        assertEquals(2, serie.getTemporadas().size());
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
