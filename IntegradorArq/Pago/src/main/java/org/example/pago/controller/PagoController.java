package org.example.pago.controller;


import org.example.pago.DTO.PagoRequestDto;
import org.example.pago.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pago")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @PostMapping("")
    public ResponseEntity<Boolean> activarMonopatin(@RequestBody PagoRequestDto pagoRequestDto) {
        try {
            boolean resultado = this.pagoService.pagar(pagoRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false); // O maneja el error como mejor se ajuste a tu caso
        }
    }



}
