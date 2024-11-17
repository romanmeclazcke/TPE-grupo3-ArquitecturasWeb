package org.example.viaje.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeRequestDTO {

    @Schema(description = "Id del usuario al que se asigno el viaje", example = "3")
    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @Schema(description = "Id del monopatin utilizado para el viaje", example = "10")
    @NotNull(message = "El monopatín es obligatorio")
    private Long idMonopatin;

    @Schema(description = "Kilometros recorridos en el viaje", example = "5.4")
    @Positive(message = "Los kilómetros recorridos deben ser un valor positivo")
    private Double kilometrosRecorridos;
}
