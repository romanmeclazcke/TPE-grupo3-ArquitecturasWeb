package org.example.usuario.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.usuario.DTO.RolRequestDto;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.entity.Rol;
import org.example.usuario.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    RolService rolService;

    @Operation(
            summary = "Crear un nuevo rol",
            description = "Endpoint para crear un nuevo rol a partir de su DTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para crear un nuevo rol: RolRequestDTO",
                    required = true,
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = RolRequestDto.class)
                    )
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "El rol a sido creado correctamente",
                            content = @Content(
                                    schema = @Schema(implementation = Rol.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Revise los campos he intente nuevamente"
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<?> crearRol(@RequestBody RolRequestDto rolRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(rolService.save(rolRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear el rol, revise los campos e intente nuevamente.\"}");
        }
    }

}
