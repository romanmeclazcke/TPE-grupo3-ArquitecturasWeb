package org.example.monopatin.repository;

import org.example.monopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin  m where m.x between  :x-500 and :x+500 AND m.y between  :y-500 AND :y+500")
    List<Monopatin> getMonopatinesEnRadio1km(@PathVariable("x") Integer x, @PathVariable("y")Integer y);
}
