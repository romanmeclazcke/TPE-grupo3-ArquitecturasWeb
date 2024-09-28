package DTO;

import Entities.Carrera;
import Entities.Estudiante;
import java.util.Date;

public class InscripcionDTO {
    private int id;
    private Estudiante estudiante;
    private Carrera carrera;
    private Date fecha_inscripcion;
    private Date fecha_graduacion;

    public InscripcionDTO(Estudiante estudiante, Carrera carrera, Date fecha_inscripcion, Date fecha_graduacion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_graduacion = fecha_graduacion;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public Date getFecha_graduacion() {
        return fecha_graduacion;
    }

    public void setFecha_graduacion(Date fecha_graduacion) {
        this.fecha_graduacion = fecha_graduacion;
    }

    public boolean isGraduado() {
        if (this.fecha_graduacion == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", estudiante=" + estudiante +
                ", carrera=" + carrera +
                ", fecha_inscripcion=" + fecha_inscripcion +
                ", fecha_graduacion=" + fecha_graduacion + '\n';
    }
}
