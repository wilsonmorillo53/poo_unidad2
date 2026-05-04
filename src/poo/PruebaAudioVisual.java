package poo;

import uni1a.*;
import uni1a.ups.clases.adicionales.Actor;
import uni1a.ups.clases.adicionales.Temporada;
import uni1a.ups.clases.adicionales.Investigador;
import ups.expancion.VideoNeflix;
import ups.expancion.VideoStriming;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión Audiovisual ===");
        System.out.println("Creando objetos y estableciendo relaciones...\n");

        // 1. Instancias originales
        Pelicula pelicula = new Pelicula("Avatar", 125, "Accion", "20th Century Studios");
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasy", 8);
        Documental documental = new Documental("Cosmos", 45, "Science", "Astronomy");

        // 2. Crear relaciones (Agregaciones y Composiciones)
        Actor actor1 = new Actor("Sam", "Worthington", pelicula);
        Actor actor2 = new Actor("Zoe", "Saldana", pelicula);
        pelicula.agregarActor(actor1);
        pelicula.agregarActor(actor2);

        Temporada temp1 = new Temporada(1, 10, serie);
        Temporada temp2 = new Temporada(2, 10, serie);
        serie.agregarTemporada(temp1);
        serie.agregarTemporada(temp2);

        Investigador inv1 = new Investigador("Neil", "deGrasse Tyson", documental);
        documental.agregarInvestigador(inv1);

        // 3. Instancias de las nuevas clases (Paquete expancion)
        VideoNeflix netflixOriginal = new VideoNeflix("Stranger Things", 50, "Sci-Fi", true, "4K UHD");
        Actor actor3 = new Actor("Millie", "Bobby Brown", null); // Actriz en Netflix
        netflixOriginal.agregarActor(actor3);

        VideoStriming amazonPrime = new VideoStriming("The Boys", 60, "Action", "Amazon Prime", 1500000);
        Actor actor4 = new Actor("Karl", "Urban", null);
        Temporada temp3 = new Temporada(1, 8, null);
        amazonPrime.agregarActor(actor4);
        amazonPrime.agregarTemporada(temp3);

        // 4. Arreglo polimórfico
        ContenidoAudiovisual[] contenidos = new ContenidoAudiovisual[5];
        contenidos[0] = pelicula;
        contenidos[1] = serie;
        contenidos[2] = documental;
        contenidos[3] = netflixOriginal;
        contenidos[4] = amazonPrime;

        // 5. Mostrar los detalles
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
            System.out.println("---------------------------------------------------");
        }
    }
}