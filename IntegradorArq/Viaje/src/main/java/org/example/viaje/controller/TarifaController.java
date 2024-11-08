package org.example.viaje.controller;

import jakarta.validation.Valid;
import org.example.viaje.DTO.TarifaRequestDto;
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

    @PostMapping("")
    public ResponseEntity<?> createTarifa(@RequestBody @Valid TarifaRequestDto tarifaRequestDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.crearTarfia(tarifaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idTarifa}")
    public ResponseEntity<?> getTarifa(@PathVariable Long idTarifa) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getById(idTarifa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
