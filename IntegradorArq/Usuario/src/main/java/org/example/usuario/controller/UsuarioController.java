package org.example.usuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.usuario.DTO.CuentaRequestDto;
import org.example.usuario.DTO.CuentaResponseDto;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.DTO.UsuarioResponseDto;
import org.example.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Obtener todos los usuarios"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Los usuarios han sido obtenidos con exito",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(type = "array", implementation = UsuarioResponseDto.class)
                                    )
                            }
                    )
            }
    )
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener todos los usuarios.\"}");
        }
    }

    @Operation(
            summary = "Obtener un usuario via un ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se obtuvo correctamente el usuario",
                            content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "El usuario con la id requerida no ha sido encontrado"
                    )
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@Parameter(description = "Id del usuario a buscar", example = "5", required = true) @PathVariable Long userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuarioById(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener el usuario con id :"+userId+"."+"\"}");
        }
    }

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Endpoint para crear un nuevo usuario a través del DTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para crear un usuario: UsuarioRequestDTO",
                    required = true,
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UsuarioRequestDto.class)
                    )
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Se ha creado el usuario con exito",
                            content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UsuarioResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Revise los campos he intente nuevamente"
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<?> crearUsuario(@Parameter(description = "DTO de la cuenta a crear", required = true, schema = @Schema(implementation = CuentaRequestDto.class))
                                              @RequestBody @Valid UsuarioRequestDto UsuarioRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(UsuarioRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar e;l usuario, revise los campos e intente nuevamente.\"}");
        }
    }


    @Operation(
            summary = "Agregar usuario a una cuenta",
            description = "Agregar usuario a una cuenta previamente creada a través de: userId y cuentaId"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Se ha asignado el usuario a la cuenta con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Verifique los ids e intente nuevamente"
                    )
            }
    )
    @PostMapping("/{userId}/agregar-cuenta/{cuentaId}")
    public ResponseEntity<?> agregarCuenta(@Parameter(description = "Id del usuario asignado" , example = "16", required = true) @PathVariable Long userId,
                                           @Parameter(description = "Id de la cuenta a la que se asigna", example = "1", required = true)@PathVariable Long cuentaId){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarCuenta(userId,cuentaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar e;l usuario, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(
            summary = "Eliminar un usuario en base a su id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Usuario eliminado con exito"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "El id de el usuario a eliminar no fue encontrado"
                    )
            }
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> eliminarUsuario(@Parameter(description = "Id de el usuario a eliminar", example = "3", required = true)@PathVariable Long userId) {
        try {
            usuarioService.eliminarUsuario(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ Usuario eliminado }"); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario con id: " + userId + ".\"}");
        }
    }

    @Operation(
            summary = "Editar un usuario en base a un id",
            description = "Endpoint para editar un usuario en base a : userId y el DTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para editar usuario: usuarioRequestDTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioRequestDto.class)
                    )
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La cuenta ha sido editada con exito",
                            content = @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = UsuarioRequestDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Chequee el id y la información requerida"
                    )
            }
    )
    @PatchMapping("/{userId}")
    public ResponseEntity<?> editarUsuario(@Parameter(description = "Id del usuario a editar", example = "6", required = true )@PathVariable Long userId,
                                           @Parameter(description = "DTO con la información a editar", required = true, schema = @Schema(implementation = CuentaRequestDto.class))
                                           @RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.editarUsuario(userId, usuarioRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar el usuario con id: " + userId + ".\"}");
        }
    }

//    @GetMapping("/cercanos")
//    public ResponseEntity<?> getMonopatinesCercanos() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getMonopatinesCercanos());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
