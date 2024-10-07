package com.example.entregable3.Repository;

import org.example.practico3.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonasRepository extends JpaRepository<Persona, Integer> {

    @Query("SELECT p FROM Persona p JOIN p.turnos t WHERE t.id = :idTurno")
    public List<Persona> getPersonasByTurno(@Param("idTurno") Integer idTurno);

    @Query("Select s from Socio s")
    List<Persona> getSocios();


    @Query("Select p from Persona p JOIN p.domicilio d WHERE d.id =:idCiudad")
    List<Persona> getPersonasByCiudad(@Param("idCiudad") Integer idCiudad);
}
