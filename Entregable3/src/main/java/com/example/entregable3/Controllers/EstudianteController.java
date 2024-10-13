package com.example.entregable3.Controllers;

import com.example.entregable3.Model.Estudiante;
import com.example.entregable3.Services.EstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteServices estudianteServices;

    @GetMapping("")
    public ResponseEntity<?> getAllEstudiantes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEstudianteById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el estudiante con el ID: " + id + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createEstudiante(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.save(estudiante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiante(@PathVariable Integer id, @RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.update(id, estudiante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(estudianteServices.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el estudiante, intente nuevamente.\"}");
        }
    }

    @GetMapping("/edad")
    public ResponseEntity<?> getEstudiantesByEdad() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.getEstudiantesByEdad());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al obtener los estudiantes por edad, por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> getEstudiantesByGenero(@PathVariable char genero) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServices.getEstudianteByGenero(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al obtener los estudiantes por género, por favor intente más tarde.\"}");
        }
    }
}
