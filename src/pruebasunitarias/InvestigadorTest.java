package pruebasunitarias;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uni1a.Documental;
import uni1a.ups.clases.adicionales.Investigador;

public class InvestigadorTest {

    @Test
    public void testConstructor() {
        Documental doc = new Documental("Doc", 60, "Ciencia", "Biologia");
        Investigador inv = new Investigador("Maria", "Biologa", doc);
        assertEquals("Maria", inv.getNombre());
        assertEquals("Biologa", inv.getEspecialidad());
        assertSame(doc, inv.getDocumental());
    }

    @Test
    public void testSetNombre() {
        Documental doc = new Documental("Doc", 60, "Ciencia", "Biologia");
        Investigador inv = new Investigador("A", "B", doc);
        inv.setNombre("Pedro");
        assertEquals("Pedro", inv.getNombre());
    }

    @Test
    public void testSetEspecialidad() {
        Documental doc = new Documental("Doc", 60, "Ciencia", "Biologia");
        Investigador inv = new Investigador("A", "B", doc);
        inv.setEspecialidad("Fisica");
        assertEquals("Fisica", inv.getEspecialidad());
    }

    @Test
    public void testSetDocumental() {
        Documental doc1 = new Documental("Doc1", 60, "Ciencia", "Bio");
        Documental doc2 = new Documental("Doc2", 45, "Historia", "Guerra");
        Investigador inv = new Investigador("Luis", "Historiador", doc1);
        inv.setDocumental(doc2);
        assertSame(doc2, inv.getDocumental());
    }
}
