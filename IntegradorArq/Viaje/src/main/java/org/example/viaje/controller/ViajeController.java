package org.example.viaje.controller;


import org.example.viaje.DTO.ViajeRequestDTO;
import org.example.viaje.feignClients.ParadaFeignClient;
import org.example.viaje.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @Autowired
    ParadaFeignClient paradaFeignClient;

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}/paradaDestino/{paradaId}")
    public ResponseEntity<?> createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId, @PathVariable("paradaId") Long paradaId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeService.save(monopatinId,usuarioId,paradaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verificar-parada/{paradaId}")
    public ResponseEntity<?> verificarParada(@PathVariable("paradaId") Long paradaId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaFeignClient.getParadaById(paradaId));
        } catch(Exception e){
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


    @GetMapping("/monopatin/{idMonopatin}")
    public ResponseEntity<?> getViajesPorMonopatin(@PathVariable Long idMonopatin) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajesPorMonopatin(idMonopatin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/monopatines/viajes/{cant}/anio/{anio}")
    public ResponseEntity<?> getMonopatinesConMasDeXViajes(@PathVariable("cant") int cant, @PathVariable("anio") int anio) {
        try {
            List<Long> monopatines = viajeService.getMonopatinesConMasDeXViajes(anio, cant);
            return ResponseEntity.status(HttpStatus.OK).body(monopatines);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
