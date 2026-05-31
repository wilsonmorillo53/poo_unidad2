package uni1a;

public abstract class ContenidoAudiovisual {
    private static int contar = 0;
    private String titulo;
    private int duracionEnMinutos;
    private String genero;
    private int id;

    public ContenidoAudiovisual(String titulo, int duracionEnMinutos, String genero) {
        this.id = contar++;
        this.titulo = titulo;
        this.duracionEnMinutos = duracionEnMinutos;
        this.genero = genero;
    }

    // Getter y Setter para el campo 'titulo'
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter y Setter para el campo 'duracionEnMinutos'
    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    // Getter y Setter para el campo 'genero'
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Getter para el campo 'id' (no se proporciona el Setter ya que 'id' se asigna en el constructor y parece ser inmutable)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContar() {
        return contar;
    }

    public static void setContar(int valor) {
        contar = valor;
    }
    
    public abstract void mostrarDetalles();
}