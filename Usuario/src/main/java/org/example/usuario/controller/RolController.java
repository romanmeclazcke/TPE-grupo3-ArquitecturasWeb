package org.example.usuario.controller;


import org.example.usuario.DTO.RolRequestDto;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    RolService rolService;

    @PostMapping("")
    public ResponseEntity<?> crearRol(@RequestBody RolRequestDto rolRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rolService.save(rolRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear el rol, revise los campos e intente nuevamente.\"}");
        }
    }

}
