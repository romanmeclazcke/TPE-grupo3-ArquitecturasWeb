package micro.example.gateway.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    private Long id;
    private String tipo_rol;
    private List<Usuario> usuarios;
}
