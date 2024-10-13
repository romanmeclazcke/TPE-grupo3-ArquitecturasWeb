package com.example.entregable3.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class EstudianteDTO {
    @Getter
    private Integer num_libreta;
    @Getter @Setter
    private String nombre;
    private String apellido;
    private int edad;
    private char genero;
    private int documento;
    private String ciudad_residencia;

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, int edad, char genero, int documento, String ciudad_residencia) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.documento = documento;
        this.ciudad_residencia = ciudad_residencia;
    }

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, int edad) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, String ciudad_residencia) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad_residencia = ciudad_residencia;
    }

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, char genero) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "num_libreta=" + num_libreta +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero=" + genero +
                ", documento=" + documento +
                ", ciudad_residencia='" + ciudad_residencia + '\'' +
                '}';
    }
}
