package org.example.usuario.repository;


import org.example.usuario.DTO.CuentaResponseDto;
import org.example.usuario.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {

    @Query("SELECT new org.example.usuario.DTO.CuentaResponseDto(c.credito, c.fecha_alta) " +
            "FROM Cuenta c JOIN c.usuarios u " +
            "WHERE u.id = :userId")
    List<CuentaResponseDto> getCuentasByUser(@Param("userId") Long userId);

}
