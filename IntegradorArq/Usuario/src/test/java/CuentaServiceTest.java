import org.example.usuario.DTO.CuentaCargarSaldoDto;
import org.example.usuario.DTO.CuentaResponseDto;
import org.example.usuario.entity.Cuenta;
import org.example.usuario.repository.CuentaRepository;
import org.example.usuario.service.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {
    @InjectMocks
    private CuentaService cuentaService;
    @Mock
    private CuentaRepository cuentaRepository;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setActiva(true);
    }

    @Test
    void TEST_anularCuenta() throws Exception {
        //Simulo que la cuenta existe
        when(cuentaRepository.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
        cuentaService.anularCuenta(cuenta.getId());

        verify(cuentaRepository).save(cuenta);
        assertFalse(cuenta.isActiva());
    }

    @Test
    void TEST_anularCuentaNoEncontrada() {
        //Simulo que la cuenta no existe
        when(cuentaRepository.findById(cuenta.getId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> cuentaService.anularCuenta(cuenta.getId()));
    }

    @Test
    void TEST_cargarSaldo() throws Exception {
        cuenta.setCredito(50.0);
        CuentaCargarSaldoDto dto = new CuentaCargarSaldoDto();
        dto.setCredito(100.0);

        when(cuentaRepository.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
        when(cuentaRepository.save(cuenta)).thenReturn(cuenta);

        CuentaResponseDto response = cuentaService.cargarSaldo(cuenta.getId(), dto);

        assertEquals(150.0, cuenta.getCredito()); //50 iniciales + 100 cargados
        assertNotNull(response);
    }
}
