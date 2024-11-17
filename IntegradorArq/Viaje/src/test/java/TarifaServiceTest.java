import org.example.viaje.DTO.TarifaResponseDto;
import org.example.viaje.entity.Tarifa;
import org.example.viaje.repository.TarifaRepository;
import org.example.viaje.service.TarifaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarifaServiceTest {
    @InjectMocks
    private TarifaService tarifaService;
    @Mock
    private TarifaRepository tarifaRepository;

    private Tarifa tarifa;

    @BeforeEach
    void setUp() {
        tarifa = new Tarifa();
        tarifa.setId(1L);
        tarifa.setTarifa(15.0);
    }

    @Test
    void TEST_getTarifaNormalPlazoValido_Exito() throws Exception {
        when(tarifaRepository.getTarifaNormalEnPlazoValido()).thenReturn(tarifa);
        TarifaResponseDto response = tarifaService.getTarifaNormalEnPlazoValido();

        assertNotNull(response);
        assertEquals(15.0, response.getTarifa());
    }
}
