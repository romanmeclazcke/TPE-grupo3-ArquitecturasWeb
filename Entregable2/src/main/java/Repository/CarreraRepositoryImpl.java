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
    public CarreraDTO createCarrera(Carrera c) {
        this.em.getTransaction().begin();
        if (c == null) {
            this.em.persist(c);
        } else {
            c = this.em.merge(c);
        }
        this.em.getTransaction().commit();

        return new CarreraDTO(c.getIdCarrera(),c.getNombre());
    }
}