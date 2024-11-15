package org.example.mapa.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinResponseDto {

    @Schema(description = "ID único del monopatín", example = "123")
    private Long id;

    @Schema(description = "Tiempo de uso del monopatín", example = "PT30M")
    private Duration tiempo_uso;

    @Schema(description = "Distancia recorrida en kilómetros", example = "10.5")
    private Double kilometros;

    @Schema(description = "Posición en el eje X", example = "45")
    private Integer x;

    @Schema(description = "Posición en el eje Y", example = "30")
    private Integer y;
}
