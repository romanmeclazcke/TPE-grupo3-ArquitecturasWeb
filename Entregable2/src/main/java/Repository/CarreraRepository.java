package Repository;

import DTO.CarreraDTO;
import Entities.Carrera;

public interface CarreraRepository {
    CarreraDTO createCarrera(Carrera c);
}
