package org.example.viaje.feignClients;

import org.example.viaje.DTO.PagoRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pago-service", url = "http://localhost:8088")

public interface PagoFeignClient {

    @PostMapping("pago")
    Boolean pagar(@RequestBody PagoRequestDto pagoRequestDto);
}
