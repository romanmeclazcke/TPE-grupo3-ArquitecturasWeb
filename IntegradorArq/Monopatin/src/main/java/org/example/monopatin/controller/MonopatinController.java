package org.example.monopatin.controller;

import org.example.monopatin.DTO.MonopatinRequestDto;
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

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(monopatinServices.getAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
