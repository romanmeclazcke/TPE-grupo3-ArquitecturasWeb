import org.example.pago.DTO.PagoRequestDto;
import org.example.pago.DTO.ResumenPagosDTO;
import org.example.pago.FeignClient.UsuarioFeignClient;
import org.example.pago.Model.Cuenta;
import org.example.pago.repository.PagoRepository;
import org.example.pago.repository.PagoRepositoryCustom;
import org.example.pago.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {
    @InjectMocks
    private PagoService pagoService;
    @Mock
    private PagoRepository pagoRepository;
    @Mock
    private PagoRepositoryCustom pagoRepositoryCustom;
    @Mock
    private UsuarioFeignClient usuarioFeignClient;

    private PagoRequestDto requestDto;
    private List<Cuenta> cuentas;

    @BeforeEach
    void setUp() {
        requestDto = new PagoRequestDto();
        requestDto.setUserId(8L);
        requestDto.setMonto(700.0);

        Cuenta c1 = new Cuenta();
        Cuenta c2 = new Cuenta();
        c1.setCredito(150.0);
        c2.setCredito(70.0);
        cuentas = new ArrayList<>();
        cuentas.add(c1);
        cuentas.add(c2);
    }

    @Test
    void TEST_creditoInsuficiente() throws Exception {
        when(usuarioFeignClient.getCuentasByUser(requestDto.getUserId())).thenReturn(cuentas);

        boolean resultado = pagoService.pagar(requestDto);
        assertFalse(resultado); //Ya que hay que pagar 700 y entre ambas cuentas tiene 220
    }

    @Test
    void TEST_getTotalFacturado() throws Exception {
        when(pagoRepositoryCustom.getTotalFacturadoEntre(any(LocalDate.class), any(LocalDate.class))).thenReturn(500.0);
        ResumenPagosDTO resumen = pagoService.getTotalFacturadoEntre(2024, 1, 3);

        assertNotNull(resumen);
        assertEquals(500.0, resumen.getTotal());
    }
}
