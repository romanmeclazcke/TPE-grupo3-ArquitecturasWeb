package org.example.viaje.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaRequestDto {

    @Schema(description = "Costo de la tarifa", example = "150.00")
    @Positive(message = "La tarifa debe ser un valor positivo")
    private Double tarifa;

    @Schema(description = "Tipo de tarifa asignada al viaje", example = "Normal")
    @NotNull(message = "Es obligatorio indicar el tipo de tarifa")
    @NotEmpty(message = "El tipo de tarifa no puede esta vacio")
    private String tipo_tarifa;

    @Schema(description = "Fecha en la que se genero la tarifa" , example = "2024-11-16T15:30:00.000Z")
    private Date fecha_inicio;
}
