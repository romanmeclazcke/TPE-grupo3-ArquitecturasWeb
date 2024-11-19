package org.example.usuario.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.usuario.entity.Rol;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDto {
    @Schema(description = "Id autogenerado de usuario" , example = "1")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "Charles")
    private String nombre;
    @Schema(description = "Apellido del usuario", example = "Darwin")
    private String apellido;
    @Schema(description = "Numero de telefono asociado al usuario", example ="2494332088")
    private Long numeroCelular;
    @Schema(description = "Email del usuario", example = "charlesdarwin@gmail.com")
    private String email;
    @Schema(description = "Contraseña asignada al usuario", example = "asd123")
    private String password;
    @Schema(description = "Rol asignado al usuario" , example = "2")
    private Rol rol;

    private String mensaje;
    private boolean exito;
}
