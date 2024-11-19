package org.example.mantenimiento.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoResponseDto {
    @Schema(description = "Id del mantenimiento", example = "3")
    private Long id;
    @Schema(description = "Id del monopatin asignado al mantenimiento", example = "14")
    private Long id_monopatin;
    @Schema(description = "Fecha en la que se inicio el mantenimiento", example = "2020-02-20")
    private LocalDate fecha_inicio;
    @Schema(description = "Fecha en la que finalizo el mantenimiento", example = "2020-02-26")
    private LocalDate fecha_fin;
    @Schema(description = "Lista de modificaciones/reparaciones realizadas", example = "[Cambio de batería, Ajuste de frenos]")
    private List<String> acciones;

    private String mensaje;
    private boolean exito;
}
