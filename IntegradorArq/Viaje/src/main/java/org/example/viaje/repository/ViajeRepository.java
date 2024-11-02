package org.example.viaje.repository;

import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    
}
