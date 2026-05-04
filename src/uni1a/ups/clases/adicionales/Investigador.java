package uni1a.ups.clases.adicionales;

import uni1a.Documental;

public class Investigador {
    private String nombre;
    private String especialidad;
    private Documental documental;

    public Investigador(String nombre, String especialidad, Documental documental) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.documental = documental;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Documental getDocumental() {
        return documental;
    }

    public void setDocumental(Documental documental) {
        this.documental = documental;
    }
}
