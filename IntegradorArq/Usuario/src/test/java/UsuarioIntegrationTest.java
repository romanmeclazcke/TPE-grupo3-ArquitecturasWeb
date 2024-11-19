import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.entity.Rol;
import org.example.usuario.entity.Usuario;
import org.example.usuario.repository.RolRepository;
import org.example.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.example.usuario.UsuarioApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest(classes = UsuarioApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UsuarioIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCrearUsuario() throws Exception {
        Rol rol = new Rol();
        rol.setTipo_rol("ADMIN");
        rol = rolRepository.save(rol);

        UsuarioRequestDto usuarioRequestDto = new UsuarioRequestDto();
        usuarioRequestDto.setNombre("Juan");
        usuarioRequestDto.setApellido("Pérez");
        usuarioRequestDto.setEmail("juan@example.com");
        usuarioRequestDto.setNumeroCelular(123456789L);
        usuarioRequestDto.setId_rol(rol.getId());

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuarioRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setApellido("Sánchez");
        usuario.setEmail("carlos@example.com");
        usuario.setTelefono(987654321L);
        usuario = usuarioRepository.save(usuario);

        mockMvc.perform(get("/usuarios/" + usuario.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}