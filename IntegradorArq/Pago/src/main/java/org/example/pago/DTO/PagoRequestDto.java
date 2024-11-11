package org.example.pago.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDto {
    private Long userId;
    private Long viajeId;
    private Double monto;
}
