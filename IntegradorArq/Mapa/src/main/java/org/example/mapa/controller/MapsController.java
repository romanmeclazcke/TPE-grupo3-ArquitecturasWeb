package org.example.mapa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.mapa.DTO.DistanciasResponseDto;
import org.example.mapa.DTO.MonopatinResponseDto;
import org.example.mapa.DTO.UbicacionRequestDto;
import org.example.mapa.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapa")
public class MapsController {

    @Autowired
    MapsService mapsService;

    @Operation(summary = "Endpoint para obtener la distancia entre 2 paradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La Distancia fue calculada correctamente",
                    content = {@Content(mediaType = "applicatin/json", schema = @Schema(implementation = DistanciasResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Una o ambas paradas no fueron encontradas", content = @Content),
    })
    @GetMapping("/distancia/origen/{idParadaOrigen}/destino/{idParadaDestino}")
    public ResponseEntity<?> getDistanciasEntreParadas(@Parameter(description = "Id de la parada", example = "2") @PathVariable Long idParadaOrigen,@Parameter(description = "Id de la parada", example = "10") @PathVariable Long idParadaDestino) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.mapsService.getDistanciaEntreParadas(idParadaOrigen, idParadaDestino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener monopatines cercanos a una cordenada",
            description = "Este endpoint te permite obtener todos los monopatines cercados a una cordenada x e y",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto de datos del monopatín a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UbicacionRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Los monopatines fueron encontrados",
                    content = {@Content(mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines cercanos", content = @Content),
    })
    @GetMapping("/obtener-monopatin")
    public ResponseEntity<?> getMonopatinByUbicacion(@RequestBody UbicacionRequestDto ubicacionRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.mapsService.getMonopatinByUbicacion(ubicacionRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
