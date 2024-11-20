package org.example.usuario.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Credito a cargar", example = "200.00")
    @NotNull( message = "El credito es Obligatorio para editar una cuenta")
    private Double credito;


}
