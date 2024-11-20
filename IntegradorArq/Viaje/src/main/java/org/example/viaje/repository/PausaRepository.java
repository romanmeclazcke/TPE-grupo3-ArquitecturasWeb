package org.example.viaje.repository;

import org.example.viaje.entity.Pausa;
import org.example.viaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PausaRepository extends JpaRepository<Pausa, Long> {


    @Query("SELECT p FROM Pausa p WHERE p.viaje.id = :idViaje")
    List<Pausa> getPausasPorViaje(@Param("idViaje") Long idViaje);



    @Query("SELECT p FROM Pausa p WHERE p.viaje.id_monopatin=:idMonopatin")
    List<Pausa> getPausasPorMonopatin(@Param("idMonopatin") Long idMonopatin);


}
