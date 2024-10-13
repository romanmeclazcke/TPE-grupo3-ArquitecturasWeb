package com.example.entregable3.DTO;
import com.example.entregable3.Model.Inscripcion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CarreraDTO {
    @Getter
    private int idCarrera;
    @Setter @Getter
    private String nombre;
    private List<Inscripcion> inscriptos;

    public CarreraDTO(int idCarrera, String nombre) {
        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.inscriptos = new ArrayList<Inscripcion>();
    }

    @Override
    public String toString() {
        return "idCarrera=" + idCarrera +
                ", nombre='" + nombre + '\'' +
                ", inscriptos=" + inscriptos + '\n';
    }
}