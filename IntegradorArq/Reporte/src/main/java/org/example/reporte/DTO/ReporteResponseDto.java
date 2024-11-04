package org.example.reporte.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDto {
    private Long id;
    private Long idMonopatin;
    private LocalDate fecha;
    private Duration tiempo_uso_total;
}
