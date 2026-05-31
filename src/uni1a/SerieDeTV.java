package uni1a;

import java.util.ArrayList;
import java.util.List;
import uni1a.ups.clases.adicionales.Temporada;

public class SerieDeTV extends ContenidoAudiovisual {
    private int numeroDeTemporadas;
    private final List<Temporada> temporadas;

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int numeroDeTemporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.numeroDeTemporadas = numeroDeTemporadas;
        this.temporadas = new ArrayList<>();
    }

    public void agregarTemporada(Temporada temporada) {
        if (temporada != null) {
            this.temporadas.add(temporada);
        }
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public int getNumeroDeTemporadas() {
        return numeroDeTemporadas;
    }

    public void setNumeroDeTemporadas(int numeroDeTemporadas) {
        this.numeroDeTemporadas = numeroDeTemporadas;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la serie:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Temporadas: " + this.numeroDeTemporadas);
        System.out.println("Detalle de Temporadas:");
        for (Temporada t : temporadas) {
            System.out.println(" - Temporada " + t.getNumero() + " (" + t.getEpisodios() + " episodios)");
        }
        System.out.println();
    }
}