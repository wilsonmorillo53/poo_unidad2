package ups.expancion;

import java.util.ArrayList;
import java.util.List;
import uni1a.ContenidoAudiovisual;
import uni1a.ups.clases.adicionales.Actor;
import uni1a.ups.clases.adicionales.Temporada;

public class VideoStriming extends ContenidoAudiovisual {
    private String plataforma;
    private int visualizaciones;
    private final List<Actor> actores; // Agregación
    private final List<Temporada> temporadas; // Composición

    public VideoStriming(String titulo, int duracionEnMinutos, String genero, String plataforma, int visualizaciones) {
        super(titulo, duracionEnMinutos, genero);
        this.plataforma = plataforma;
        this.visualizaciones = visualizaciones;
        this.actores = new ArrayList<>();
        this.temporadas = new ArrayList<>();
    }

    public void agregarActor(Actor actor) {
        this.actores.add(actor);
    }

    public void agregarTemporada(Temporada temporada) {
        this.temporadas.add(temporada);
    }

    public List<Actor> getActores() {
        return actores;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }


    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de Video Streaming:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Visualizaciones: " + visualizaciones);
        System.out.println("Actores:");
        for (Actor actor : actores) {
            System.out.println(" - " + actor.getNombre() + " " + actor.getApellido());
        }
        System.out.println("Temporadas:");
        for (Temporada t : temporadas) {
            System.out.println(" - Temporada " + t.getNumero() + " (" + t.getEpisodios() + " episodios)");
        }
        System.out.println();
    }
}
