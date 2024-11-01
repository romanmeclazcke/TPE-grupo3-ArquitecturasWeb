package org.example.mantenimiento.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoResponseDto {
    private Long id_monopatin;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private List<String> acciones;

    private String mensaje;
    private boolean exito;
}
