package org.example.usuario.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.usuario.entity.Rol;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {

    @Schema(description = "Id autogenerado de usuario" , example = "1")
    private Long id; // Número identificatorio

    @Schema(description = "Nombre del usuario", example = "Charles")
    @NotEmpty(message = "el campo nombre de la carrera no puede estar vacío")
    @NotNull( message = "El nombre es un campo obligatorio.")
    private String nombre; // Nombre del usuario

    @Schema(description = "Apellido del usuario", example = "Darwin")
    @NotEmpty(message = "el campo apellido  no puede estar vacío")
    @NotNull( message = "El apellido es un campo obligatorio.")
    private String apellido; // Apellido del usuario

    @Schema(description = "Numero de telefono asociado al usuario", example ="2494332088")
    @NotNull( message = "El numero de celular es un campo obligatorio.")
    private Long numeroCelular; // Número de celular

    @Schema(description = "Email del usuario", example = "charlesdarwin@gmail.com")
    @NotEmpty(message = "el email nombre de la carrera no puede estar vacío")
    @NotNull( message = "El email es un campo obligatorio.")
    private String email; // Email válido

    @Schema(description = "ID del rol asignado al usuario" , example = "2")
    private Long id_rol;

    @Schema(description = "Contraseña asignada al usuario", example = "asd123")
    private String password;

}
