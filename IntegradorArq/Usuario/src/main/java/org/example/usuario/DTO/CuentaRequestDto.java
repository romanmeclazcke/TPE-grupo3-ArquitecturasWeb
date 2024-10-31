package org.example.usuario.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.usuario.entity.Usuario;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRequestDto {

    @NotEmpty(message = "el campo nombre del rol no puede estar vacío")
    @NotNull( message = "El nombre deel rol es un campo obligatorio.")
    private Double credito;


}
