package org.example.usuario.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Long numeroCelular;
    private String email;
    private Long id_rol;

    private String mensaje;
    private boolean exito;
}
