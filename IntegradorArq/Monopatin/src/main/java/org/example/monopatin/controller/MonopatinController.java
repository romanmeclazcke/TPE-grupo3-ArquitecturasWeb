package org.example.monopatin.controller;

import org.example.monopatin.DTO.MonopatinRequestDto;
//import org.example.monopatin.feignClient.ViajeFeignClient;
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
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.save(MonopatinRequestDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{monopatinId}")
    public ResponseEntity<?> delete(@PathVariable Long monopatinId) throws Exception {
        try{
            monopatinServices.delete(monopatinId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{ Monopatin eliminado con exito }");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{monopatinId}")
    public ResponseEntity<?> editarMonopatin(@PathVariable Long monopatinId, @RequestBody @Valid MonopatinRequestDto monopatinRequestDto) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.editarMonopatin(monopatinId,monopatinRequestDto));
        } catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{monopatinId}")
    public ResponseEntity<?> getById(@PathVariable Long monopatinId) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getById(monopatinId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/activar/{monopatinId}/usuarioId/{usuarioId}")
    public ResponseEntity<?> activarMonopatin(@PathVariable Long monopatinId,@PathVariable Long usuarioId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.activarMonopatin(monopatinId, usuarioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
