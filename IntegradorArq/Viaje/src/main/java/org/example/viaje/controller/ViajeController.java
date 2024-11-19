package org.example.viaje.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.DTO.ViajeResponseDTO;
import org.example.viaje.Model.Parada;
import org.example.viaje.feignClients.ParadaFeignClient;
import org.example.viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @Autowired
    ParadaFeignClient paradaFeignClient;

    @Operation(
            summary = "Crear un nuevo viaje",
            description = "Endpoint para crear un nuevo viaje a partir de un monopatin, usuario y una parada"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "El Viaje ha sido creado con éxito",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ViajeResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Chequee los campos e intente nuevamente",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}/paradaDestino/{paradaId}")
    public ResponseEntity<?> createViaje(@Parameter(description = "El id del monopatin asignado al viaje" , example = "3") @PathVariable("monopatinId") Long monopatinId,
                                         @Parameter(description = "El id del usuario que inicio el viaje" , example = "14") @PathVariable("usuarioId") Long usuarioId,
                                         @Parameter(description = "El id de la parada donde sera dejado el monopatin" , example = "9") @PathVariable("paradaId") Long paradaId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(monopatinId,usuarioId,paradaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para verificar la existencia de una parada"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se verifico la existencia de la parada",
                    content =  @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Parada.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No se encontro la parada indicada",
                    content =  @Content()
            )
    })
    @GetMapping("/verificar-parada/{paradaId}")
    public ResponseEntity<?> verificarParada(@Parameter(description = "El id de la parada a verificar" , example = "5") @PathVariable("paradaId") Long paradaId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaFeignClient.getParadaById(paradaId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para obtener todos los viajes"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtuvieron todos los viajes",
                    content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array" ,implementation = ViajeResponseDTO.class)
                    )
            )
    })
    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para obtener los viajes asociados a un monopatin"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retorna una lista de los viajes realizados por el monopatin (idMonopatin)",
                    content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array" , implementation = ViajeResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El id indicado de monopatin no pudo ser encontrado",
                    content = @Content()
            )
    })
    @GetMapping("/monopatin/{idMonopatin}")
    public ResponseEntity<?> getViajesPorMonopatin(@Parameter(description = "El id del monopatin a obtener los viajes",example = "1")@PathVariable Long idMonopatin) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajesPorMonopatin(idMonopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Obtiene un reporte dada una cantidad y un año ",
            description = "Este endpoint permite obtener los monopatines que han realizado mas de (x) cantidad de viajes en un año (y)" +
                    "ambos dados por el usuario. "
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se retorno el reporte de monopatines que cumplen la condicion",
                    content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array" , implementation = Long.class)
                    )
            )
    })
    @GetMapping("/monopatines/viajes/{cant}/anio/{anio}")
    public ResponseEntity<?> getMonopatinesConMasDeXViajes(@Parameter(description = "La cantidad minima de viajes del monopatin", example = "3")@PathVariable("cant") int cant,
                                                           @Parameter(description = "El año en que se realizaron los viajes" , example = "2024")@PathVariable("anio") int anio) {
        try {
            List<Long> monopatines = viajeService.getMonopatinesConMasDeXViajes(anio, cant);
            return ResponseEntity.status(HttpStatus.OK).body(monopatines);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para terminar un viaje"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "El viaje se ha terminado correctamente",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El viaje indicado no ha podido ser encontrado o cerrado",
                    content = @Content()
            )
    })
    @PostMapping("/cerrar/{viajeId}")
    public ResponseEntity<?> cerrarViaje(@Parameter(description = "El id del viaje a cerrar", example = "1") @PathVariable("viajeId") Long viajeId) {
        try {
            this.viajeService.endViaje(viajeId);
            return ResponseEntity.status(HttpStatus.OK).body("Viaje cerrado");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
