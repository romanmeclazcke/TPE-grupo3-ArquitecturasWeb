package org.example.usuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener todos los usuarios.\"}");
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuarioById(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener el usuario con id :"+userId+"."+"\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid UsuarioRequestDto UsuarioRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(UsuarioRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar e;l usuario, revise los campos e intente nuevamente.\"}");
        }
    }



    @PostMapping("/{userId}/agregar-cuenta/{cuentaId}")
    public ResponseEntity<?> agregarCuenta(@PathVariable Long userId,@PathVariable Long cuentaId){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarCuenta(userId,cuentaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar e;l usuario, revise los campos e intente nuevamente.\"}");
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long userId) {
        try {
            usuarioService.eliminarUsuario(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ Usuario eliminado }"); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el usuario con id: " + userId + ".\"}");
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long userId, @RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.editarUsuario(userId, usuarioRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar el usuario con id: " + userId + ".\"}");
        }
    }

//    @GetMapping("/cercanos")
//    public ResponseEntity<?> getMonopatinesCercanos() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getMonopatinesCercanos());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
