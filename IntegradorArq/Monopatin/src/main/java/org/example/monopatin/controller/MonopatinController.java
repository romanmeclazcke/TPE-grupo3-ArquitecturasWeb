package org.example.monopatin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.monopatin.DTO.MonopatinDisponibilidadDTO;
import org.example.monopatin.DTO.MonopatinRequestDto;
//import org.example.monopatin.feignClient.ViajeFeignClient;
import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.DTO.MonopatinSumaKilometrosDto;
import org.example.monopatin.feignClient.ViajeFeignClient;
import org.example.monopatin.service.MonopatinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    @Autowired
    MonopatinServices monopatinServices;

    @Operation(
            summary = "Crear un nuevo monopatín",
            description = "Este endpoint permite crear un nuevo monopatín",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto de datos del monopatín a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MonopatinRequestDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Monopatín creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MonopatinResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud incorrecta",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("")
    public ResponseEntity<?> crearMonopatin(@RequestBody @Valid MonopatinRequestDto monopatinRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.save(monopatinRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Endpoint para eliminar un monopatin por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue eliminado correctamente", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "El monopatin no fue encontrado", content = @Content),
    })
    @DeleteMapping("/{monopatinId}")
    public ResponseEntity<?> delete(@Parameter(description = "Id del monopatin a eliminar", example = "1") @PathVariable Long monopatinId) throws Exception {
        try {
            monopatinServices.delete(monopatinId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ Monopatin eliminado con exito }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(summary = "Editar un nuevo monopatín",
            description = "Este endpoint permite editar un nuevo monopatín existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto de datos del monopatín a crear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MonopatinRequestDto.class)
                    )
            ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue editado correctamente", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "El monopatin no fue encontrado", content = @Content),
    })
    @PutMapping("/{monopatinId}")
    public ResponseEntity<?> editarMonopatin(@Parameter(description = "Id del monopatin a editar", example = "1") @PathVariable Long monopatinId, @RequestBody @Valid MonopatinRequestDto monopatinRequestDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.editarMonopatin(monopatinId, monopatinRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Endpoint para obtener todos los monopatines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue eliminado correctamente", content = {@Content( mediaType = "application/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines", content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Endpoint para obtener un monopatin por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue encontrado", content = {@Content( mediaType = "application/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines", content = @Content),
    })
    @GetMapping("/{monopatinId}")
    public ResponseEntity<?> getById(@Parameter(description = "Id del monopatin a obtener", example = "1")@PathVariable Long monopatinId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getById(monopatinId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(summary = "Endpoint para activar un monopatin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatin activado y viaje creado con exito", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontro uno de los recursos especificados ", content = @Content),
    })
    @PostMapping("/activar/{monopatinId}/usuarioId/{usuarioId}/paradaDestino/{paradaId}")
    public ResponseEntity<?> activarMonopatin(@Parameter(description = "Id del monopatin", example = "1")@PathVariable Long monopatinId, @Parameter(description = "Id del usuario", example = "2")@PathVariable Long usuarioId,@Parameter(description = "Id de la parada destino", example = "1") @PathVariable Long paradaId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.activarMonopatin(monopatinId, usuarioId, paradaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Endpoint para sumar kilometros a un monopatin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resumen obtenido con exito", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinSumaKilometrosDto.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines", content = @Content),
    })
    @PatchMapping("/{idMonopatin}/sumar/kilometros")
    public ResponseEntity<?> sumarKilometros(@Parameter(description = "id del monopatin a sumar kiloemtros", example = "1")@PathVariable Long idMonopatin, @RequestBody MonopatinSumaKilometrosDto monopatinSumaKilometrosDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.monopatinServices.sumarKilometros(idMonopatin, monopatinSumaKilometrosDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para obtener monopatines en un radio de 1km a una posición",
            description = "Este endpoint permite obtener una lista de monopatines cercanos a una posición específica, dentro de un radio de 1km."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue editado correctamente", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "El monopatin no fue encontrado", content = @Content),
    })
    @GetMapping("/cercanos-a-posicion/{x}/{y}")
    public ResponseEntity<?> getMonopatinesEnRadio1km(
            @PathVariable Integer x,
            @PathVariable Integer y
    ) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesEnRadio1km(x, y));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para obtener monopatines ordenados por kilómetros",
            description = "Este endpoint devuelve una lista de monopatines ordenados por los kilómetros recorridos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "El monopatin fue editado correctamente", content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "El monopatin no fue encontrado", content = @Content),
    })
    @GetMapping("/order/kilometros")
    public ResponseEntity<?> getMonopatinesPorKilometros() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesPorKilometros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(
            summary = "Endpoint para obtener monopatines ordenados por tiempo de uso con pausa",
            description = "Este endpoint devuelve una lista de monopatines ordenados por el tiempo total de uso, incluyendo pausas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de monopatines ordenados por tiempo de uso con pausa",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonopatinResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron monopatines",
                    content = @Content
            )
    })
    @GetMapping("/order/tiempo-uso/con-pausa")
    public ResponseEntity<?> getMonopatinesConTiempoPausa() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesPorKilometros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(
            summary = "Endpoint para obtener monopatines ordenados por tiempo de uso sin pausa",
            description = "Este endpoint devuelve una lista de monopatines ordenados por el tiempo total de uso sin contar las pausas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de monopatines ordenados por tiempo de uso sin pausa",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MonopatinResponseDto.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontraron monopatines",
                    content = @Content
            )
    })
    @GetMapping("/order/tiempo-uso/sin-pausa")
    public ResponseEntity<?> getMonopatinesSinTiempoPausa() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesSinTiempoPausa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @Operation(
            summary = "Endpoint para obtener la disponibilidad de monopatines",
            description = "Este endpoint devuelve la disponibilidad de los monopatines, mostrando si están en uso o disponibles."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Disponibilidad de monopatines",
                    content = {@Content( mediaType = "applicatin/json", schema = @Schema(implementation = MonopatinDisponibilidadDTO.class))}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se encontró información sobre la disponibilidad",
                    content = @Content
            )
    })
    @GetMapping("/order/disponibilidad")
    public ResponseEntity<?> getDisponibilidad() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getDisponibilidad());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
