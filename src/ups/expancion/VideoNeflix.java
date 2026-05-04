package ups.expancion;

import java.util.ArrayList;
import java.util.List;
import uni1a.ContenidoAudiovisual;
import uni1a.ups.clases.adicionales.Actor;

public class VideoNeflix extends ContenidoAudiovisual {
    private boolean esOriginal;
    private String resolucion;
    private final List<Actor> actores; // Agregación: El Video de Netflix tiene actores

    public VideoNeflix(String titulo, int duracionEnMinutos, String genero, boolean esOriginal, String resolucion) {
        super(titulo, duracionEnMinutos, genero);
        this.esOriginal = esOriginal;
        this.resolucion = resolucion;
        this.actores = new ArrayList<>();
    }

    public void agregarActor(Actor actor) {
        this.actores.add(actor);
    }

    public List<Actor> getActores() {
        return actores;
    }


    public boolean isEsOriginal() {
        return esOriginal;
    }

    public void setEsOriginal(boolean esOriginal) {
        this.esOriginal = esOriginal;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Video Netflix:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("¿Original?: " + (esOriginal ? "Sí" : "No"));
        System.out.println("Resolución: " + resolucion);
        System.out.println("Actores:");
        for (Actor actor : actores) {
            System.out.println(" - " + actor.getNombre() + " " + actor.getApellido());
        }
        System.out.println();
    }
}
