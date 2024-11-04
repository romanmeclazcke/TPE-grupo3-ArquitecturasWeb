/*package org.example.viaje.feignClients;

import org.example.monopatin.entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "monopatin-service", url = "http://localhost:8081")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{monopatinId}")
    Monopatin getMonopatinById(@PathVariable("monopatinId") Long monopatinId);

    @PutMapping("/monopatines/{monopatinId}")
    void updateMonopatin(@PathVariable("monopatinId") Long id, @RequestBody Monopatin monopatin);
}*/
