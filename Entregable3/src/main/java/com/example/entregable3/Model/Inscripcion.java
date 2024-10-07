package com.example.entregable3.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Estudiante estudiante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Carrera carrera;;


    @Column
    private Date fecha_inscripcion;
    @Column(nullable = true)
    private Date fecha_graduacion;

    public Inscripcion() {}

    public Inscripcion(Estudiante estudiante, Carrera carrera, Date fecha_graduacion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fecha_inscripcion = new Date();
        this.fecha_graduacion = fecha_graduacion;
    }

   
}
