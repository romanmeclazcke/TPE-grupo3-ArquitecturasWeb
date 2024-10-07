package com.example.entregable3.Repository;
import com.example.entregable3.DTO.ReporteCarreraDto;
import com.example.entregable3.Model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CarreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera,Integer> {

    @Query("SELECT new com.example.entregable3.DTO.ReporteCarreraDto( " +
            "c.nombre, " +
            "COUNT(i.fecha_inscripcion), " +
            "COUNT(i.fecha_graduacion), " +
            "EXTRACT(YEAR FROM i.fecha_inscripcion) ) " +
            "FROM Carrera c " +
            "LEFT JOIN c.inscriptos i " +
            "GROUP BY c.idCarrera, EXTRACT(YEAR FROM i.fecha_inscripcion) " +
            "ORDER BY c.nombre , EXTRACT(YEAR FROM i.fecha_inscripcion)  ")
    List<ReporteCarreraDto> getReporteCarreras();
}
