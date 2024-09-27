package Repository;

import DTO.CarreraConNumeroInscriptosDTO;
import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import Entities.Carrera;
import Repository.CarreraRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;
    private static CarreraRepositoryImpl instance;

    private CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public static Repository.CarreraRepositoryImpl getInstancia(EntityManager em) {
        if (instance == null) {
            instance = new CarreraRepositoryImpl(em);
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

    @Override
    public List<CarreraConNumeroInscriptosDTO> getReporteCarreras() {
        String query = "SELECT new DTO.CarreraConNumeroInscriptosDTO( " +
                "c.nombre, " +
                "COUNT(i.fecha_inscripcion), " +
                "EXTRACT(YEAR FROM i.fecha_inscripcion), " +
                "COUNT(i.fecha_graduacion), " +
                "EXTRACT(YEAR FROM i.fecha_graduacion) ) " +
                "FROM Carrera c " +
                "LEFT JOIN c.inscriptos i " +
                "GROUP BY c.nombre, EXTRACT(YEAR FROM i.fecha_inscripcion), EXTRACT(YEAR FROM i.fecha_graduacion) " +
                "ORDER BY c.nombre, EXTRACT(YEAR FROM i.fecha_inscripcion), EXTRACT(YEAR FROM i.fecha_graduacion)";
        List<CarreraConNumeroInscriptosDTO> reporte =
                em.createQuery(query , CarreraConNumeroInscriptosDTO.class).getResultList();

        return reporte;
    }


}