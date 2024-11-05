package org.example.reporte.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTiempoResponseDto {
    private Double totalTiempo; //con pausas o sin pausas
    private Boolean exito;
}
