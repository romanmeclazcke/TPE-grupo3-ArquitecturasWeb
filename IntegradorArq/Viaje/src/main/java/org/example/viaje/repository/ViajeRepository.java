package org.example.viaje.repository;

import org.example.viaje.DTO.ViajeResumenMesesDTO;
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

    @Query("SELECT v.id_monopatin FROM Viaje v WHERE FUNCTION('YEAR', v.fecha_inicio) = :anio GROUP BY v.id_monopatin HAVING COUNT(v) > :viajes")
    List<Long> getMonopatinesConMasDeXViajes(@Param("anio") int anio, @Param("viajes") int viajes);

    @Query("SELECT new org.example.viaje.DTO.ViajeResumenMesesDTO(YEAR(v.fecha_inicio),SUM(t.tarifa), :mesAnterior, :mesPosterior) FROM Viaje v JOIN v.tarifa t  WHERE YEAR(v.fecha_inicio) = :anio AND MONTH(v.fecha_inicio) BETWEEN :mesAnterior AND :mesPosterior GROUP BY MONTH(v.fecha_inicio)")
    List<ViajeResumenMesesDTO> getTotalFacturadoEntre(@Param("anio") int anio, @Param("mesAnterior") int mesAnterior, @Param("mesPosterior") int mesPosterior);
}
