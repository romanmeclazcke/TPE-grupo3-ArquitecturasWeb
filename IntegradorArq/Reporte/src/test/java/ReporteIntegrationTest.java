

import org.example.reporte.ReporteApplication;
import org.example.reporte.Model.Monopatin;
import org.example.reporte.feignClients.MonopatinFeignClient;
import org.example.reporte.feignClients.ViajeFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ReporteApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ReporteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonopatinFeignClient monopatinFeignClient;

    @MockBean
    private ViajeFeignClient viajeFeignClient;

    @Test
    void testObtenerReporteKilometro() throws Exception {
        Mockito.when(monopatinFeignClient.getMonopatinesPorKilometros())
                .thenReturn(Arrays.asList(
                        new Monopatin(1L,  Duration.ofHours(10),1200.0, true),
                        new Monopatin(2L,  Duration.ofHours(8),800.0, true)
                ));

        mockMvc.perform(get("/reporte/kilometros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idMonopatin", is(1)))
                .andExpect(jsonPath("$[0].kilometros", is(1200.0)))
                .andExpect(jsonPath("$[1].idMonopatin", is(2)))
                .andExpect(jsonPath("$[1].kilometros", is(800.0)));
    }

    @Test
    void testObtenerReporteTiempoConPausa() throws Exception {
        Mockito.when(monopatinFeignClient.getMonopatinesConTiempoPausa())
                .thenReturn(Arrays.asList(
                        new Monopatin(1L,  Duration.ofHours(12),1000.0, true),
                        new Monopatin(3L,  Duration.ofHours(6),600.0,true)
                ));

        mockMvc.perform(get("/reporte/tiempo").param("pausas", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idMonopatin", is(1)))
                .andExpect(jsonPath("$[0].tiempo_uso", is("PT12H")))
                .andExpect(jsonPath("$[1].idMonopatin", is(3)))
                .andExpect(jsonPath("$[1].tiempo_uso", is("PT6H")));
    }
}
