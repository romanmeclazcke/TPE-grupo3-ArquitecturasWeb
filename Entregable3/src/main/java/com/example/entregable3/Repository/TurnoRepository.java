package com.example.entregable3.Repository;

import org.example.practico3.Model.Persona;
import org.example.practico3.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    @Query("SELECT p FROm Turno t JOIN t.pacientes p WHERE t.id=:idTurno")
    public List<Persona> getPersonasAsignadasATurno(@Param("idTurno") Integer idTurno);

}
