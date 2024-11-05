package org.example.viaje.DTO;


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
    Long id;
    Date fecha_inicio;
    LocalDateTime hora_frin;
    Date fecha_fin;
    LocalDateTime hora_inicio;
    String mensaje;
    Boolean estado;
}
