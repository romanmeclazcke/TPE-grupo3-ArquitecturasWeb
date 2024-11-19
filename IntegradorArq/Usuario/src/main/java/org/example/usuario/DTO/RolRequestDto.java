package org.example.usuario.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequestDto {

    @Schema(description = "Id autogenerado del rol", example = "1")
    private Long id; // Número identificatorio

    @Schema(description = "Nombre del rol", example = "ADMINISTRADOR")
    @NotEmpty(message = "el campo nombre del rol no puede estar vacío")
    @NotNull( message = "El nombre deel rol es un campo obligatorio.")
    private String tipo_rol;

}
