package org.example.viaje.DTO;

import java.time.LocalDate;
import java.util.Date;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeResponseDTO {

    @Schema(description = "Id del usuario al que se asigno el viaje", example = "3")
    private Long idUsuario;
    @Schema(description = "Id del monopatin utilizado para el viaje", example = "10")
    private Long idMonopatin;
    @Schema(description = "Id de la parada en la que se inicio el viaje", example = "8")
    private Long idParadaOrigen;
    @Schema(description = "Id de la parada donde se dejara el monopatin", example = "7")
    private Long idParadaDestino;
    @Schema(description = "Fecha de inicio del viaje" , example = "2024-11-16T15:30:00" )
    private LocalDate fechaInicio;
    @Schema(description = "Fecha de finalización del viaje", example = "2024-11-16T15:50:00")
    private LocalDate fechaFin;
    @Schema(description = "Kilometros recorridos en el viaje", example = "5.4")
    private Double kilometrosRecorridos;

    private String mensaje;
    private boolean exito;
}
