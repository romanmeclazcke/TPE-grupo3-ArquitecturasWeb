 package org.example.reporte.controller;


import org.example.reporte.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reporte")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/kilometros")
    public ResponseEntity<?> obtenerReporteKilometro() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReporteKilometro());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/tiempo")
    public ResponseEntity<?> obtenerReporteTiempoSinPausa() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReporteTiempoSinPausa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/tiempo/con-pausa")
    public ResponseEntity<?> obtenerReporteTiempoConPausa() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.reporteService.obtenerReporteKilometro());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
