package org.example.viaje.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDto {

    @Schema(description = "Id del usuario asociado al pago", example = "11")
    private Long userId;
    @Schema(description = "Id del viaje al que se le realiza el pago", example = "1")
    private Long viajeId;
    @Schema(description = "Monto a pagar del viaje asociado", example = "200.50")
    private Double monto;
}
