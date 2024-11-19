package org.example.mantenimiento.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoRequestDto {

    @Schema(description = "Fecha en la que inicio el mantenimiento", example = "2024-11-15")
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    //@NotEmpty(message = "La fecha de inicio no puede estar vacía")
    private LocalDate fecha_inicio;
    @Schema(description = "Lista de modificaciones/reparaciones realizadas", example = "[Cambio de batería, Ajuste de frenos]")
    private List<String> acciones;
}
