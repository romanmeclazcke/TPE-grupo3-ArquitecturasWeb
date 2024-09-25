package Repository;

import DTO.EstudianteDTO;
import Entities.Estudiante;
import java.util.List;

public interface EstudianteRepository {

    EstudianteDTO createEstudiante(Estudiante estudiante);
    List<EstudianteDTO> getEstudiantesByEdad();
    EstudianteDTO getEstudianteByLibreta(int numero_libreta);
    List<EstudianteDTO> getEstudianteByGenero(char genero);
}
