package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni1a.Pelicula;
import uni1a.ups.clases.adicionales.Actor;

public class PeliculaTest {
    private Pelicula pelicula;

    @BeforeEach
    public void setUp() {
        pelicula = new Pelicula("Inception", 148, "Ciencia Ficcion", "Warner Bros");
    }

    @Test
    public void testConstructor() {
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(148, pelicula.getDuracionEnMinutos());
        assertEquals("Ciencia Ficcion", pelicula.getGenero());
        assertEquals("Warner Bros", pelicula.getEstudio());
        assertTrue(pelicula.getActores().isEmpty());
    }

    @Test
    public void testGetId() {
        assertTrue(pelicula.getId() >= 0);
    }

    @Test
    public void testSetTitulo() {
        pelicula.setTitulo("The Matrix");
        assertEquals("The Matrix", pelicula.getTitulo());
    }

    @Test
    public void testSetDuracion() {
        pelicula.setDuracionEnMinutos(120);
        assertEquals(120, pelicula.getDuracionEnMinutos());
    }

    @Test
    public void testSetGenero() {
        pelicula.setGenero("Accion");
        assertEquals("Accion", pelicula.getGenero());
    }

    @Test
    public void testSetEstudio() {
        pelicula.setEstudio("Universal");
        assertEquals("Universal", pelicula.getEstudio());
    }

    @Test
    public void testAgregarActor() {
        Actor actor = new Actor("Leonardo", "DiCaprio", pelicula);
        pelicula.agregarActor(actor);
        assertEquals(1, pelicula.getActores().size());
        assertSame(actor, pelicula.getActores().get(0));
    }

    @Test
    public void testAgregarMultiplesActores() {
        Actor a1 = new Actor("Actor1", "Ap1", pelicula);
        Actor a2 = new Actor("Actor2", "Ap2", pelicula);
        pelicula.agregarActor(a1);
        pelicula.agregarActor(a2);
        assertEquals(2, pelicula.getActores().size());
    }

    @Test
    public void testHerencia() {
        assertTrue(pelicula instanceof uni1a.ContenidoAudiovisual);
    }

    @Test
    public void testMostrarDetallesNoLanzaExcepcion() {
        assertDoesNotThrow(() -> pelicula.mostrarDetalles());
    }
}
