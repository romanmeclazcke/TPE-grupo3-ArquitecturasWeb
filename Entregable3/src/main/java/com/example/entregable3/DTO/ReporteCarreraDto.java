package com.example.entregable3.DTO;

public class ReporteCarreraDto {
    private String nombre;
    private Long numeroInscriptos;
    private Long egresadosPorAnio;
    private Integer anioLectivo;

    public ReporteCarreraDto(String nombre, Long numeroInscriptos , Long egresadosPorAnio, Integer anioLectivo) {
        this.nombre = nombre;
        this.numeroInscriptos = numeroInscriptos;
        this.egresadosPorAnio = egresadosPorAnio;
        this.anioLectivo = anioLectivo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNumeroInscriptos() {
        return numeroInscriptos;
    }

    public void setNumeroInscriptos(Long numeroInscriptos) {
        this.numeroInscriptos = numeroInscriptos;
    }

    public Long getEgresadosPorAnio() {
        return egresadosPorAnio;
    }

    public void setEgresadosPorAnio(Long egresadosPorAnio) {
        this.egresadosPorAnio = egresadosPorAnio;
    }

    public Integer getAnioLectivo() {
        return anioLectivo;
    }

    public void setAnioLectivo(Integer anioLectivo) {
        this.anioLectivo = anioLectivo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", #inscriptos: " + numeroInscriptos +
                ", egresados del año: " + egresadosPorAnio +
                ", año lectivo: " + anioLectivo + '\n';
    }
}