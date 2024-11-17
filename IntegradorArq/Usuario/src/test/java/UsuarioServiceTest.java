import org.example.usuario.DTO.UsuarioRequestDto;
import org.example.usuario.DTO.UsuarioResponseDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.entity.Rol;
import org.example.usuario.entity.Usuario;
import org.example.usuario.repository.CuentaRepository;
import org.example.usuario.repository.RolRepository;
import org.example.usuario.repository.UsuarioRepository;
import org.example.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService service;
    @Mock
    private UsuarioRepository repository;
    @Mock
    private CuentaRepository cuentaRepository;
    @Mock
    private RolRepository rolRepository;

    private Usuario usuario;
    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta  = new Cuenta();
        cuenta.setId(10L);
        cuenta.setUsuarios(new ArrayList<>());
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCuentas(new ArrayList<>());
    }

    @Test
    void TEST_agregarCuenta() throws Exception {
        when(repository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(cuentaRepository.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);

        UsuarioResponseDto response = service.agregarCuenta(usuario.getId(), cuenta.getId());

        assertTrue(response.isExito());
        verify(repository.save(usuario));
        verify(cuentaRepository).save(cuenta);
        assertTrue(cuenta.getUsuarios().contains(usuario));
        assertTrue(usuario.getCuentas().contains(cuenta));
    }

    @Test
    void TEST_agregarCuentaInexistente() throws Exception {
        UsuarioResponseDto response = service.agregarCuenta(usuario.getId(), cuenta.getId());

        assertFalse(response.isExito());
        assertEquals("La cuenta con id 10 no existe.", response.getMensaje());
    }

    @Test
    void TEST_editarUsuario() throws Exception {
        when(repository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        UsuarioRequestDto request = new UsuarioRequestDto();
        request.setNombre("Nuevo");
        request.setApellido("Nuevo");
        request.setEmail("nuevo@gmail.com");
        request.setNumeroCelular(12345678L);
        request.setId_rol(2L);

        Rol rol = new Rol();
        rol.setId(2L);

        when(repository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(rolRepository.findById(rol.getId())).thenReturn(Optional.of(rol));
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDto response = service.editarUsuario(usuario.getId(), request);

        assertTrue(response.isExito());
        assertEquals("Nuevo", usuario.getNombre());
        assertEquals("Nuevo", usuario.getApellido());
        assertEquals("nuevo@gmail.com", usuario.getEmail());
        assertEquals(rol, usuario.getRol());
        assertEquals(12345678L, usuario.getTelefono());
        verify(repository).save(usuario);
    }
}
