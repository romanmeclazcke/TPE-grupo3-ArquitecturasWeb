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

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public void setNumInscriptos(long numInscriptos) {
        this.numInscriptos = numInscriptos;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public long getNumInscriptos() {
        return numInscriptos;
    }

    @Override
    public String toString() {
        return "idCarrera=" + idCarrera +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                ", numInscriptos=" + numInscriptos + '\n';
    }
}
