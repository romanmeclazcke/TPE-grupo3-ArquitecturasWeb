package com.example.entregable3.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCarrera;
    @Column
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscriptos;

    public Carrera() {
        super();
        this.inscriptos = new ArrayList<Inscripcion>();
    }

    public Carrera(String nombre) {
        this.nombre = nombre;
    }
}
