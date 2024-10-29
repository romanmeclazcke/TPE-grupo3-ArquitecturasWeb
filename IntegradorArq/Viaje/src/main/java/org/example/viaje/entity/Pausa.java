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
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;


}
