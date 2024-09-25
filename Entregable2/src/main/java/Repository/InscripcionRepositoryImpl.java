package Repository;

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
    public Inscripcion createInscripcion(Estudiante estudiante, Carrera carrera) {
        if (estudiante == null && carrera == null) {
            Inscripcion inscripcion = new Inscripcion();
            return null; //acomodar
        }
        return null; //acomodar
    }

    @Override
    public List<Estudiante> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera) {
        return List.of();
    }

    @Override
    public List<Carrera> getCarrerasOrderByInscriptos() {
        return List.of();
    }
}
