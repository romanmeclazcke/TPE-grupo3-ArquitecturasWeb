package com.example.entregable3.Controllers;


import com.example.entregable3.DTO.ReporteCarreraDto;
import com.example.entregable3.Model.Carrera;
import com.example.entregable3.Services.CarreraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private  CarreraServices carreraServices;

    // Obtener todas las carreras
    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraServices.findAll();
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public Carrera getCarreraById(@PathVariable Integer id) throws Exception {
        return carreraServices.findById(id);
    }

    // Crear una nueva carrera
    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) throws Exception {
        return carreraServices.save(carrera);
    }

    // Actualizar una carrera existente
    @PutMapping("/{id}")
    public Carrera updateCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) throws Exception {
        return carreraServices.update(id, carrera);
    }

    // Eliminar una carrera por ID
    @DeleteMapping("/{id}")
    public String deleteCarrera(@PathVariable Integer id) throws Exception {
        if (carreraServices.delete(id)) {
            return "Carrera eliminada con éxito.";
        } else {
            throw new Exception("No se pudo eliminar la carrera con ID: " + id);
        }
    }

    // Obtener reportes de carrera personalizados
    @GetMapping("/reportes")
    public List<ReporteCarreraDto> getReportesCarrera() {
        return carreraServices.getReportesCarrera();
    }


}

