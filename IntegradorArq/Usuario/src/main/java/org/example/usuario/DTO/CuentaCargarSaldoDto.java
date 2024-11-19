package org.example.usuario.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCargarSaldoDto {

    @Schema(description = "Credito a cargar", example = "200.00")
    @NotNull( message = "El credito es Obligatorio para cargar a una cuenta")
    private Double credito;
}
