package Repository;

import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import java.util.List;

public interface InscripcionRepository {
    Inscripcion createInscripcion(Estudiante estudiante, Carrera carrera);
    List<Estudiante> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera);
    List<Carrera> getCarrerasOrderByInscriptos();
}
