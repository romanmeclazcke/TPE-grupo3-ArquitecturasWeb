package org.example.usuario.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCargarSaldoDto {
    @NotNull( message = "El credito es Obligatorio para cargar a una cuenta")
    private Double credito;
}
