package Repository;

import DTO.CarreraConNumeroInscriptosDTO;
import DTO.CarreraDTO;
import Entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    CarreraDTO createCarrera(Carrera c);
    List<CarreraConNumeroInscriptosDTO> getReporteCarreras();
}
