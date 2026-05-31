package service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import uni1a.ContenidoAudiovisual;
import uni1a.Documental;
import uni1a.Pelicula;
import uni1a.SerieDeTV;
import uni1a.ups.clases.adicionales.Actor;
import uni1a.ups.clases.adicionales.Temporada;
import uni1a.ups.clases.adicionales.Investigador;
import ups.expancion.VideoNeflix;
import ups.expancion.VideoStriming;

/**
 * Servicio de gestión de contenido audiovisual
 * Maneja la lógica de negocio para operaciones CRUD
 */
public class ContenidoService {
    private final List<ContenidoAudiovisual> contenidos;
    private final List<Actor> actores;

    public ContenidoService() {
        this.contenidos = new ArrayList<>();
        this.actores = new ArrayList<>();
    }

    // ==================== CRUD Contenido ====================

    /**
     * Crea y añade una película al sistema
     */
    public void crearPelicula(String titulo, int duracion, String genero, String estudio) {
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
        contenidos.add(pelicula);
    }

    /**
     * Crea y añade una serie de TV al sistema
     */
    public void crearSerie(String titulo, int duracion, String genero, int numTemporadas) {
        SerieDeTV serie = new SerieDeTV(titulo, duracion, genero, numTemporadas);
        contenidos.add(serie);
    }

    /**
     * Crea y añade un documental al sistema
     */
    public void crearDocumental(String titulo, int duracion, String genero, String tema) {
        Documental documental = new Documental(titulo, duracion, genero, tema);
        contenidos.add(documental);
    }

    /**
     * Crea y añade un video Netflix al sistema
     */
    public void crearVideoNeflix(String titulo, int duracion, String genero, boolean esOriginal, String resolucion) {
        VideoNeflix video = new VideoNeflix(titulo, duracion, genero, esOriginal, resolucion);
        contenidos.add(video);
    }

    /**
     * Crea y añade un video Streaming al sistema
     */
    public void crearVideoStriming(String titulo, int duracion, String genero, String plataforma, int visualizaciones) {
        VideoStriming video = new VideoStriming(titulo, duracion, genero, plataforma, visualizaciones);
        contenidos.add(video);
    }

    /**
     * Obtiene todos los contenidos
     */
    public List<ContenidoAudiovisual> obtenerTodosLosContenidos() {
        return new ArrayList<>(contenidos);
    }

