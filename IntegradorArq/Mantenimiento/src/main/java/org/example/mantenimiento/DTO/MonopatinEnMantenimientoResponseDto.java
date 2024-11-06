package org.example.mantenimiento.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinEnMantenimientoResponseDto {
    private Boolean en_mantenimiento;
    private String mensaje;
    private Boolean estado;
    private Long idMonopatin;
}
