package org.example.pago.FeignClient;

import org.example.pago.DTO.CuentaRequestDto;
import org.example.pago.Model.Cuenta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioFeignClient {

    @GetMapping("/cuenta/{idUsuario}")
    List<Cuenta> getCuentasByUser(@PathVariable("idUsuario") Long idUsuario);

    @PutMapping("/cuenta/{idCuenta}")
    void updateCuenta(@PathVariable("idCuenta") Long idCuenta, @RequestBody CuentaRequestDto requestDto);
}
