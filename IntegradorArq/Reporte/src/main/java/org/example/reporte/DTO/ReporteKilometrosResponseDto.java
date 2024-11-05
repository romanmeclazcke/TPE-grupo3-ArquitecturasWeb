package org.example.reporte.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteKilometrosResponseDto {
    private Double totalKilometros;
    private Boolean exito;
}
