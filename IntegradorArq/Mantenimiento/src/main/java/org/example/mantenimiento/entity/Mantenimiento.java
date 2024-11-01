package org.example.mantenimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_monopatin;

    @Column
    private LocalDate fecha_inicio;
    @Column(nullable = true)
    private LocalDate fecha_fin;

    @ElementCollection //chequear
    private List<String> acciones;
}
