package org.example.reporte.controller;


import org.example.reporte.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reporte")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("")
    public ResponseEntity<?> obtenerReportePorTipo(@RequestParam(required = true) String tipo, @RequestParam(required = true) Long idMonopatin) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReportePorTipo(tipo, idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
