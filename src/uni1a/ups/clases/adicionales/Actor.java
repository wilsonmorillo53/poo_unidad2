package uni1a.ups.clases.adicionales;

import uni1a.Pelicula;

public class Actor {
    private String nombre;
    private String apellido;
    private Pelicula pelicula;

    public Actor(String nombre, String apellido, Pelicula pelicula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pelicula = pelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
