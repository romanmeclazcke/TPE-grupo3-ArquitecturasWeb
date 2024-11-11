package org.example.pago.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDto {
    private Long userId;
    private Long viajeId;
    private Double monto;
    private LocalDate fechaEmitida;
}
