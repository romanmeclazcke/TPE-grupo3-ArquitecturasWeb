package org.example.monopatin.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinRequestDto {

    private long id;

    @NotNull(message = "El tiempo de uso es un campo obligatorio")
    private Duration tiempo_uso;

    @Positive(message = "La cantidad de km debe ser un postiva")
    @NotNull(message = "La cantidad de km es un campo obligatorio")
    private double kilometros;

    @NotNull(message = "La disponibilidad del monopatin es obligatoria")
    private boolean disponible;

}
