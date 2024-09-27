package DTO;
public class CarreraConNumeroInscriptosDTO {
    private String nombre;
    private Long numeroInscriptos;
    private Integer anioInscripciones;
    private Long egresadosPorAnio;
    private Integer anioEgresados;

    public CarreraConNumeroInscriptosDTO(String nombre, Long numeroInscriptos, Integer anioInscripciones , Long egresadosPorAnio, Integer anioEgresados) {
        this.nombre = nombre;
        this.numeroInscriptos = numeroInscriptos;
        this.anioInscripciones = anioInscripciones;
        this.egresadosPorAnio = egresadosPorAnio;
        this.anioEgresados = anioEgresados;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CarreraConNumeroInscriptosDTO{" +
                ", nombre='" + nombre + '\'' +
                ", numeroInscriptos=" + numeroInscriptos +
                ", anioInscripciones=" + anioInscripciones +
                ", egresadosPorAnio=" + egresadosPorAnio +
                ", anioEgresados=" + anioEgresados +
                '}';
    }
}