    /**
     * Obtiene un contenido por ID
     */
    public ContenidoAudiovisual obtenerContenidoPorId(int id) {
        return contenidos.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca contenidos por título (búsqueda parcial)
     */
    public List<ContenidoAudiovisual> buscarPorTitulo(String titulo) {
        return contenidos.stream()
                .filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Busca contenidos por género
     */
    public List<ContenidoAudiovisual> buscarPorGenero(String genero) {
        return contenidos.stream()
                .filter(c -> c.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    /**
     * Busca contenidos por tipo de contenido
     */
    public List<ContenidoAudiovisual> buscarPorTipo(String tipo) {
        return contenidos.stream()
                .filter(c -> c.getClass().getSimpleName().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un contenido por ID
     */
    public boolean eliminarContenido(int id) {
        return contenidos.removeIf(c -> c.getId() == id);
    }

    /**
     * Actualiza el título de un contenido
     */
    public boolean actualizarTitulo(int id, String nuevoTitulo) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(id);
        if (contenido != null) {
            contenido.setTitulo(nuevoTitulo);
            return true;
        }
        return false;
    }

    /**
     * Actualiza la duración de un contenido
     */
    public boolean actualizarDuracion(int id, int nuevaDuracion) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(id);
        if (contenido != null) {
            contenido.setDuracionEnMinutos(nuevaDuracion);
            return true;
        }
        return false;
    }

    /**
     * Actualiza el género de un contenido
     */
    public boolean actualizarGenero(int id, String nuevoGenero) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(id);
        if (contenido != null) {
            contenido.setGenero(nuevoGenero);
            return true;
        }
        return false;
    }

    // ==================== Manejo de Archivos ====================

    public boolean guardarSistema(String carpetaDestino) {
        try {
            File directorio = new File(carpetaDestino);
            if (!directorio.exists() && !directorio.mkdirs()) {
                return false;
            }

            guardarContenidos(new File(directorio, "contenidos.csv"));
            guardarActores(new File(directorio, "actores.csv"));
            guardarTemporadas(new File(directorio, "temporadas.csv"));
            guardarInvestigadores(new File(directorio, "investigadores.csv"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean cargarSistema(String carpetaOrigen) {
        try {
            File directorio = new File(carpetaOrigen);
            File contenidosFile = new File(directorio, "contenidos.csv");
            File actoresFile = new File(directorio, "actores.csv");
            File temporadasFile = new File(directorio, "temporadas.csv");
            File investigadoresFile = new File(directorio, "investigadores.csv");

            if (!contenidosFile.exists()) {
                return false;
            }

            limpiarSistema();
            Map<Integer, ContenidoAudiovisual> idMap = cargarContenidos(contenidosFile);
            if (idMap == null) {
                limpiarSistema();
                return false;
            }

            if (actoresFile.exists()) {
                cargarActores(actoresFile, idMap);
            }
            if (temporadasFile.exists()) {
                cargarTemporadas(temporadasFile, idMap);
            }
            if (investigadoresFile.exists()) {
                cargarInvestigadores(investigadoresFile, idMap);
            }

            return true;
        } catch (IOException e) {
            limpiarSistema();
            return false;
        }
    }

    private void limpiarSistema() {
        contenidos.clear();
        actores.clear();
        ContenidoAudiovisual.setContar(0);
    }

    private void guardarContenidos(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.println("id,tipo,titulo,duracion,genero,estudio,temporadas,tema,esOriginal,resolucion,plataforma,visualizaciones");
            for (ContenidoAudiovisual contenido : contenidos) {
                String tipo = contenido.getClass().getSimpleName();
                String estudio = "";
                String temporadas = "";
                String tema = "";
                String esOriginal = "";
                String resolucion = "";
                String plataforma = "";
                String visualizaciones = "";

                if (contenido instanceof Pelicula) {
                    estudio = ((Pelicula) contenido).getEstudio();
                } else if (contenido instanceof SerieDeTV) {
                    temporadas = String.valueOf(((SerieDeTV) contenido).getTemporadas());
                } else if (contenido instanceof Documental) {
                    tema = ((Documental) contenido).getTema();
                } else if (contenido instanceof VideoNeflix) {
                    VideoNeflix video = (VideoNeflix) contenido;
                    esOriginal = String.valueOf(video.isEsOriginal());
                    resolucion = video.getResolucion();
                } else if (contenido instanceof VideoStriming) {
                    VideoStriming video = (VideoStriming) contenido;
                    plataforma = video.getPlataforma();
                    visualizaciones = String.valueOf(video.getVisualizaciones());
                }

                writer.println(String.join(",",
                        csvQuote(String.valueOf(contenido.getId())),
                        csvQuote(tipo),
                        csvQuote(contenido.getTitulo()),
                        csvQuote(String.valueOf(contenido.getDuracionEnMinutos())),
                        csvQuote(contenido.getGenero()),
                        csvQuote(estudio),
                        csvQuote(temporadas),
                        csvQuote(tema),
                        csvQuote(esOriginal),
                        csvQuote(resolucion),
                        csvQuote(plataforma),
                        csvQuote(visualizaciones)
                ));
            }
        }
    }

    private void guardarActores(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.println("nombre,apellido,contenidoId,contenidoTipo");
            for (Actor actor : actores) {
                String contenidoId = "";
                String contenidoTipo = "";
                
                // Buscar el contenido al que pertenece el actor (soporta Pelicula, VideoNeflix, VideoStriming)
                ContenidoAudiovisual ref = null;
                for (ContenidoAudiovisual c : contenidos) {
                    if (c instanceof Pelicula && ((Pelicula) c).getActores().contains(actor)) {
                        ref = c;
                        break;
                    } else if (c instanceof VideoNeflix && ((VideoNeflix) c).getActores().contains(actor)) {
                        ref = c;
                        break;
                    } else if (c instanceof VideoStriming && ((VideoStriming) c).getActores().contains(actor)) {
                        ref = c;
                        break;
                    }
                }
                
                if (ref != null) {
                    contenidoId = String.valueOf(ref.getId());
                    contenidoTipo = ref.getClass().getSimpleName();
                } else if (actor.getPelicula() != null) {
                    contenidoId = String.valueOf(actor.getPelicula().getId());
                    contenidoTipo = actor.getPelicula().getClass().getSimpleName();
                }

                writer.println(String.join(",",
                        csvQuote(actor.getNombre()),
                        csvQuote(actor.getApellido()),
                        csvQuote(contenidoId),
                        csvQuote(contenidoTipo)
                ));
            }
        }
    }

    private void guardarTemporadas(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.println("numero,episodios,contenidoId,contenidoTipo");
            for (ContenidoAudiovisual contenido : contenidos) {
                if (contenido instanceof SerieDeTV) {
                    SerieDeTV serie = (SerieDeTV) contenido;
                    for (Temporada temporada : serie.getListaTemporadas()) {
                        writer.println(String.join(",",
                                csvQuote(String.valueOf(temporada.getNumero())),
                                csvQuote(String.valueOf(temporada.getEpisodios())),
                                csvQuote(String.valueOf(contenido.getId())),
                                csvQuote(contenido.getClass().getSimpleName())
                        ));
                    }
                } else if (contenido instanceof VideoStriming) {
                    VideoStriming video = (VideoStriming) contenido;
                    for (Temporada temporada : video.getTemporadas()) {
                        writer.println(String.join(",",
                                csvQuote(String.valueOf(temporada.getNumero())),
                                csvQuote(String.valueOf(temporada.getEpisodios())),
                                csvQuote(String.valueOf(contenido.getId())),
                                csvQuote(contenido.getClass().getSimpleName())
                        ));
                    }
                }
            }
        }
    }

    private void guardarInvestigadores(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.println("nombre,especialidad,contenidoId");
            for (ContenidoAudiovisual contenido : contenidos) {
                if (contenido instanceof Documental) {
                    Documental documental = (Documental) contenido;
                    for (var investigador : documental.getInvestigadores()) {
                        writer.println(String.join(",",
                                csvQuote(investigador.getNombre()),
                                csvQuote(investigador.getEspecialidad()),
                                csvQuote(String.valueOf(contenido.getId()))
                        ));
                    }
                }
            }
        }
    }

    private Map<Integer, ContenidoAudiovisual> cargarContenidos(File file) throws IOException {
        Map<Integer, ContenidoAudiovisual> idMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] tokens = splitCsvLine(line);
                if (tokens.length < 12) {
                    continue;
                }
                int id = Integer.parseInt(tokens[0]);
                String tipo = tokens[1];
                String titulo = tokens[2];
                int duracion = Integer.parseInt(tokens[3]);
                String genero = tokens[4];
                String estudio = tokens[5];
                String temporadas = tokens[6];
                String tema = tokens[7];
                boolean esOriginal = Boolean.parseBoolean(tokens[8]);
                String resolucion = tokens[9];
                String plataforma = tokens[10];
                int visualizaciones = tokens[11].isBlank() ? 0 : Integer.parseInt(tokens[11]);

                ContenidoAudiovisual contenido;
                switch (tipo) {
                    case "Pelicula":
                        contenido = new Pelicula(titulo, duracion, genero, estudio);
                        break;
                    case "SerieDeTV":
                        contenido = new SerieDeTV(titulo, duracion, genero, temporadas.isBlank() ? 0 : Integer.parseInt(temporadas));
                        break;
                    case "Documental":
                        contenido = new Documental(titulo, duracion, genero, tema);
                        break;
                    case "VideoNeflix":
                        contenido = new VideoNeflix(titulo, duracion, genero, esOriginal, resolucion);
                        break;
                    case "VideoStriming":
                        contenido = new VideoStriming(titulo, duracion, genero, plataforma, visualizaciones);
                        break;
                    default:
                        continue;
                }

                contenido.setId(id);
                if (id >= ContenidoAudiovisual.getContar()) {
                    ContenidoAudiovisual.setContar(id + 1);
                }

                contenidos.add(contenido);
                idMap.put(id, contenido);
            }
        }
        return idMap;
    }

