package org.example.mapa.controller;

import org.example.mapa.DTO.UbicacionRequestDto;
import org.example.mapa.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapa")
public class MapsController {


    @Autowired
    MapsService mapsService;

    @GetMapping("/distancia/origen/{idParadaOrigen}/destino/{idParadaDestino}")
    public ResponseEntity<?> getDistanciasEntreParadas(@PathVariable Long idParadaOrigen, @PathVariable Long idParadaDestino){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.mapsService.getDistanciaEntreParadas(idParadaOrigen,idParadaDestino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/obtener-monopatin")
    public ResponseEntity<?> getMonopatinByUbicacion(@RequestBody UbicacionRequestDto ubicacionRequestDto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.mapsService.getMonopatinByUbicacion(ubicacionRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
