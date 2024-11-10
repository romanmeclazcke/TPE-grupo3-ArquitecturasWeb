package org.example.viaje.controller;


import org.apache.coyote.Response;
import org.example.viaje.DTO.TarifaRequestDto;
import org.example.viaje.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarifa")
public class TarifaController {

    @Autowired
    TarifaService tarifaService;


    @GetMapping("/")
    public ResponseEntity<?> getAllTarifas() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/plazo-valido")
    public ResponseEntity<?> getTarifaEnPlazoValido() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.getTarifaEnPlazoValido());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> crateTarifa(@RequestBody TarifaRequestDto tarifaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.crearTarfia(tarifaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
