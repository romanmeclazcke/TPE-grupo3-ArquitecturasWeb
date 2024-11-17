package org.example.pago.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDto {
    @Schema(description = "Id del usuario asociado al pago",example = "2")
    private Long userId;

    @Schema(description = "id del viaje asociado al pago", example = "20")
    private Long viajeId;

    @Schema(description = "Monto total del viaje", example = "2302.2")
    private Double monto;

    @Schema(description = "Fecha de emision del pago", example = "2024/10/10")
    private LocalDate fechaEmitida;
}
