package org.example.viaje.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="pausa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pausa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fecha_inicio;
    private Time hora_inicio;

    @Column(nullable = true)
    private  Date fecha_fin;

    @Column(nullable = true)
    private Time hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;


}
