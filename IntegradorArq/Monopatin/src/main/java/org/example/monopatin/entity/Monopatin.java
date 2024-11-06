package org.example.monopatin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;

@Entity
@Table(name="monopatin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Duration tiempo_uso;
    private Double kilometros= 0.0;
    private Boolean disponible;
    private Integer x= 0;
    private Integer y =0;

}
