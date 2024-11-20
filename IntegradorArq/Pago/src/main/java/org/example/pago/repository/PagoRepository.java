package org.example.pago.repository;

import org.example.pago.entity.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends MongoRepository<Pago, String> {

    @Query("{ 'fechaEmitido': { $gte: ?0, $lt: ?1 } }")
    Double getTotalFacturadoEntre(LocalDate fechaInicio, LocalDate fechaFin);
}
