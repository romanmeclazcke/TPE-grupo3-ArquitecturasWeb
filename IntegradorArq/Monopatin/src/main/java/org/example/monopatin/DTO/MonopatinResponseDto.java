package org.example.monopatin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinResponseDto {

    @Schema(description = "ID único del monopatín", example = "1")
    private Long id;

    @Schema(description = "Tiempo de uso del monopatín", example = "PT1H30M") // ISO 8601 duration format
    private Duration tiempo_uso;

    @Schema(description = "Distancia recorrida en kilómetros", example = "15.5")
    private Double kilometros;

    @Schema(description = "Indica si el monopatín está disponible para uso", example = "true")
    private Boolean disponible;

    @Schema(description = "Coordenada X en el mapa", example = "100")
    private Integer x;

    @Schema(description = "Coordenada Y en el mapa", example = "200")
    private Integer y;
}
