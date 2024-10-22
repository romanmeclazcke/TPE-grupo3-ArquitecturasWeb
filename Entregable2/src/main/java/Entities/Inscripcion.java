package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="inscripcion")
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

    public Date getFecha_graduacion() {
        return fecha_graduacion;
    }

    public void setFecha_graduacion(Date fecha_graduacion) {
        this.fecha_graduacion = fecha_graduacion;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public boolean isGraduado() {
        if (this.fecha_graduacion == null) {
            return false;
        }

        return true;
    }
}
