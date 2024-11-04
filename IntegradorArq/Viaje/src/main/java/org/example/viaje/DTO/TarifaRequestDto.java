package org.example.viaje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaRequestDto {
    private Double tarifa;
    private String tipo_tarifa;
    private Date fecha_inicio;
}
