package uni1a;

import java.util.ArrayList;
import java.util.List;
import uni1a.ups.clases.adicionales.Investigador;

public class Documental extends ContenidoAudiovisual {
    private String tema;
    private final List<Investigador> investigadores;

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
        super(titulo, duracionEnMinutos, genero);
        this.tema = tema;
        this.investigadores = new ArrayList<>();
    }

    public void agregarInvestigador(Investigador investigador) {
        if (investigador != null) {
            this.investigadores.add(investigador);
        }
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
        System.out.println("Detalles del documental:");
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