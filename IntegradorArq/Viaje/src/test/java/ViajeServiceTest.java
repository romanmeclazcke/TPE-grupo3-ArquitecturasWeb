import org.example.viaje.DTO.PagoRequestDto;
import org.example.viaje.DTO.PausaResponseDto;
import org.example.viaje.DTO.TarifaResponseDto;
import org.example.viaje.DTO.ViajeResponseDTO;
import org.example.viaje.Model.Distancia;
import org.example.viaje.entity.Viaje;
import org.example.viaje.feignClients.MapaFeignClient;
import org.example.viaje.feignClients.PagoFeignClient;
import org.example.viaje.repository.ViajeRepository;
import org.example.viaje.service.PausaService;
import org.example.viaje.service.TarifaService;
import org.example.viaje.service.ViajeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViajeServiceTest {
    @InjectMocks
    private ViajeService viajeService;

    @Mock
    private ViajeRepository viajeRepository;

    @Mock
    private PausaService pausaService;

    @Mock
    private MapaFeignClient mapaFeignClient;

    @Mock
    private TarifaService tarifaService;

    @Mock
    private PagoFeignClient pagoFeignClient;

    private Viaje viaje;
    private Distancia distancia;
    private PausaResponseDto pausaResponseDto;
    private TarifaResponseDto tarifaResponseDto;

    @BeforeEach
    void setUp() {
        viaje = new Viaje();
        viaje.setId(1L);
        viaje.setId_parada_origen(1L);
        viaje.setId_parada_destino(2L);
        viaje.setId_usuario(123L);

        distancia = new Distancia();
        distancia.setDistancia(100.0);

        pausaResponseDto = new PausaResponseDto();
        pausaResponseDto.setHora_inicio(LocalDateTime.now().minusMinutes(10));
        pausaResponseDto.setHora_frin(LocalDateTime.now());

        tarifaResponseDto = new TarifaResponseDto();
        tarifaResponseDto.setTarifa(5.0);
    }

    @Test
    void TEST_endViajeExitoso() throws Exception {
        when(viajeRepository.findById(viaje.getId())).thenReturn(Optional.of(viaje));
        when(mapaFeignClient.getDistanciaEntreParada(viaje.getId_parada_origen(), viaje.getId_parada_destino())).thenReturn(distancia);
        when(pausaService.getPausasPorViaje(viaje.getId())).thenReturn(Collections.singletonList(pausaResponseDto));
        when(tarifaService.getTarifaNormalEnPlazoValido()).thenReturn(tarifaResponseDto);
        when(pagoFeignClient.pagar(any(PagoRequestDto.class))).thenReturn(true);

        viajeService.endViaje(viaje.getId());

        verify(viajeRepository).save(viaje);
        verify(pagoFeignClient).pagar(any(PagoRequestDto.class));
    }

    @Test
    void TEST_getViajesPorMonopatin() {
        Viaje viaje2 = new Viaje();
        viaje2.setId(2L);
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(viaje2);
        viajes.add(viaje);

        when(viajeRepository.getViajesPorMonopatin(1L)).thenReturn(viajes);
        List<ViajeResponseDTO> response = viajeService.getViajesPorMonopatin(1L);

        assertNotNull(response);
        assertEquals(2, response.size());
        verify(viajeRepository).getViajesPorMonopatin(1L);
    }
}
