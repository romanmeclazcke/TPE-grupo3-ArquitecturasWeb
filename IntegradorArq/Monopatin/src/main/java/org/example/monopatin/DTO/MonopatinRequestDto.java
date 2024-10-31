package org.example.monopatin.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinRequestDto {

    private long id;

    @NotEmpty(message = "El tiempo de uso es un campo obligatorio")
    private Duration tiempo_uso;

    @NotNull(message = "La cantidad de km es un campo obligatorio")
    @NotEmpty(message = "La cantidad de km no puede estar vacía")
    private double kilometros;

    @NotNull(message = "La disponibilidad del monopatin es obligatoria")
    private boolean disponible;

}
