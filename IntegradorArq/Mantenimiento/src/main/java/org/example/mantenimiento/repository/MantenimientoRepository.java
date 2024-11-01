package org.example.mantenimiento.repository;

import org.example.mantenimiento.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    Mantenimiento save(Mantenimiento nuevo);
}
