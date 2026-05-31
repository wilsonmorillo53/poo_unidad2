package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uni1a.Documental;
import uni1a.ups.clases.adicionales.Investigador;

public class DocumentalTest {
    private Documental documental;

    @BeforeEach
    public void setUp() {
        documental = new Documental("Planet Earth", 50, "Naturaleza", "Vida Salvaje");
    }

    @Test
    public void testConstructor() {
        assertEquals("Planet Earth", documental.getTitulo());
        assertEquals(50, documental.getDuracionEnMinutos());
        assertEquals("Naturaleza", documental.getGenero());
        assertEquals("Vida Salvaje", documental.getTema());
        assertTrue(documental.getInvestigadores().isEmpty());
    }

    @Test
    public void testSetTema() {
        documental.setTema("Oceano");
        assertEquals("Oceano", documental.getTema());
    }

    @Test
    public void testAgregarInvestigador() {
        Investigador inv = new Investigador("David", "Biologo", documental);
        documental.agregarInvestigador(inv);
        assertEquals(1, documental.getInvestigadores().size());
        assertSame(inv, documental.getInvestigadores().get(0));
    }

    @Test
    public void testAgregarMultiplesInvestigadores() {
        documental.agregarInvestigador(new Investigador("A", "Bio", documental));
        documental.agregarInvestigador(new Investigador("B", "Geo", documental));
        assertEquals(2, documental.getInvestigadores().size());
    }

    @Test
    public void testHerencia() {
        assertTrue(documental instanceof uni1a.ContenidoAudiovisual);
    }

    @Test
    public void testMostrarDetallesNoLanzaExcepcion() {
        assertDoesNotThrow(() -> documental.mostrarDetalles());
    }
}
