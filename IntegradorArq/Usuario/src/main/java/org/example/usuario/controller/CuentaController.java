package org.example.usuario.controller;

import jakarta.validation.Valid;
import org.example.usuario.DTO.CuentaRequestDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @PostMapping("")
    public ResponseEntity<?> crearCuenta(@RequestBody  @Valid CuentaRequestDto CuentaRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.save(CuentaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e+"{\"error\":\"Error. No se pudo ingresar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }



    @GetMapping("/{userId}")
    public ResponseEntity<?> getCuenta(@PathVariable Long userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.getCuentasByUser(userId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e+"{\"error\":\"Error. No se pudo ingresar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCuenta(@PathVariable Long id, @RequestBody @Valid CuentaRequestDto cuentaRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cuentaService.editarCuenta(id, cuentaRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "{\"error\":\"Error. No se pudo editar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long id) {
        try {
            cuentaService.eliminarCuenta(id);
            return ResponseEntity.status(HttpStatus.OK).build(); // Devuelve 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e + "{\"error\":\"Error. No se pudo eliminar la cuenta, revise los campos e intente nuevamente.\"}");
        }
    }
}
