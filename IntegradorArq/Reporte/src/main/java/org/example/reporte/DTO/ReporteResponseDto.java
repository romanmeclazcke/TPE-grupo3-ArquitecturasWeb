package org.example.reporte.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDto {
    private Long idMonopatin;
    private Double kilometros;
    private Duration tiempo_uso;
}
