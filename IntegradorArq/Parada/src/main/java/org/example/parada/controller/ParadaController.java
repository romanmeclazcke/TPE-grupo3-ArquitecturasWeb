package org.example.parada.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.parada.DTO.ParadaRequestDto;
import org.example.parada.DTO.ParadaResponseDto;
import org.example.parada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parada")
public class ParadaController {
    @Autowired
    ParadaService paradaService;

    @Operation(
            summary = "Crear una nueva parada",
            description = "Este endpoint permite crear una nueva parada",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto de la parada a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaRequestDto.class))))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Parda creada creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("")
    public ResponseEntity<?> registrarParada(@RequestBody ParadaRequestDto paradaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paradaService.save(paradaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Eliminar parada",
            description = "Este endpoint permite eliminar una nueva parada por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parada eliminada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ), @ApiResponse(
            responseCode = "404",
            description = "Parada no encontrada",
            content = @Content(mediaType = "application/json")
    )
    })
    @DeleteMapping("/{idParada}")
    public ResponseEntity<?> quitarParada(@Parameter(description = "Id de la parada a eliminar", example = "2") @PathVariable Long idParada) {
        try {
            paradaService.delete(idParada);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ Parada eliminada con éxito }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Estacionar monopatin",
            description = "Este endpoint te permite estacionar un monopatin en una parada determinada"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatin estacionado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parada no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/{idParada}/estacionar/monopatin/{idMonopatin}")
    public ResponseEntity<?> estacionarMonopatinEnParada(@Parameter(description = "Id de la parada en la cual se estacionara el monopatin", example = "1") @PathVariable Long idParada, @Parameter(description = "Id del monopatin a estacionar", example = "10") @PathVariable Long idMonopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.ubicarMonopatinEnParada(idParada, idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Retirar monopatin de parada",
            description = "Este endpoint te permite retirar un monopatin de una parada determinada"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatin retirado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parada no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PatchMapping("/{idParada}/retirar/monopatin/{idMonopatin}")
    public ResponseEntity<?> retirarMonopatinDeParada(@Parameter(description = "Id de la parada en la cual se estacionara el monopatin", example = "1") @PathVariable Long idParada, @Parameter(description = "Id del monopatin a estacionar", example = "10") @PathVariable Long idMonopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.retirarMonopatinDeParada(idParada, idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener parada por id",
            description = "Este endpoint te permite obtener informacion de una parada por su Id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informacion de la parada obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parada no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{idParada}")
    public ResponseEntity<?> getParadaById(@Parameter(description = "Id de la parada a obtener informacion", example = "2") @PathVariable Long idParada) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadaById(idParada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtenerer todas las paradas",
            description = "Este endpoint te permite obtener informacion de todas las paradas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informacion todas las parada obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paradas no encontradas",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("")
    public ResponseEntity<?> getParadas() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtener la parada que contiene un monopatin en especifico",
            description = "Este endpoint te permite obtener la parada que contiene un monopatin en especifico estacionado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Informacion de la parada obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ParadaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Parada no encontrada",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/contine-monopatin/{idMonopatin}")
    public ResponseEntity<?> getParadaContieneMonopatin(@Parameter(description = "Id del monopatin a obtenre en que para esta", example = "2") @PathVariable Long idMonopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadaContieneMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
