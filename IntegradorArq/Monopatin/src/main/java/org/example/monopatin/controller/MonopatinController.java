package org.example.monopatin.controller;

import org.example.monopatin.DTO.MonopatinRequestDto;
//import org.example.monopatin.feignClient.ViajeFeignClient;
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

    @PostMapping("")
    public ResponseEntity<?> crearMonopatin(@RequestBody @Valid MonopatinRequestDto MonopatinRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.save(MonopatinRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{monopatinId}")
    public ResponseEntity<?> delete(@PathVariable Long monopatinId) throws Exception {
        try {
            monopatinServices.delete(monopatinId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ Monopatin eliminado con exito }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{monopatinId}")
    public ResponseEntity<?> editarMonopatin(@PathVariable Long monopatinId, @RequestBody @Valid MonopatinRequestDto monopatinRequestDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.editarMonopatin(monopatinId, monopatinRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{monopatinId}")
    public ResponseEntity<?> getById(@PathVariable Long monopatinId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getById(monopatinId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/activar/{monopatinId}/usuarioId/{usuarioId}/paradaDestino/{paradaId}")
    public ResponseEntity<?> activarMonopatin(@PathVariable Long monopatinId, @PathVariable Long usuarioId, @PathVariable Long paradaId) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.activarMonopatin(monopatinId, usuarioId, paradaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PatchMapping("/{idMonopatin}/sumar/kilometros")
    public ResponseEntity<?> sumarKilometros(@PathVariable Long idMonopatin, @RequestBody MonopatinSumaKilometrosDto monopatinSumaKilometrosDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.monopatinServices.sumarKilometros(idMonopatin, monopatinSumaKilometrosDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/cercanos-a-posicion/{x}/{y}")
    public ResponseEntity<?> getMonopatinesEnRadio1km(@PathVariable Integer x, @PathVariable Integer y) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesEnRadio1km(x, y));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/order/kilometros")
    public ResponseEntity<?> getMonopatinesPorKilometros() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesPorKilometros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/order/tiempo-uso/con-pausa")
    public ResponseEntity<?> getMonopatinesConTiempoPausa() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesPorKilometros());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/order/tiempo-uso/sin-pausa")
    public ResponseEntity<?> getMonopatinesSinTiempoPausa() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getMonopatinesSinTiempoPausa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/order/disponibilidad")
    public ResponseEntity<?> getDisponibilidad() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getDisponibilidad());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
