package org.example.viaje.feignClients;

import org.example.usuario.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8080")
public interface UsuarioFeignClient {

    @GetMapping("/usuarios/{id}")
    Usuario getUsuarioById(@PathVariable("id") Long idUsuario);
}
