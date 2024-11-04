package org.example.viaje.DTO;

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
    
    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El monopatín es obligatorio")
    private Long idMonopatin;

    @Positive(message = "Los kilómetros recorridos deben ser un valor positivo")
    private Double kilometrosRecorridos;
}
