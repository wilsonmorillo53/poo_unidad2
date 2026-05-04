/**
 * Class SerieDeTV
 */
package uni1a;

// Subclase SerieDeTV que extiende de ContenidoAudiovisual
import java.util.ArrayList;
import java.util.List;
import uni1a.ups.clases.adicionales.Temporada;

public class SerieDeTV extends ContenidoAudiovisual {
    private int temporadas;
    private final List<Temporada> listaTemporadas; // Composición: La SerieDeTV tiene temporadas

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int temporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = temporadas;
        this.listaTemporadas = new ArrayList<>();
    }

    public void agregarTemporada(Temporada temporada) {
        this.listaTemporadas.add(temporada);
    }

    public List<Temporada> getListaTemporadas() {
        return listaTemporadas;
    }


    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Temporadas: " + this.temporadas);
        System.out.println("Detalle de Temporadas:");
        for (Temporada t : listaTemporadas) {
            System.out.println(" - Temporada " + t.getNumero() + " (" + t.getEpisodios() + " episodios)");
        }
        System.out.println();
    }
}