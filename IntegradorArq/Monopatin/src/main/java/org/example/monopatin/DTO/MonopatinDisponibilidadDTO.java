package org.example.monopatin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinDisponibilidadDTO {
    
    private int MonopatinesDisponibles;
    private int MonopatinesEnMantenimiento;
}
