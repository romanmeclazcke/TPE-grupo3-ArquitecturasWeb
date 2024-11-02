package org.example.parada.controller;

import jakarta.validation.Valid;
import org.example.parada.DTO.ParadaRequestDto;
import org.example.parada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paradas")
public class ParadaController {
    @Autowired
    ParadaService paradaService;

    @PostMapping("")
    public ResponseEntity<?> registrarParada(@RequestBody @Valid ParadaRequestDto paradaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paradaService.save(paradaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idParada}")
    public ResponseEntity<?> quitarParada(@PathVariable Long idParada) {
        try {
            paradaService.delete(idParada);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ Parada eliminada con éxito }");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
