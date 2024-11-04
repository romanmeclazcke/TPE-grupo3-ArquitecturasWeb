package org.example.monopatin.feignClient;


import org.example.viaje.DTO.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "viaje-service", url = "http://localhost:8084")
public interface ViajeFeignClient {

    @PostMapping("monopatinId/{monopatinId}/usuarioId/{usuarioId}")
    ViajeResponseDTO createViaje(@PathVariable Long monopatinId, Long usuarioId);

}