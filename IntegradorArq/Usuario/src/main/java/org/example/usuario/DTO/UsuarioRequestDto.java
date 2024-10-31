package org.example.usuario.DTO;


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
    private Long id; // Número identificatorio

    @NotEmpty(message = "el campo nombre de la carrera no puede estar vacío")
    @NotNull( message = "El nombre es un campo obligatorio.")
    private String nombre; // Nombre del usuario

    @NotEmpty(message = "el campo apellido  no puede estar vacío")
    @NotNull( message = "El apellido es un campo obligatorio.")
    private String apellido; // Apellido del usuario

    @NotEmpty(message = "el numero de ceular no puede estar vacío")
    @NotNull( message = "El numero de celular es un campo obligatorio.")
    private Long numeroCelular; // Número de celular

    @NotEmpty(message = "el email nombre de la carrera no puede estar vacío")
    @NotNull( message = "El email es un campo obligatorio.")
    private String email; // Email válido

    private Long id_rol;

}
