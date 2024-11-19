package org.example.usuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.usuario.DTO.CuentaCargarSaldoDto;
import org.example.usuario.DTO.CuentaRequestDto;
import org.example.usuario.DTO.CuentaResponseDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @Operation(
            summary = "Crear una nueva cuenta",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para crear cuenta: CuentaRequestDTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CuentaRequestDto.class)
                    )
            )

    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "La cuenta ha sido creada con exito",
                            content = @Content(
                                    schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Chequee los campos e intente nuevamente"
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<?> crearCuenta(@Parameter(description = "DTO con la información necesaria para crear la cuenta", required = true ,
            schema = @Schema(implementation = CuentaResponseDto.class)) @RequestBody  @Valid CuentaRequestDto CuentaRequestDto){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(CuentaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e+"{\"error\":\"Error. No se pudo ingresar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }


    @Operation(
            summary = "Obtener una cuenta via id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cuenta obtenida",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "La cuenta no ha sido encontrada"
                    )
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCuenta(@Parameter(description = "Id de la cuenta a buscar" , example = "7") @PathVariable Long userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getCuentasByUser(userId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e+"{\"error\":\"Error. No se pudo ingresar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(
            summary = "Editar una cuenta a través de un id y el DTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para editar la cuenta: id y CuentaRequestDTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CuentaRequestDto.class)
                    )
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La cuenta se edito con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No se ha encontrado la cuenta a editar o la información esta incompleta"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCuenta(@Parameter(description = "Id de la cuenta a editar", example = "9") @PathVariable Long id,
                                          @Parameter(description = "DTO de la cuenta editada", required = true, schema = @Schema(implementation = CuentaRequestDto.class) )
                                            @RequestBody @Valid CuentaRequestDto cuentaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.editarCuenta(id, cuentaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "{\"error\":\"Error. No se pudo editar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(
            summary = "Anular una cuenta a través de un id"
    )
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Cuenta anulada con exito",
                        content = @Content(
                            schema = @Schema(implementation = CuentaResponseDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "204",
                        description = "Cuenta anulada exitosamente. No se devuelve contenido."
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Solicitud incorrecta o error al intentar anular la cuenta."
                )
        }
    )
    @PutMapping("/{id}/anular")
    public ResponseEntity<?> anularCuenta(@Parameter(description = "Id de la cuenta a anular", required = true ,example = "3") @PathVariable Long id) {
        try {
            cuentaService.anularCuenta(id);
            return ResponseEntity.status(HttpStatus.OK).build(); // Devuelve 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "{\"error\":\"Error. No se pudo anular la cuenta.\"}");
        }
    }

    @Operation(
            summary = "Operación para asignar saldo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para cargar saldo: idCuenta y CuentaCargarSaldoDTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CuentaCargarSaldoDto.class)
                    )
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Se ha asignado el saldo correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CuentaResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Chequee los campos he intente nuevamente"
                    )
            }
    )
    @PatchMapping("{idCuenta}/cargar-saldo")
    public ResponseEntity<?> cargarSaldo(@Parameter(description = "Id de la cuenta a cargar saldo", example = "3", required = true)@PathVariable Long idCuenta,
                                         @Parameter(description = "DTO con el credito a asignar", required = true, schema = @Schema(implementation = CuentaCargarSaldoDto.class) )
                                         @RequestBody CuentaCargarSaldoDto cuentaCargarSaldoDto){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.cuentaService.cargarSaldo(idCuenta,cuentaCargarSaldoDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "{\"error\":\"Error. No se pudo cargar saldo a  la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

}
