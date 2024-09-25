package Repository;

import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import DTO.InscripcionDTO;
import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;

import javax.persistence.EntityManager;
import java.util.List;

public class InscripcionRepositoryImpl implements InscripcionRepository {
    private EntityManager em;

    public InscripcionRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public InscripcionDTO createInscripcion(Estudiante estudiante, Carrera carrera) {
        //COMPLETAR
        return null;
    }

    @Override
    public List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera) {
        String query = "SELECT new DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.ciudad_residencia) FROM Carrera c JOIN c.inscriptos i JOIN i.estudiante e WHERE i.carrera = :carrera AND e.ciudad_residencia = :ciudad";
        List<EstudianteDTO> estudiantes = em.createQuery(query, EstudianteDTO.class).setParameter("carrera", carrera).setParameter("ciudad", ciudad).getResultList();

        return estudiantes;
    }

    @Override
    public List<CarreraDTO> getCarrerasOrderByInscriptos() { //VERIFICAR CONSULTA
        String query = "SELECT new DTO.CarreraDTO(c.idCarrera, c.nombre), count(*) AS cantidad_inscriptos FROM Carrera c JOIN c.inscriptos i GROUP BY i.carrera HAVING count(*) > 0 ORDER BY count(*)";
        List<CarreraDTO> carreras = em.createQuery(query, CarreraDTO.class).getResultList();

        return carreras;
    }
}
