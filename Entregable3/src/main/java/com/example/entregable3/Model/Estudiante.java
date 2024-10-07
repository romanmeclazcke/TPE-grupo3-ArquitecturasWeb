package com.example.entregable3.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer num_libreta;
    @Column
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private int documento;
    private String ciudad_residencia;

    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones;

    public Estudiante() {
        super();
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    public Estudiante( String nombre, String apellido, int edad, char genero, int documento, String ciudad_residencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.documento = documento;
        this.ciudad_residencia = ciudad_residencia;
    }



}
