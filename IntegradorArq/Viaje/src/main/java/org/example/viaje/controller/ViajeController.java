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

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
    public ResponseEntity<?> createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(monopatinId,usuarioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
