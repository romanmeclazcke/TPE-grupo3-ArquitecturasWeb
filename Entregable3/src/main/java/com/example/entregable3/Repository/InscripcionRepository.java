package com.example.entregable3.Repository;

import com.example.entregable3.DTO.CarreraConNumInscriptosDto;
import com.example.entregable3.DTO.EstudianteDTO;
import com.example.entregable3.DTO.InscripcionDTO;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Model.Estudiante;
import com.example.entregable3.Model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InscripcionRepository")
public interface InscripcionRepository  extends JpaRepository<Inscripcion, Integer> {
    @Query("SELECT new com.example.entregable3.DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.edad, e.genero, e.documento, e.ciudad_residencia) " +
            "FROM Carrera c " +
            "JOIN c.inscriptos i " +
            "JOIN i.estudiante e " +
            "WHERE i.carrera = :carrera AND e.ciudad_residencia = :ciudad")
    List<EstudianteDTO> getEstudiantesByCarreraAndCiudad(String ciudad, Carrera carrera);

    @Query("SELECT new com.example.entregable3.DTO.CarreraConNumInscriptosDto(c.idCarrera, c.nombre, count(i)) " +
            "FROM Carrera c LEFT JOIN c.inscriptos i " +
            "GROUP BY c.idCarrera, c.nombre " +
            "ORDER BY count(i) DESC")
    List<CarreraConNumInscriptosDto> getCarrerasOrderByInscriptos();
}
