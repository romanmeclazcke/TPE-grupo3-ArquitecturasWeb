package org.example.mapa.feignClients;


import org.example.mapa.Model.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "parada-service", url = "http://localhost:8084")
public interface ParadaFeignClient {

    @GetMapping("/parada/{paradaId}")
    Parada getParadaById(@PathVariable("paradaId") Long monopatinId);

    @GetMapping("/parada")
    List<Parada> getParadas();
}
