/**
 * Class Pelicula
 */
package uni1a;

// Subclase Pelicula que extiende de ContenidoAudiovisual
import java.util.ArrayList;
import java.util.List;
import uni1a.ups.clases.adicionales.Actor;

public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private final List<Actor> actores; // Agregación: La película tiene actores


    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio;
        this.actores = new ArrayList<>();
    }

    public void agregarActor(Actor actor) {
        this.actores.add(actor);
    }

    public List<Actor> getActores() {
        return actores;
    }


    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio: " + estudio);
        System.out.println("Actores:");
        for (Actor actor : actores) {
            System.out.println(" - " + actor.getNombre() + " " + actor.getApellido());
        }
        System.out.println();
    }
}