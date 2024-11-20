package org.example.mantenimiento.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinEnMantenimientoResponseDto {

    @Schema(description = "Mantenimiento activado/desactivado", example = "true")
    private Boolean en_mantenimiento;
    @Schema(description = "Id del monopatin consultado", example = "13")
    private Long idMonopatin;

    private String mensaje;
    private Boolean estado;
}
