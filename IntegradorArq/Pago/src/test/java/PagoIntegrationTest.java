import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pago.DTO.PagoRequestDto;
import org.example.pago.Model.Cuenta;
import org.example.pago.FeignClient.UsuarioFeignClient;
import org.example.pago.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.example.pago.PagoApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest(classes = PagoApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PagoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PagoService pagoService;

    @MockBean
    private UsuarioFeignClient usuarioFeignClient;

    @Test
    public void testPagoExitoso() throws Exception {
        Long userId = 1L;
        Double montoPago = 100.0;
        PagoRequestDto pagoRequestDto = new PagoRequestDto(userId, 20L, montoPago, LocalDate.now());

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setId(1L);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(2L);

        List<Cuenta> cuentas = Arrays.asList(cuenta1, cuenta2);
        when(usuarioFeignClient.getCuentasByUser(userId)).thenReturn(cuentas);

        when(pagoService.pagar(pagoRequestDto)).thenReturn(true);

        mockMvc.perform(post("/pago")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pagoRequestDto)))
                .andExpect(status().isOk()) 
                .andExpect(content().string("true")); 
    }
}
