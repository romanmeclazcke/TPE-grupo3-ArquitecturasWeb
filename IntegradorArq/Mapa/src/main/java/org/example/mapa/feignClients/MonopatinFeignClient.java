package org.example.mapa.feignClients;

import org.example.mapa.Model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "monopatin-service", url = "http://localhost:8081")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{monopatinId}")
    Monopatin getMonopatinById(@PathVariable("monopatinId") Long monopatinId);
}
