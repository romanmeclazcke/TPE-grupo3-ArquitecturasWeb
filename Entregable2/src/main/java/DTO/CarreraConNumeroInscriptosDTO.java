package DTO;
public class CarreraConNumeroInscriptosDTO {
    private int idCarrera;
    private String nombre;
    private Long numeroInscriptos;

    public CarreraConNumeroInscriptosDTO(int idCarrera, String nombre, Long numeroInscriptos) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.numeroInscriptos = numeroInscriptos;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", numero inscriptos=" + numeroInscriptos + '\n';
    }
}
