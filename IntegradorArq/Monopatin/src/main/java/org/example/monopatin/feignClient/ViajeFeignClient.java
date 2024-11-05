package org.example.monopatin.feignClient;


//import org.example.viaje.DTO.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "viaje-service", url = "http://localhost:8084")
public interface ViajeFeignClient {

    @PostMapping("/viaje/crear/{monopatinId}/usuario/{usuarioId}")
    void createViaje(@PathVariable("monopatinId") Long monopatinId,@PathVariable("usuarioId") Long usuarioId);

}