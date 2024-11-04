package org.example.viaje.repository;

import org.example.viaje.entity.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PausaRepository extends JpaRepository<Pausa, Long> {

}
