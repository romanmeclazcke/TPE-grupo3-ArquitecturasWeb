package org.example.viaje.repository;

import org.example.viaje.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {


    @Query(" SELECT  t FROM Tarifa  t where  t.fecha_inicio < current date " +
            "order by t.fecha_inicio desc ")
    Tarifa getTarifaEnPlazoValido();
}
