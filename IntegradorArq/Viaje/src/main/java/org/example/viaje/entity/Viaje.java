package org.example.viaje.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.usuario.entity.Usuario;

import java.sql.Time;
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
    private Date fecha_inicio;
    private Date fecha_fin;
    private Time hora_inicio;
    private Time hora_fin;
    private Double km;
    private Long id_usuario;
    private Long id_monopatin; //??

    @ManyToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Pausa> pausas;

}
