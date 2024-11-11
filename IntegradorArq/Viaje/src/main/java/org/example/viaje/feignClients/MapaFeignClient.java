package org.example.viaje.feignClients;

import org.example.viaje.Model.Distancia;
import org.example.viaje.Model.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mapa-service", url = "http://localhost:8087")
public interface MapaFeignClient {

    @GetMapping("/mapa/distancia/origen/{idParadaOrigen}/destino/{idParadaDestino}")
    Distancia getDistanciaEntreParada(@PathVariable("idParadaOrigen") Long idParadaOrigen,
                                      @PathVariable("idParadaDestino") Long idParadaDestino);

}
