package Repository;

import Entities.Estudiante;
import java.util.List;

public interface EstudianteRepository {

    Estudiante createEstudiante(Estudiante estudiante);
    List<Estudiante> getEstudiantesByEdad();
    Estudiante getEstudianteByLibreta(int numero_libreta);
    List<Estudiante> getEstudianteByGenero(char genero);
}
