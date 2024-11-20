package micro.example.gateway.FeignClient;

import micro.example.gateway.DTO.UsuarioRequestDto;
import micro.example.gateway.DTO.UsuarioResponseDto;
import micro.example.gateway.Model.Rol;
import micro.example.gateway.Model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioFeignClient {

    @PostMapping("/usuarios")
    UsuarioResponseDto crearCuenta(@RequestBody UsuarioRequestDto usuario);

    @GetMapping("/rol/{idRol}")
    Rol getRolById(@PathVariable("idRol") Long idRol);

    @GetMapping("/usuarios/email/{userEmail}")
    UsuarioResponseDto getUserByUsername(@PathVariable("userEmail") String userEmail);
}
