package org.example.viaje.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distancia {
    private Long idParadaOrigen;
    private Long idParadaDestino;
    private Double distancia;
}
