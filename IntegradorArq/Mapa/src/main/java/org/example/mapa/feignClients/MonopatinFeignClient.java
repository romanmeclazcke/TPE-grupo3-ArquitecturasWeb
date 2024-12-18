package org.example.mapa.feignClients;

import org.example.mapa.Model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "monopatin-service", url = "http://localhost:8083")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{monopatinId}")
    Monopatin getMonopatinById(@PathVariable("monopatinId") Long monopatinId);


    @GetMapping("/monopatines/cercanos-a-posicion/{x}/{y}")
    List<Monopatin> getMonopatinesEnRadio1km(@PathVariable("x") Integer x, @PathVariable("y") Integer y);
}
