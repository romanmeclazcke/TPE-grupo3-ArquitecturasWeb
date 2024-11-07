package org.example.parada.controller;

import org.example.parada.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parada")
public class ParadaController {
    @Autowired
    ParadaService paradaService;

    @PostMapping("")
    public ResponseEntity<?> registrarParada() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paradaService.save());
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


    @PostMapping("/{idParada}/estacionar/monopatin/{idMonopatin}")
    public ResponseEntity<?> estacionarMonopatinEnParada(@PathVariable Long idParada, @PathVariable Long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.ubicarMonopatinEnParada(idParada, idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{idParada}/retirar/monopatin/{idMonopatin}")
    public ResponseEntity<?> retirarMonopatinDeParada(@PathVariable Long idParada, @PathVariable Long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.retirarMonopatinDeParada(idParada, idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idParada}")
    public ResponseEntity<?> getParadaById(@PathVariable Long idParada){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadaById(idParada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getParadas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/contine-monopatin/{idMonopatin}")
    public ResponseEntity<?> getParadaContieneMonopatin(@PathVariable Long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.paradaService.getParadaContieneMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
