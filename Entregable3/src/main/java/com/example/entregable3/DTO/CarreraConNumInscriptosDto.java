package com.example.entregable3.DTO;

public class CarreraConNumInscriptosDto {
    private int idCarrera;
    private String nombreCarrera;
    private long numInscriptos;

    public CarreraConNumInscriptosDto(int idCarrera, String nombreCarrera, long numInscriptos) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.numInscriptos = numInscriptos;

    }


    @Override
    public String toString() {
        return "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", numInscriptos=" + numInscriptos + '\n';
    }
}
