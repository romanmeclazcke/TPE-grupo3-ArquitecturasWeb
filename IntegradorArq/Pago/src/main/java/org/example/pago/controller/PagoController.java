package org.example.pago.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.pago.DTO.PagoRequestDto;
import org.example.pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pago")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @Operation(
            summary = "Crear un pago asociado a un viaje",
            description = "Este endpoint permite generar un pago asociado a un viaje",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto del pago a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PagoRequestDto.class))))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pago creado creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("")
    public ResponseEntity<Boolean> activarMonopatin(@RequestBody PagoRequestDto pagoRequestDto) {
        try {
            boolean resultado = this.pagoService.pagar(pagoRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false); // O maneja el error como mejor se ajuste a tu caso
        }
    }


    @Operation(
            summary = "Obtener un monto total facturado entre un periodo de tiempo",
            description = "Este endpoint permite obtener un monto de facturacion entre 2 fechas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monto facuturado generado  correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Double.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/anio/{anio}/entre/{mesAnterior}/{mesPosterior}")
    public ResponseEntity<?> getTotalFacturadoEntre(@Parameter(description = "Anio por el cual filtrar la facturacion", example = "2024") @PathVariable("anio") int anio,@Parameter(description = "Numero del mes por el cual comenzar a recolectar facturacion", example = "1") @PathVariable("mesAnterior") int mesAnterior, @Parameter(description = "Mes donde dejar de contar facturacion", example = "20")@PathVariable("mesPosterior") int mesPosterior) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pagoService.getTotalFacturadoEntre(anio,mesAnterior,mesPosterior));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
