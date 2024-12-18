package org.example.viaje.feignClients;

import org.example.viaje.Model.Parada;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "parada-service", url = "http://localhost:8085")
public interface ParadaFeignClient {

    @GetMapping("/parada/{idParada}")
    ResponseEntity<?> getParadaById(@PathVariable Long idParada);


    @GetMapping("/parada/contine-monopatin/{idMonopatin}")
    Parada getParadaQueContieneMonopatin(@PathVariable Long idMonopatin);

}
