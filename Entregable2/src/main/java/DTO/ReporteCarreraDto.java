package DTO;

public class ReporteCarreraDto {
    private String nombre;
    private Long numeroInscriptos;
    private Integer anioInscripciones;
    private Long egresadosPorAnio;
    private Integer anioEgresados;

    public ReporteCarreraDto(String nombre, Long numeroInscriptos, Integer anioInscripciones , Long egresadosPorAnio, Integer anioEgresados) {
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
        return "Nombre: " + nombre +
                ", #inscriptos: " + numeroInscriptos +
                ", año inscripciones: " + anioInscripciones +
                ", egresados del año: " + egresadosPorAnio +
                ", año egresados: " + anioEgresados + '\n';
    }
}
