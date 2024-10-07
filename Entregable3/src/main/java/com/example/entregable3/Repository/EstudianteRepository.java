package com.example.entregable3.Repository;


import com.example.entregable3.DTO.EstudianteDTO;
import com.example.entregable3.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EstudianteRepository")
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT new com.example.entregable3.DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.edad) FROM Estudiante e ORDER BY e.edad")
    List<EstudianteDTO> getEstudiantesByEdad();

    @Query("SELECT new com.example.entregable3.DTO.EstudianteDTO(e.num_libreta, e.nombre, e.apellido, e.genero) FROM Estudiante e WHERE e.genero = :genero")
    List<EstudianteDTO> getEstudianteByGenero(char genero);
}
