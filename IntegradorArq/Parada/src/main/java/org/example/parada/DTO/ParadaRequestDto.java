package org.example.parada.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaRequestDto {

    @Schema(description = "Id de la parada", example = "1", nullable = true)
    private Long id;
    @Schema(description = "Lista de monopatines en la parada", example = "<1,2,3>", nullable = true)
    private List<Long> monopatines;
    @Schema(description = "posicion x de la parada", example = "2", nullable = false)
    private Integer x;
    @Schema(description = "posicion y de la parada", example = "100", nullable = false)
    private Integer y;
}
