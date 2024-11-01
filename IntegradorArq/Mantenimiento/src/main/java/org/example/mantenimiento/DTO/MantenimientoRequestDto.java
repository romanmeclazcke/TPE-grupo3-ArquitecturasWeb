package org.example.mantenimiento.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoRequestDto {
    private LocalDate fecha_fin;
    private List<String> acciones;

    @NotNull(message = "El id del monopatín es un campo obligatorio")
    @NotEmpty(message = "El id del monopatín no puede estar vacío")
    private Long id_monopatin;

    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @NotEmpty(message = "La fecha de inicio no puede estar vacía")
    private LocalDate fecha_inicio;
}
