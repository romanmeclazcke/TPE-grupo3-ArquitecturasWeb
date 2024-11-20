package org.example.monopatin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDisponibilidadDTO {

    @Schema(description = "Número de monopatines disponibles para uso", example = "10")
    private int MonopatinesDisponibles;

    @Schema(description = "Número de monopatines actualmente en mantenimiento", example = "2")
    private int MonopatinesEnMantenimiento;
}
