package org.example.reporte.feignClients;

import org.example.viaje.DTO.ViajeResponseDTO;
import org.example.viaje.entity.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.util.List;

@FeignClient(name = "viaje-service", url = "http://localhost:8084")
public interface ViajeFeignClient {

    @GetMapping("/viaje/monopatin/{idMonopatin}")
    List<Viaje> obtenerViajesPorMonopatin(@PathVariable("idMonopatin") Long idMonopatin);

    @GetMapping("/viaje/{idMonopatin}/kilometros")
    Double obtenerKilometrosPorMonopatin(@PathVariable Long idMonopatin);

    @GetMapping("/viaje/{idMonopatin}/tiempo-con-pausas")
    Double obtenerTiempoConPausasPorMonopatin(@PathVariable Long idMonopatin);

    @GetMapping("/viaje/{idMonopatin}/tiempo-sin-pausas")
    Double obtenerTiempoSinPausasPorMonopatin(@PathVariable Long idMonopatin);

}
