package org.example.parada.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaResponseDto {
    private Long id;
    private List<Long> monopatines;

    private String mensaje;
    private boolean exito;
}