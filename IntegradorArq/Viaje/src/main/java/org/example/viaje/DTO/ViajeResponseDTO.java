package org.example.viaje.DTO;

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

    private Date fechaInicio;

    private Date fechaFin;

    private Double kilometrosRecorridos;
}
