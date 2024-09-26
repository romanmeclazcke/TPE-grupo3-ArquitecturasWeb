package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @MapsId
    @ManyToOne
    @JoinColumn
    private Estudiante estudiante;

    @MapsId
    @ManyToOne
    @JoinColumn
    private Carrera carrera;

    @Column
    private Date antiguedad;
    private boolean graduado;

    public Inscripcion() {}

    public Inscripcion(Estudiante estudiante, Carrera carrera, Date antiguedad, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public int getId() {
        return id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getAntiguedad() {
        return antiguedad;
    }

    public  boolean getGraduado() {
        return graduado;
    }
}
