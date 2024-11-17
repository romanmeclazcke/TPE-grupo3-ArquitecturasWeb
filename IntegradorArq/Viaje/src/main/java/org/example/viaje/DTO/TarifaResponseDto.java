package org.example.viaje.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaResponseDto {

    @Schema(description = "Id de la tarifa", example = "1")
    private Long id;

    @Schema(description = "Costo de la tarifa", example = "150.00")
    private Double tarifa;

    @Schema(description = "Tipo de tarifa asignada al viaje", example = "Normal")
    private String tipo_tarifa;

    @Schema(description = "Fecha en la que se genero la tarifa" , example = "2024-11-16T15:30:00.000Z")
    private Date fecha_inicio;
}
