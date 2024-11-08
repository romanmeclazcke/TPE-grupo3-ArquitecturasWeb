package org.example.viaje.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaRequestDto {
    @Positive(message = "La tarifa debe ser un valor positivo")
    private Double tarifa;
    @NotNull(message = "Es obligatorio indicar el tipo de tarifa")
    @NotEmpty(message = "El tipo de tarifa no puede esta vacio")
    private String tipo_tarifa;
    private Date fecha_inicio;
}
