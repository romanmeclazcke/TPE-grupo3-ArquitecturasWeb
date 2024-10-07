package com.example.entregable3.DTO;

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
        return "CarreraConNumeroInscriptosDTO{" +
                ", nombre='" + nombre + '\'' +
                ", numeroInscriptos=" + numeroInscriptos +
                ", anioInscripciones=" + anioInscripciones +
                ", egresadosPorAnio=" + egresadosPorAnio +
                ", anioEgresados=" + anioEgresados +
                '}';
    }
}
