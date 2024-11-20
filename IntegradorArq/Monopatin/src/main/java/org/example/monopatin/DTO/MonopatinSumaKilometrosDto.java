package org.example.monopatin.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinSumaKilometrosDto {

    @Schema(description = "Cantidad total de kilómetros recorridos por el monopatín", example = "20.5", required = true)
    private Double kilometros;
}
