package Repository;

import DTO.ReporteCarreraDto;
import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import DTO.ReporteCarreraDto;
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
        if (c.getIdCarrera() == null) {
            this.em.persist(c);
        } else {
            c = this.em.merge(c);
        }
        this.em.getTransaction().commit();

        return new CarreraDTO(c.getIdCarrera(),c.getNombre());
    }

    @Override
    public List<ReporteCarreraDto> getReporteCarreras() {
        String query = "SELECT new DTO.ReporteCarreraDto( " +
                "c.nombre, " +
                "COUNT(i.fecha_inscripcion), " +
                "COUNT(i.fecha_graduacion), " +
                "EXTRACT(YEAR FROM i.fecha_inscripcion) ) " +
                "FROM Carrera c " +
                "LEFT JOIN c.inscriptos i " +
                "GROUP BY c.idCarrera, EXTRACT(YEAR FROM i.fecha_inscripcion) " +
                "ORDER BY c.nombre , EXTRACT(YEAR FROM i.fecha_inscripcion)  ";
        List<ReporteCarreraDto> reporte =
                em.createQuery(query , ReporteCarreraDto.class).getResultList();

        return reporte;
    }


}