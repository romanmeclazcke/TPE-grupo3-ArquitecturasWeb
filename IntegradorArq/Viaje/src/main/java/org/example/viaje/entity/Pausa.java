package org.example.viaje.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDateTime;
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
    private LocalDateTime hora_inicio;

    @Column(nullable = true)
    private  Date fecha_fin;

    @Column(nullable = true)
    private LocalDateTime hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    @ToString.Exclude
    @JsonIgnore
    private Viaje viaje;


}
