package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Temporada;

public class TemporadaTest {

    @Test
    public void testConstructorConSerie() {
        SerieDeTV serie = new SerieDeTV("Serie", 45, "Drama", 3);
        Temporada temp = new Temporada(1, 10, serie);
        assertEquals(1, temp.getNumero());
        assertEquals(10, temp.getEpisodios());
        assertSame(serie, temp.getSerie());
    }

    @Test
    public void testConstructorSinSerie() {
        Temporada temp = new Temporada(2, 8, null);
        assertEquals(2, temp.getNumero());
        assertEquals(8, temp.getEpisodios());
        assertNull(temp.getSerie());
    }

    @Test
    public void testSetNumero() {
        Temporada temp = new Temporada(1, 10, null);
        temp.setNumero(5);
        assertEquals(5, temp.getNumero());
    }

    @Test
    public void testSetEpisodios() {
        Temporada temp = new Temporada(1, 10, null);
        temp.setEpisodios(20);
        assertEquals(20, temp.getEpisodios());
    }

    @Test
    public void testSetSerie() {
        Temporada temp = new Temporada(1, 10, null);
        SerieDeTV serie = new SerieDeTV("Nueva", 30, "Comedia", 2);
        temp.setSerie(serie);
        assertSame(serie, temp.getSerie());
    }
}
