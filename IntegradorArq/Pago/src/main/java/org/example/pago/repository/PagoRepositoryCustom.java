package org.example.pago.repository;

import org.example.pago.entity.Pago;
import org.example.pago.DTO.ResumenPagosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class PagoRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Double getTotalFacturadoEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("fechaEmitido").gte(fechaInicio).lt(fechaFin)
                ),
                Aggregation.group().sum("monto").as("total")
        );

        AggregationResults<ResumenPagosDTO> results = mongoTemplate.aggregate(aggregation, Pago.class, ResumenPagosDTO.class);

        if (results.getMappedResults().isEmpty()) {
            return 0.0;
        } else {
            // Retornamos el total sumado
            return results.getMappedResults().get(0).getTotal();
        }
    }
}
