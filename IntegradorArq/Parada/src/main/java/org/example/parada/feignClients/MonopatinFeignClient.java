package org.example.parada.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.monopatin.entity.Monopatin;

@FeignClient(name = "monopatin-service", url = "http://localhost:8083")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{monopatinId}")
    Monopatin getMonopatinById(@PathVariable("monopatinId") Long monopatinId);
}
