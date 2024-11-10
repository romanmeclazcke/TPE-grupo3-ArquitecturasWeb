package org.example.reporte.service;


import org.example.reporte.DTO.ReporteResponseDto;
import org.example.reporte.Model.Monopatin;
import org.example.reporte.feignClients.MonopatinFeignClient;
import org.example.reporte.feignClients.ViajeFeignClient;
import org.example.reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;
    @Autowired
    ViajeFeignClient viajeFeignClient;

    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    public List<ReporteResponseDto> obtenerReporteKilometro() {
        List<org.example.reporte.Model.Monopatin> monopatines = this.monopatinFeignClient.getMonopatinesPorKilometros();
        List<ReporteResponseDto> reporteKilometros = new ArrayList<>();
        for (org.example.reporte.Model.Monopatin monopatin : monopatines) {
            reporteKilometros.add(this.mapearEntidadADto(monopatin));
        }
        return reporteKilometros;
    }


    public List<ReporteResponseDto> obtenerReporteTiempoSinPausa() {
        List<org.example.reporte.Model.Monopatin> monopatines = this.monopatinFeignClient.getMonopatinesSinTiempoPausa();
        List<ReporteResponseDto> reporteKilometros = new ArrayList<>();
        for (org.example.reporte.Model.Monopatin monopatin : monopatines) {
            reporteKilometros.add(this.mapearEntidadADto(monopatin));
        }
        return reporteKilometros;
    }


    public List<ReporteResponseDto> obtenerReporteTiempoConPausa() {
        List<org.example.reporte.Model.Monopatin> monopatines = this.monopatinFeignClient.getMonopatinesConTiempoPausa();
        List<ReporteResponseDto> reporteKilometros = new ArrayList<>();
        for (org.example.reporte.Model.Monopatin monopatin : monopatines) {
            reporteKilometros.add(this.mapearEntidadADto(monopatin));
        }
        return reporteKilometros;
    }



    private ReporteResponseDto mapearEntidadADto(Monopatin m) {
        ReporteResponseDto dto = new ReporteResponseDto();
        dto.setIdMonopatin(m.getId());
        dto.setKilometros(m.getKilometros());
        dto.setTiempo_uso(m.getTiempo_uso());
        return dto;
    }
}
