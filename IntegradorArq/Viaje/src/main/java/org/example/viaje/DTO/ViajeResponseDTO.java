package org.example.viaje.DTO;

import java.time.LocalDate;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeResponseDTO {
    private Long idUsuario;
    private Long idMonopatin;
    private Long idParadaOrigen;
    private Long idParadaDestino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double kilometrosRecorridos;

    private String mensaje;
    private boolean exito;
}
