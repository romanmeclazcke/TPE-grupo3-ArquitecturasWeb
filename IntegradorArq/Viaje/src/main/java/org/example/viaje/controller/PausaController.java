package org.example.viaje.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.viaje.DTO.PausaResponseDto;
import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.service.PausaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pausa")
public class PausaController {
    @Autowired
    PausaService pausaService;

    @Operation(
            summary = "",
            description = "",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para crear una nueva pausa: id del viaje asociado (idViaje)",
                    required = true,
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Long.class)
                    )
            )

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se ha creado correctamente la pausa",
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PausaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Revise los campos e intente nuevamente",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("viaje/{idViaje}")
    public ResponseEntity<?> crearPausa(@Parameter(description = "El id del viaje al cual se realiza la pausa", example = "3") @PathVariable Long idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.crearPausa(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para obtener las pausas asociadas a un viaje",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Información requerida para obtener las pausas de un viaje: idViaje",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PausaResponseDto.class)
                    )
            )
    )
    @GetMapping("/viaje/{idViaje}")
    public ResponseEntity<?> getPausasPorViaje(@Parameter(description = "El id del viaje al cual se realiza la pausa", example = "3") @PathVariable Long idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.getPausasPorViaje(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
