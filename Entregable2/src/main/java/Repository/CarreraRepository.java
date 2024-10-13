package Repository;

import DTO.ReporteCarreraDto;
import DTO.CarreraDTO;
import DTO.ReporteCarreraDto;
import Entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    CarreraDTO createCarrera(Carrera c);
    List<ReporteCarreraDto> getReporteCarreras();
}
