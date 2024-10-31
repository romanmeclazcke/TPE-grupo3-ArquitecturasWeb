package org.example.usuario.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequestDto {

    private Long id; // Número identificatorio

    @NotEmpty(message = "el campo nombre del rol no puede estar vacío")
    @NotNull( message = "El nombre deel rol es un campo obligatorio.")
    private String tipo_rol;

}
