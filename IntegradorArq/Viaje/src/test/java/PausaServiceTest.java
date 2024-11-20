import org.example.viaje.DTO.PausaResponseDto;
import org.example.viaje.entity.Pausa;
import org.example.viaje.entity.Viaje;
import org.example.viaje.repository.PausaRepository;
import org.example.viaje.repository.ViajeRepository;
import org.example.viaje.service.PausaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PausaServiceTest {
    @InjectMocks
    private PausaService pausaService;
    @Mock
    private PausaRepository pausaRepository;
    @Mock
    private ViajeRepository viajeRepository;

    private Viaje viaje;

    @BeforeEach
    void setUp() {
        viaje = new Viaje();
        viaje.setId(10L);
    }

    @Test
    void TEST_getPausasPorViaje() {
        Pausa p1 = new Pausa();
        p1.setId(1L);
        p1.setHora_inicio(LocalDateTime.now());

        Pausa p2 = new Pausa();
        p2.setId(2L);
        p2.setHora_inicio(LocalDateTime.now().plusMinutes(30));

        List<Pausa> pausas = new ArrayList<>();
        pausas.add(p1);
        pausas.add(p2);

        when(pausaRepository.getPausasPorViaje(viaje.getId())).thenReturn(pausas);
        List<PausaResponseDto> response = pausaService.getPausasPorViaje(viaje.getId());

        assertEquals(2, response.size());
        assertEquals(p1.getId(), response.get(0).getId());
        assertEquals(p2.getId(), response.get(1).getId());
    }

    @Test
    void testCrearPausaExito() {
        when(viajeRepository.findById(viaje.getId())).thenReturn(Optional.of(viaje));
        when(pausaRepository.save(any(Pausa.class))).thenAnswer(invocation -> invocation.getArgument(0)); //Devuelve esa misma instancia de pausa

        PausaResponseDto response = pausaService.crearPausa(viaje.getId());

        assertNotNull(response.getHora_inicio());
    }
}
