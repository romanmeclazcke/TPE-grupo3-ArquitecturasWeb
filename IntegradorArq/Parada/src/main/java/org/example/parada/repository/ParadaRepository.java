package org.example.parada.repository;

import org.example.parada.entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT p FROM Parada p WHERE :idMonopatin MEMBER OF p.id_monopatines")
    Parada getParadaContieneMonopatin(@Param("idMonopatin") Long idMonopatin);
}
