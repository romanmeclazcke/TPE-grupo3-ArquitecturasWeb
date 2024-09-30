package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarrera;
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

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
