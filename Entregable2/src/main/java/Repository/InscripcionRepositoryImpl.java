package Repository;

import DTO.*;
import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class InscripcionRepositoryImpl implements InscripcionRepository {
    private final EntityManager em;
    private static InscripcionRepositoryImpl instancia;

    public InscripcionRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public InscripcionDTO createInscripcion(Estudiante estudiante, Carrera carrera) {
        this.em.getTransaction().begin();
        Inscripcion inscripcion = null;
        if(estudiante != null && carrera != null) {
            inscripcion = new Inscripcion(estudiante, carrera, null);
            this.em.persist(inscripcion);
        }

        this.em.getTransaction().commit();

        if (inscripcion != null) {
            return new InscripcionDTO(estudiante,carrera,inscripcion.getFecha_inscripcion(), inscripcion.getFecha_graduacion());
        }
        return null;
    }



    @Override
    public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera) {
        String query = "SELECT new DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.edad, e.genero, e.documento, e.ciudad_residencia) " +
                "FROM Carrera c " +
                "JOIN c.inscriptos i " +
                "JOIN i.estudiante e " +
                "WHERE i.carrera = :carrera AND e.ciudad_residencia = :ciudad";

        return em.createQuery(query, EstudianteDTO.class)
                .setParameter("carrera", carrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }

    @Override
    public List<CarreraConNumInscriptosDto> getCarrerasOrderByInscriptos() {
        String query = "SELECT new DTO.CarreraConNumInscriptosDto(c.idCarrera, c.nombre, count(i)) " +
                "FROM Carrera c LEFT JOIN c.inscriptos i " +
                "GROUP BY c.idCarrera, c.nombre " +
                "ORDER BY count(i) DESC";
        return em.createQuery(query, CarreraConNumInscriptosDto.class).getResultList();
    }

    @Override
    public InscripcionDTO graduarEstudiante(Estudiante estudiante, Carrera carrera) {

        this.em.getTransaction().begin();

        String query = "SELECT i FROM Inscripcion i WHERE i.estudiante = :estudiante AND i.carrera = :carrera";
        Inscripcion inscripcion = em.createQuery(query, Inscripcion.class)
                .setParameter("estudiante", estudiante)
                .setParameter("carrera", carrera)
                .getSingleResult();

        if(inscripcion != null) {
            inscripcion.setFecha_graduacion(new Date());
            this.em.persist(inscripcion);
            this.em.getTransaction().commit();
            return new InscripcionDTO(estudiante, carrera, inscripcion.getFecha_inscripcion(), inscripcion.getFecha_graduacion());
        }
        return null;
    }


    public static InscripcionRepositoryImpl getInstancia(EntityManager em) {
        if (instancia == null) {
            instancia = new InscripcionRepositoryImpl(em);
        }
        return instancia;
    }
}
