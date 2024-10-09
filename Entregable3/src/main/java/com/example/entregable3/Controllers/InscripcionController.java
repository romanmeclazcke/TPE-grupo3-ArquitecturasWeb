package com.example.entregable3.Controllers;


import com.example.entregable3.DTO.CarreraConNumInscriptosDto;
import com.example.entregable3.DTO.EstudianteDTO;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Model.Inscripcion;
import com.example.entregable3.Services.InscripcionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionServices inscripcionServices;

    @GetMapping("")
    public ResponseEntity<?> getAllInscripciones() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInscripcionById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra la inscripción con el ID: " + id + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createInscripcion(@RequestBody Inscripcion inscripcion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.save(inscripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar la inscripción, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInscripcion(@PathVariable Integer id, @RequestBody Inscripcion inscripcion) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.update(id, inscripcion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la inscripción, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInscripcion(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(inscripcionServices.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la inscripción, intente nuevamente.\"}");
        }
    }

    @GetMapping("/carreras-inscriptos")
    public ResponseEntity<?> getCarreraConNumInscriptos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.getCarreraConNumInscriptosDto());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al obtener las carreras con el número de inscriptos, por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/estudiantes/{ciudad}/{carrera}")
    public ResponseEntity<?> getEstudiantesByCarreraAndCiudad(@PathVariable String ciudad, @PathVariable Carrera carrera) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServices.getEstudiantesByCarreraAndCiudad(ciudad, carrera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al obtener los estudiantes por carrera y ciudad, por favor intente más tarde.\"}");
        }
    }
}
