package org.example.mantenimiento.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.DTO.MantenimientoResponseDto;
import org.example.mantenimiento.service.MantenimientoService;
import org.example.mantenimiento.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    MantenimientoService mantenimientoService;

    @Operation(
            summary = "Crear un nuevo mantenimiento",
            description = "Asigna un monopatin a mantenimiento, a partir de su id y MantenimientoDTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para registrar el mantenimiento: idMonopatin y mantenimientoDTO",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation = MantenimientoRequestDto.class)
                    )

            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Mantenimiento registrado con exito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation = MantenimientoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado el monopatin"
            )
    })
    @PostMapping("/{idMonopatin}")
    public ResponseEntity<?> createMantenimiento(@Parameter(description = "Id del monopatin a iniciar mantenimiento", example = "9" , required = true) @PathVariable Long idMonopatin,
                                                 @Parameter(description = "Id del monopatin a finalizar mantenimiento", example = "9" , required = true,
                                                         schema = @Schema( implementation = MantenimientoRequestDto.class ))
                                                 @RequestBody @Valid MantenimientoRequestDto mantenimientoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mantenimientoService.save(idMonopatin, mantenimientoDTO));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
            summary = "Finalizar estado de mantenimiento"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se finalizado el mantenimiento con exito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation = MantenimientoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontro el monopatin a finalizar mantenimiento"
            )
    })
    @PatchMapping("/finalizar/{idMonopatin}")
    public ResponseEntity<?> finMantenimiento(@Parameter(description = "Id del monopatin a finalizar mantenimiento", example = "9" , required = true) @PathVariable Long idMonopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.endMantenimiento(idMonopatin));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(
            summary = "Verificar estado de mantenimiento",
            description = "En base a un id verifica si se encuentra en mantenimiento o no"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatin encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation = MantenimientoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado el id del monopatin"
            )
    })
    @GetMapping("/{idMonopatin}")
    public ResponseEntity<?> elMonopatinSeEncuentraEnMantenimiento(@Parameter(description = "Id del monopatin a verificar estado", example = "15", required = true) @PathVariable Long idMonopatin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.elMonopatinSeEncuentraEnMantenimiento(idMonopatin));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
