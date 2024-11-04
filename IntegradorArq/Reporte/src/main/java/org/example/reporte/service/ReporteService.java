package org.example.reporte.service;


import org.example.reporte.DTO.ReporteResponseDto;
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

    public ReporteResponseDto obtenerReportePorTipo(String tipoReporte){
        ReporteResponseDto reporteDto = new ReporteResponseDto();
        if(tipoReporte!="kilometros" || tipoReporte!="conPausa"||tipoReporte!="sinPausa"){
             reporteDto.setExito(false);
             reporteDto.setMensaje("Solo podes filtar por kilometors, conPausa, SinPausa");
        }
        reporteDto.setMonopatines(null);//hacer consulta al microservicio que trae los monopatines ordenador o por kilometros, con o sin pausa
        return reporteDto;
    }
}
