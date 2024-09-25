package Repository;

import Entities.Estudiante;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private EntityManager em;

    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        if (estudiante.getNum_libreta() == null) {
            // Si el estudiante no tiene un ID, es un nuevo estudiante
            em.persist(estudiante);
        } else {
            // Si el estudiante ya tiene un ID, utiliza merge para actualizar o adjuntar
            estudiante = em.merge(estudiante);
        }
        return estudiante;
    }

    @Override
    public List<Estudiante> getEstudiantesByEdad() {
        String query = "SELECT e FROM Estudiante e ORDER BY edad";
        List<Estudiante> estudiantes = em.createQuery(query, Estudiante.class).getResultList();
        return estudiantes;
    }

    @Override
    public Estudiante getEstudianteByLibreta(int numero_libreta) {
        return em.find(Estudiante.class, numero_libreta);
    }

    @Override
    public List<Estudiante> getEstudianteByGenero(char genero) {
        String query = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        List<Estudiante> estudiantes = em.createQuery(query, Estudiante.class).setParameter("genero", genero).getResultList();
        return estudiantes;
    }
}
