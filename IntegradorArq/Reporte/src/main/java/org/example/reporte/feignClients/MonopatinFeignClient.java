package org.example.reporte.feignClients;

import org.example.reporte.Model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "monopatin-service", url = "http://localhost:8081")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines")
    List<Monopatin> getMonopatines();

    @GetMapping("/monopatines/order/kilometros")
    List<Monopatin> getMonopatinesPorKilometros();

    @GetMapping("/monopatines/order/tiempo-uso/con-pausa")
    List<Monopatin> getMonopatinesConTiempoPausa();

    @GetMapping("/monopatines/order/tiempo-uso/sin-pausa")
    List<Monopatin> getMonopatinesSinTiempoPausa();
}
