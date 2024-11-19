package org.example.monopatin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinRequestDto {

    @Schema(description = "ID del monopatín", example = "1", required = false)
    private long id;

    //@NotNull(message = "El tiempo de uso es un campo obligatorio")
    @Schema(description = "Duración del tiempo de uso del monopatín", example = "PT1H30M", required = true)
    private Duration tiempo_uso;

    @Positive(message = "La cantidad de km debe ser un valor positivo")
    @NotNull(message = "La cantidad de km es un campo obligatorio")
    @Schema(description = "Distancia recorrida por el monopatín en kilómetros", example = "15.5", required = true)
    private double kilometros;

    @NotNull(message = "La disponibilidad del monopatín es obligatoria")
    @Schema(description = "Estado de disponibilidad del monopatín", example = "true", required = true)
    private boolean disponible;

    @NotNull(message = "La coordenada X es obligatoria")
    @Schema(description = "Coordenada X en el mapa", example = "100", required = true)
    private Integer x;

    @NotNull(message = "La coordenada Y es obligatoria")
    @Schema(description = "Coordenada Y en el mapa", example = "200", required = true)
    private Integer y;

}
