package org.example.mapa.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanciasResponseDto {
    private Long idParadaOrigen;
    private Long idParadaDestino;
    private Double distancia;
}
