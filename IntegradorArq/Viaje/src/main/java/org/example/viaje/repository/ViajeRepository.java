package org.example.viaje.repository;

import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE v.id_monopatin = :idMonopatin AND v.id_usuario = :idUsuario")
    List<Viaje> findAllByIds(@Param("idMonopatin") Long idMonopatin, @Param("idUsuario") Long idUsuario);

    @Query("SELECT v FROM Viaje v WHERE v.id_monopatin = :idMonopatin")
    List<Viaje> getViajesPorMonopatin(@Param("idMonopatin") Long idMonopatin);


}
