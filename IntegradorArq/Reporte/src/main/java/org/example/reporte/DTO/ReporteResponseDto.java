package org.example.reporte.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDto {
    @Schema(description = "Id del monopatin", example ="20")
    private Long idMonopatin;
    @Schema(description = "kilometros del monopatin", example ="1000")
    private Double kilometros;
    @Schema(description = "tiempo de uso del monopatin", example ="PT8H30M")
    private Duration tiempo_uso;
}
