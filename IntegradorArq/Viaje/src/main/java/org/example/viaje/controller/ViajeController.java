package org.example.viaje.controller;


import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @PostMapping("monopatinId/{monopatinId}/usuarioId/{usuarioId}")
    public ResponseEntity<?> createViaje(@PathVariable Long monopatinId, @PathVariable Long usuarioId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(monopatinId,usuarioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
