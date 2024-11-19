package org.example.mantenimiento.DTO;

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
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    //@NotEmpty(message = "La fecha de inicio no puede estar vacía")
    private LocalDate fecha_inicio;
    private List<String> acciones;
}
