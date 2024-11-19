package org.example.monopatin.feignClient;


//import org.example.viaje.DTO.*;
import org.example.monopatin.Model.Pausa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "viaje-service", url = "http://localhost:8084")
public interface ViajeFeignClient {

    @PostMapping("/viaje/crear/{monopatinId}/usuario/{usuarioId}/paradaDestino/{paradaId}")
    void createViaje(@PathVariable("monopatinId") Long monopatinId,@PathVariable("usuarioId") Long usuarioId ,@PathVariable("paradaId") Long paradaId);

    @GetMapping("/viaje/verificar-parada/{paradaId}")
    ResponseEntity<?> verificarParada(@PathVariable("paradaId") Long paradaId);

    @GetMapping("/pausa/monopatin/{idMonopatin}")
    List<Pausa> getPausasIdMonopatin(@PathVariable("idMonopatin") Long idMonopatin);
}