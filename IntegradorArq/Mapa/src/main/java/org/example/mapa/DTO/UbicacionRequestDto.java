package org.example.mapa.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionRequestDto {
    @Schema(description = "Posicion x de la ubicacion ", example = "10")
    private Long x;
    @Schema(description = "Posicion y de la ubicacion", example = "30")
    private Long y;
}
