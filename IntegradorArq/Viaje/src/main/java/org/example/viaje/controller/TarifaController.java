package org.example.viaje.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.example.viaje.DTO.TarifaRequestDto;
import org.example.viaje.DTO.TarifaResponseDto;
import org.example.viaje.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {

    @Autowired
    TarifaService tarifaService;

    @Operation(
            summary = "Obtener todas las tarifas"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron correctamente todos los viajes",
                    content = {@Content(
                                mediaType = "application/json",
                                schema = @Schema(type = "array", implementation = TarifaResponseDto.class)
                    )}
            )
    })
    @GetMapping("/")
    public ResponseEntity<?> getAllTarifas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener la tarifa normal más proxima a la fecha actual"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo la tarifa normal correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema (type = "array", implementation = TarifaResponseDto.class)
                    )
            )
    })
    @GetMapping("/plazo-valido")
    public ResponseEntity<?> getTarifaNormalEnPlazoValido() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getTarifaNormalEnPlazoValido());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener la tarifa extra más proxima a la fecha actual"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvo la tarifa extra correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema (type = "array", implementation = TarifaResponseDto.class)
                    )
            )
    })
    @GetMapping("/extra/plazo-valido")
    public ResponseEntity<?> getTarifaExtraEnPlazoValido() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getTarifaExtraEnPlazoValido());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(
            summary = "Crear un nuevo tipo de tarifa",
            description = "Endpoint para crear una nueva tarifa a partir de su DTO",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para crear la tarifa: TarifaDto",
                    required = true,
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = TarifaRequestDto.class)
                    )
            )
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarifa creada con exito",
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = TarifaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Chequee los campos e intente nuevamente",
                    content = @Content( mediaType =  "application/json" )
            )
    })
    @PostMapping("")
    public ResponseEntity<?> crateTarifa(@Parameter(
            description = "DTO con la información necesaria para crear una tarifa",
            required = true,
            schema = @Schema(implementation = TarifaRequestDto.class))@RequestBody TarifaRequestDto tarifaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.crearTarfia(tarifaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
