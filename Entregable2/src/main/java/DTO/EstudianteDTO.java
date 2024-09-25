package DTO;

import java.io.Serializable;

public class EstudianteDTO implements Serializable { //PARA QUÉ?
    private Integer num_libreta;
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

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, String ciudad_residencia) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad_residencia = ciudad_residencia;
    }

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, int edad) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public EstudianteDTO(Integer num_libreta, String nombre, String apellido, char genero) {
        this.num_libreta = num_libreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    public Integer getNum_libreta() {
        return num_libreta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCiudad_residencia() {
        return ciudad_residencia;
    }

    public void setCiudad_residencia(String ciudad_residencia) {
        this.ciudad_residencia = ciudad_residencia;
    }

    @Override
    public String toString() {
        return "num_libreta=" + num_libreta +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero=" + genero +
                ", documento=" + documento +
                ", ciudad_residencia='" + ciudad_residencia + '\n';
    }
}
