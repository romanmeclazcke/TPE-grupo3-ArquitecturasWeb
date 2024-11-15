package org.example.parada.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaResponseDto {
    @Schema(description = "id de la parada",example = "1")
    private Long id;
    @Schema(description = "Lista de id de monopatines en la parada", example = "<1,2,3,4,5>")
    private List<Long> monopatines;
    @Schema(description = "Posicion x de la parada " , example = "200")
    private Integer x;
    @Schema(description = "Posicion y de la parada", example = "20")
    private Integer y;
    private String mensaje;
    private boolean exito;
}
