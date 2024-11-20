 package org.example.reporte.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.reporte.DTO.ReporteResponseDto;
import org.example.reporte.service.ReporteService;
import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.DTO.ViajeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reporte")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @Operation(
            summary = "Obtener reporte de monopatines por kilometros",
            description = "Endpoint para obtener un reporte de los monopatines ordenados por kiloemtros recorridos"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "El reporte se genero con exito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReporteResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Chequee los campos e intente nuevamente",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/kilometros")
    public ResponseEntity<?> obtenerReporteKilometro() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReporteKilometro());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines por tiempo de uso",
            description = "Endpoint para obtener un reporte de los monopatines ordenados tiempo de uso, se podra configurar para incluir o no los tiempos de pausas"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "El reporte se genero con exito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReporteResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Chequee los campos e intente nuevamente",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/tiempo")
    public ResponseEntity<?> obtenerReporteTiempo(@RequestParam(value = "pausas", defaultValue = "false") boolean pausas) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReporteTiempo(pausas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
