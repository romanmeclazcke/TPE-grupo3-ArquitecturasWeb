package org.example.mantenimiento.controller;

import jakarta.validation.Valid;
import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.service.MantenimientoService;
import org.example.mantenimiento.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    MantenimientoService mantenimientoService;

    @PostMapping("/{idMonopatin}")
    public ResponseEntity<?> createMantenimiento(@PathVariable Long idMonopatin, @RequestBody @Valid MantenimientoRequestDto mantenimientoDTO) {
        System.out.println(idMonopatin);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mantenimientoService.save(idMonopatin, mantenimientoDTO));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
