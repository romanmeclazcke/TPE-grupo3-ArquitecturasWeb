package org.example.reporte.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reporte.Model.Monopatin;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDto {
    private List<Monopatin> monopatines;
    private Boolean exito;
    private String mensaje;
}
