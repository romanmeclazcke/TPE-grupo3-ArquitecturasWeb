package org.example.reporte.service;


import org.example.reporte.DTO.ReporteKilometrosResponseDto;
import org.example.reporte.DTO.ReporteResponseDto;
import org.example.reporte.DTO.ReporteTiempoResponseDto;
import org.example.reporte.feignClients.ViajeFeignClient;
import org.example.reporte.repository.ReporteRepository;
import org.example.viaje.entity.Pausa;
import org.example.viaje.entity.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;
    @Autowired
    ViajeFeignClient viajeFeignClient;

    public Object obtenerReportePorTipo(String tipoReporte, Long idMonopatin) {
        if (idMonopatin == null) {
            ReporteResponseDto response = new ReporteResponseDto();
            response.setExito(false);
            response.setMensaje("El ID del monopatín no puede ser nulo");
            return response;
        }

        List<Viaje> viajes = viajeFeignClient.obtenerViajesPorMonopatin(idMonopatin);
        if (tipoReporte.equals("kilometros"))
            return obtenerReporteKilometros(viajes);
        else if (tipoReporte.equals("conPausa") || tipoReporte.equals("sinPausa"))
            return obtenerReporteTiempo(tipoReporte, viajes);
        else {
            ReporteResponseDto responseDto = new ReporteResponseDto();
            responseDto.setExito(false);
            responseDto.setMensaje("Sólo podés filtrar por kilómetros, conPausa o sinPausa");
            return responseDto;
        }
    }

    private ReporteKilometrosResponseDto obtenerReporteKilometros(List<Viaje> viajes) {
        ReporteKilometrosResponseDto reporte = new ReporteKilometrosResponseDto();

        double total_km = 0;
        for (Viaje v : viajes)
            total_km += v.getKm();

        reporte.setTotalKilometros(total_km);
        reporte.setExito(true);
        return reporte;
    }

    private ReporteTiempoResponseDto obtenerReporteTiempo(String tipoReporte, List<Viaje> viajes) {
        ReporteTiempoResponseDto reporte = new ReporteTiempoResponseDto();

        double totalTiempoConPausa = 0;
        double totalTiempoSinPausa = 0;
        for (Viaje v : viajes) {
            LocalDateTime horaInicio = v.getHora_inicio();
            LocalDateTime horaFin = v.getHora_fin();
            //Calculo la duración del viaje
            Long duracion = Duration.between(horaInicio, horaFin).toMinutes();

            double duracionPausas = 0;
            for (Pausa p : v.getPausas()) {
                LocalDateTime inicioPausa = p.getHora_inicio();
                LocalDateTime finPausa = p.getHora_fin();
                duracionPausas += Duration.between(inicioPausa, finPausa).toMinutes();
            }

            totalTiempoConPausa += (duracion + duracionPausas);
            totalTiempoSinPausa += duracion;
        }

        switch (tipoReporte) {
            case "conPausa":
                reporte.setTotalTiempo(totalTiempoConPausa);
                reporte.setExito(true);
                break;
            case "sinPausa":
                reporte.setTotalTiempo(totalTiempoSinPausa);
                reporte.setExito(true);
                break;
        }

        return reporte;
    }
}