    private void cargarActores(File file, Map<Integer, ContenidoAudiovisual> idMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] tokens = splitCsvLine(line);
                if (tokens.length < 4) {
                    continue;
                }
                String nombre = tokens[0];
                String apellido = tokens[1];
                String contenidoId = tokens[2];
                ContenidoAudiovisual contenido = null;
                if (!contenidoId.isBlank()) {
                    contenido = idMap.get(Integer.parseInt(contenidoId));
                }

                Actor actor = new Actor(nombre, apellido, null);
                actores.add(actor);

                if (contenido != null) {
                    if (contenido instanceof Pelicula) {
                        actor.setPelicula((Pelicula) contenido);
                        ((Pelicula) contenido).agregarActor(actor);
                    } else if (contenido instanceof VideoNeflix) {
                        ((VideoNeflix) contenido).agregarActor(actor);
                    } else if (contenido instanceof VideoStriming) {
                        ((VideoStriming) contenido).agregarActor(actor);
                    }
                }
            }
        }
    }

    private void cargarTemporadas(File file, Map<Integer, ContenidoAudiovisual> idMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] tokens = splitCsvLine(line);
                if (tokens.length < 4) {
                    continue;
                }
                int numero = Integer.parseInt(tokens[0]);
                int episodios = Integer.parseInt(tokens[1]);
                int contenidoId = Integer.parseInt(tokens[2]);
                ContenidoAudiovisual contenido = idMap.get(contenidoId);
                if (contenido == null) {
                    continue;
                }

                if (contenido instanceof SerieDeTV) {
                    SerieDeTV serie = (SerieDeTV) contenido;
                    Temporada temporada = new Temporada(numero, episodios, serie);
                    serie.agregarTemporada(temporada);
                } else if (contenido instanceof VideoStriming) {
                    Temporada temporada = new Temporada(numero, episodios, null);
                    ((VideoStriming) contenido).agregarTemporada(temporada);
                }
            }
        }
    }

    private void cargarInvestigadores(File file, Map<Integer, ContenidoAudiovisual> idMap) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] tokens = splitCsvLine(line);
                if (tokens.length < 3) {
                    continue;
                }
                String nombre = tokens[0];
                String especialidad = tokens[1];
                int contenidoId = Integer.parseInt(tokens[2]);
                ContenidoAudiovisual contenido = idMap.get(contenidoId);
                if (contenido instanceof Documental) {
                    Investigador investigador = new Investigador(nombre, especialidad, (Documental) contenido);
                    ((Documental) contenido).agregarInvestigador(investigador);
                }
            }
        }
    }

    private static String csvQuote(String value) {
        if (value == null) {
            return "";
        }
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }

    private static String[] splitCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                tokens.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        tokens.add(current.toString());
        return tokens.toArray(new String[0]);
    }

    // ==================== Gestión de Actores ====================

    /**
     * Crea y registra un actor
     */
    public void crearActor(String nombre, String apellido) {
        Actor actor = new Actor(nombre, apellido, null);
        actores.add(actor);
    }

    /**
     * Obtiene todos los actores registrados
     */
    public List<Actor> obtenerTodosLosActores() {
        return new ArrayList<>(actores);
    }

    /**
     * Busca un actor por nombre y apellido
     */
    public Actor buscarActor(String nombre, String apellido) {
        return actores.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre) && a.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .orElse(null);
    }

    /**
     * Añade un actor a una película
     */
    public boolean agregarActorAPelicula(int idPelicula, Actor actor) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(idPelicula);
        if (contenido instanceof Pelicula) {
            ((Pelicula) contenido).agregarActor(actor);
            return true;
        }
        return false;
    }

    // ==================== Gestión de Temporadas ====================

    /**
     * Añade una temporada a una serie
     */
    public boolean agregarTemporadaASerie(int idSerie, int numeroTemporada, int episodios) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(idSerie);
        if (contenido instanceof SerieDeTV) {
            SerieDeTV serie = (SerieDeTV) contenido;
            Temporada temporada = new Temporada(numeroTemporada, episodios, serie);
            serie.agregarTemporada(temporada);
            return true;
        }
        return false;
    }

    /**
     * Obtiene información de una serie
     */
    public SerieDeTV obtenerSerie(int id) {
        ContenidoAudiovisual contenido = obtenerContenidoPorId(id);
        if (contenido instanceof SerieDeTV) {
            return (SerieDeTV) contenido;
        }
        return null;
    }

    /**
     * Total de contenidos en el sistema
     */
    public int totalContenidos() {
        return contenidos.size();
    }

    /**
     * Total de actores registrados
     */
    public int totalActores() {
        return actores.size();
    }

    /**
     * Obtiene estadísticas del sistema
     */
    public String obtenerEstadisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== ESTADÍSTICAS DEL SISTEMA ==========\n");
        sb.append("Total de contenidos: ").append(totalContenidos()).append("\n");
        sb.append("Total de actores: ").append(totalActores()).append("\n");
        
        long peliculas = contenidos.stream().filter(c -> c instanceof Pelicula).count();
        long series = contenidos.stream().filter(c -> c instanceof SerieDeTV).count();
        long documentales = contenidos.stream().filter(c -> c instanceof Documental).count();
        long videosNetflix = contenidos.stream().filter(c -> c instanceof VideoNeflix).count();
        long videosStreaming = contenidos.stream().filter(c -> c instanceof VideoStriming).count();
        
        sb.append("\nDistribución por tipo:\n");
        sb.append("  - Películas: ").append(peliculas).append("\n");
        sb.append("  - Series: ").append(series).append("\n");
        sb.append("  - Documentales: ").append(documentales).append("\n");
        sb.append("  - Videos Netflix: ").append(videosNetflix).append("\n");
        sb.append("  - Videos Streaming: ").append(videosStreaming).append("\n");
        sb.append("=============================================\n");
        
        return sb.toString();
    }
}
