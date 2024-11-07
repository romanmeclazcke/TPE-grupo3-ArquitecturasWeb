package org.example.mapa.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monopatin {
    private Long id;
    private Duration tiempo_uso;
    private Double kilometros;
    private Boolean disponible;
    private Integer x;
    private Integer y;
}
