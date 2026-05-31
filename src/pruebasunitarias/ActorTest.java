package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uni1a.Pelicula;
import uni1a.ups.clases.adicionales.Actor;

public class ActorTest {

    @Test
    public void testConstructorConPelicula() {
        Pelicula p = new Pelicula("Test", 120, "Accion", "TestStudio");
        Actor actor = new Actor("Juan", "Perez", p);
        assertEquals("Juan", actor.getNombre());
        assertEquals("Perez", actor.getApellido());
        assertSame(p, actor.getPelicula());
    }

    @Test
    public void testConstructorSinPelicula() {
        Actor actor = new Actor("Ana", "Lopez", null);
        assertEquals("Ana", actor.getNombre());
        assertEquals("Lopez", actor.getApellido());
        assertNull(actor.getPelicula());
    }

    @Test
    public void testSetNombre() {
        Actor actor = new Actor("A", "B", null);
        actor.setNombre("Carlos");
        assertEquals("Carlos", actor.getNombre());
    }

    @Test
    public void testSetApellido() {
        Actor actor = new Actor("A", "B", null);
        actor.setApellido("Garcia");
        assertEquals("Garcia", actor.getApellido());
    }

    @Test
    public void testSetPelicula() {
        Actor actor = new Actor("A", "B", null);
        Pelicula p = new Pelicula("Peli", 90, "Drama", "Estudio");
        actor.setPelicula(p);
        assertSame(p, actor.getPelicula());
    }
}
