package com.example.entregable3.DTO;

import Entities.Inscripcion;

import java.util.ArrayList;
import java.util.List;

public class CarreraDTO {
    private int idCarrera;
    private String nombre;
    private List<Inscripcion> inscriptos;

    public CarreraDTO(int idCarrera, String nombre) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.inscriptos = new ArrayList<Inscripcion>();
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
                ", inscriptos=" + inscriptos + '\n';
    }
}
