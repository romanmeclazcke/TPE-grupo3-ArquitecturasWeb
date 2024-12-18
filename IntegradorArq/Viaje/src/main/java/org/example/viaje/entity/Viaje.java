package org.example.viaje.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="viaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private LocalDateTime hora_inicio;
    private LocalDateTime hora_fin;
    private Double km;
    private Long id_usuario;
    private Long id_monopatin;
    private Long id_parada_origen;
    private Long id_parada_destino;

    @ManyToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Pausa> pausas;

}
