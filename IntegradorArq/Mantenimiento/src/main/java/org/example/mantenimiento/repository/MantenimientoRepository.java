package org.example.mantenimiento.repository;

import org.example.mantenimiento.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    Mantenimiento save(Mantenimiento nuevo);

    @Query("SELECT m FROM Mantenimiento m WHERE m.id_monopatin = :idMonopatin")
    List<Mantenimiento> findAllByIdMonopatin(Long idMonopatin);

    @Query("SELECT COUNT(m) > 0 FROM Mantenimiento m WHERE m.id_monopatin = :idMonopatin")
    boolean existsById(@Param("idMonopatin") Long idMonopatin);


    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mantenimiento m WHERE m.id_monopatin = :idMonopatin")
    boolean obtenerSiEstaEnMantenimiento(@Param("idMonopatin") Long idMonopatin);
}
