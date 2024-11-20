import org.example.reporte.DTO.ReporteResponseDto;
import org.example.reporte.feignClients.MonopatinFeignClient;
import org.example.reporte.service.ReporteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.reporte.Model.Monopatin;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {
    @InjectMocks
    private ReporteService reporteService;
    @Mock
    private MonopatinFeignClient monopatinFeignClient;

    List<Monopatin> monopatines;

    @BeforeEach
    void setUp() {
        monopatines = new ArrayList<>();
        monopatines.add(new Monopatin(1L, Duration.ofHours(2), 100.0, true));
        monopatines.add(new Monopatin(2L, Duration.ofHours(5), 250.0, false));
        monopatines.add(new Monopatin(3L, Duration.ofHours(1), 20.0, true));
    }

    @Test
    void TEST_reporteKilometros() {
        when(monopatinFeignClient.getMonopatinesPorKilometros()).thenReturn(monopatines);
        List<ReporteResponseDto> response = reporteService.obtenerReporteKilometro();

        assertEquals(3, response.size());
        assertEquals(100.0, response.get(0).getKilometros());
        assertEquals(250.0, response.get(1).getKilometros());
        assertEquals(20.0, response.get(2).getKilometros());
    }

    @Test
    void TEST_reporteTiempoConPausas() {
        when(monopatinFeignClient.getMonopatinesConTiempoPausa()).thenReturn(monopatines);
        List<ReporteResponseDto> response = reporteService.obtenerReporteTiempo(true);

        assertEquals(3, response.size());
        assertEquals(Duration.ofHours(2), response.get(0).getTiempo_uso());
        assertEquals(Duration.ofHours(5), response.get(1).getTiempo_uso());
        assertEquals(Duration.ofHours(1), response.get(2).getTiempo_uso());
    }

    @Test
    void TEST_reporteTiempoSinConPausas() {
        when(monopatinFeignClient.getMonopatinesSinTiempoPausa()).thenReturn(monopatines);
        List<ReporteResponseDto> response = reporteService.obtenerReporteTiempo(false);

        assertEquals(3, response.size());
        assertEquals(Duration.ofHours(2), response.get(0).getTiempo_uso());
        assertEquals(Duration.ofHours(5), response.get(1).getTiempo_uso());
        assertEquals(Duration.ofHours(1), response.get(2).getTiempo_uso());
    }
}
