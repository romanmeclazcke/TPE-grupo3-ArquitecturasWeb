package com.example.entregable3.Controllers;


import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Services.CarreraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private  CarreraServices carreraServices;

    @GetMapping("")
    public ResponseEntity<?> getAllCarreras() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraServices.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarreraById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraServices.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra la carrera con el ID: " + id + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createCarrera(@RequestBody Carrera carrera) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraServices.save(carrera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar la carrera, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraServices.update(id, carrera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar la carrera, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carreraServices.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la carrera, intente nuevamente.\"}");
        }
    }

    @GetMapping("/reportes")
    public ResponseEntity<?> getReportesCarrera() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraServices.getReportesCarrera());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al obtener los reportes, por favor intente más tarde.\"}");
        }
    }


}

