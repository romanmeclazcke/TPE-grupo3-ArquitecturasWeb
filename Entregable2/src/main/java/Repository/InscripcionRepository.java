package Repository;

import DTO.CarreraConNumInscriptosDto;
import DTO.CarreraConNumInscriptosDto;
import DTO.CarreraDTO;
import DTO.EstudianteDTO;
import DTO.InscripcionDTO;
import Entities.Carrera;
import Entities.Estudiante;
import Entities.Inscripcion;
import java.util.List;

public interface InscripcionRepository {
    InscripcionDTO createInscripcion(Estudiante estudiante, Carrera carrera);
    List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera);
    List<CarreraConNumInscriptosDto> getCarrerasOrderByInscriptos();
    InscripcionDTO graduarEstudiante(Estudiante estudiante, Carrera carrera);
}
