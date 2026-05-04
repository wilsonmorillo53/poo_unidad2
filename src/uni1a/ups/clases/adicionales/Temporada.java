package uni1a.ups.clases.adicionales;

import uni1a.SerieDeTV;

public class Temporada {
    private int numero;
    private int episodios;
    private SerieDeTV serie;

    public Temporada(int numero, int episodios, SerieDeTV serie) {
        this.numero = numero;
        this.episodios = episodios;
        this.serie = serie;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public SerieDeTV getSerie() {
        return serie;
    }

    public void setSerie(SerieDeTV serie) {
        this.serie = serie;
    }
}
