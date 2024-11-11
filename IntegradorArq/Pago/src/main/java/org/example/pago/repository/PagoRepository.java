package org.example.pago.repository;

import org.example.pago.DTO.ResumenPagosDTO;
import org.example.pago.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {

    @Query("SELECT new org.example.pago.DTO.ResumenPagosDTO(YEAR(p.fechaEmitido),SUM(p.monto), :mesAnterior, :mesPosterior) " +
            "FROM Pago p " +
            "WHERE YEAR(p.fechaEmitido) = :anio AND MONTH(p.fechaEmitido) BETWEEN :mesAnterior AND :mesPosterior GROUP BY YEAR(p.fechaEmitido)")
    ResumenPagosDTO getTotalFacturadoEntre(@Param("anio") int anio, @Param("mesAnterior") int mesAnterior, @Param("mesPosterior") int mesPosterior);


}
