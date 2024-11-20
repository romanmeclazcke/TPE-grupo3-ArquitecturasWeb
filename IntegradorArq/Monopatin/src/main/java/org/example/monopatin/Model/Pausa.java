package org.example.monopatin.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pausa {
    private Long id;
    private Date fecha_inicio;
    private LocalDateTime hora_inicio;
    private  Date fecha_fin;
    private LocalDateTime hora_fin;
}
