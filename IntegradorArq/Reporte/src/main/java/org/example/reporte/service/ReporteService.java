package org.example.reporte.service;

import org.example.reporte.DTO.ReporteDto;
import org.example.reporte.feignClients.MonopatinFeignClient;
import org.example.reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    public ReporteDto obtenerReportePorTipo(String tipoReporte){
        ReporteDto reporteDto = new ReporteDto();
        if(tipoReporte!="kilometros" || tipoReporte!="conPausa"||tipoReporte!="sinPausa"){
             reporteDto.setExito(false);
             reporteDto.setMensaje("Solo podes filtar por kilometors, conPausa, SinPausa");
        }
        reporteDto.setMontopatines(this.monopatinFeignClient.getMonopatinesOrdenadosPor(tipoReporte));
        return reporteDto;
    }
}
