package Repository;

import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import Entities.Carrera;
import Repository.CarreraRepository;

import javax.persistence.EntityManager;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;
    private static CarreraRepositoryImpl instance;

    private CarreraRepositoryImpl() {

    }

    public static Repository.CarreraRepositoryImpl getInstancia(EntityManager em) {
        if (instance == null) {
            instance = new CarreraRepositoryImpl();
        }
        return instance;
    }

    @Override
    public CarreraDTO crearCarrera(Carrera c) {
        this.em.getTransaction().begin();
        if (c == null) { //Si el estudiante no tiene ID, es un nuevo estudiante
            this.em.persist(c);
        } else { //Si el estudiante ya tiene ID, actualiza los datos con merge
            c = this.em.merge(c);
        }
        this.em.getTransaction().commit();

        return new CarreraDTO(c.getIdCarrera(),c.getNombre());
    }
}