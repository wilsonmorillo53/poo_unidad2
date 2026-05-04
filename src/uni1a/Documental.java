/**
 * Class Documental
 */
package uni1a;

// Subclase Documental que extiende de ContenidoAudiovisual
import java.util.ArrayList;
import java.util.List;
import uni1a.ups.clases.adicionales.Investigador;

public class Documental extends ContenidoAudiovisual {
    private String tema;
    private final List<Investigador> investigadores; // Agregación: El Documental tiene investigadores

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
        super(titulo, duracionEnMinutos, genero);
        this.tema = tema;
        this.investigadores = new ArrayList<>();
    }

    public void agregarInvestigador(Investigador investigador) {
        this.investigadores.add(investigador);
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }


    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Tema: " + this.tema);
        System.out.println("Investigadores:");
        for (Investigador inv : investigadores) {
            System.out.println(" - " + inv.getNombre() + " (" + inv.getEspecialidad() + ")");
        }
        System.out.println();
    }
}