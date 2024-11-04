package org.example.reporte.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteRequestDto {
    private long id;

    @NotNull(message = "El id monopatín es un campo obligatorio")
    @NotEmpty(message = "El id monopatín no puede estar vacío")
    private Long idMonopatin;
}
