package org.example.mapa.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanciasResponseDto {

    @Schema(description = "Identificador único de la parada de origen", example = "1")
    private Long idParadaOrigen;

    @Schema(description = "Identificador único de la parada de destino", example = "2")
    private Long idParadaDestino;

    @Schema(description = "Distancia en kilómetros entre las paradas", example = "15.5")
    private Double distancia;
}
