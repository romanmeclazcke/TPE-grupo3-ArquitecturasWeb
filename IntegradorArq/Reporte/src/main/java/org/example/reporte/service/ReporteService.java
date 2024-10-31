package org.example.reporte.service;


import org.example.reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;
}
