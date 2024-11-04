package org.example.viaje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaResponseDto {
    private Long id;
    private Double tarifa;
    private String tipo_tarifa;
    private Date fecha_inicio;
}
