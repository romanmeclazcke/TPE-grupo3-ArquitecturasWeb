package org.example.viaje.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PausaResponseDto {

    @Schema(description = "Id de la pausa realizada", example = "3")
    Long id;
    @Schema(description = "Fecha en la que se realizo la pausa", example = "2024-11-16T15:30:00.000Z")
    Date fecha_inicio;
    @Schema(description = "Hora en la que se finalizo la pausa", example = "2024-11-16T16:00:00")
    LocalDateTime hora_frin;
    @Schema(description = "Fecha en la que se realizo la pausa", example = "2024-11-16T16:00:00.000Z")
    Date fecha_fin;
    @Schema(description = "Hora en la que se inicio la pausa", example = "2024-11-16T15:30:00")
    LocalDateTime hora_inicio;

    String mensaje;
    Boolean estado;
}
