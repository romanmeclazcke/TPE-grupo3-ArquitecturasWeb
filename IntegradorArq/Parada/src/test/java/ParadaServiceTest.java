import org.example.monopatin.entity.Monopatin;
import org.example.parada.DTO.ParadaResponseDto;
import org.example.parada.entity.Parada;
import org.example.parada.feignClients.MonopatinFeignClient;
import org.example.parada.repository.ParadaRepository;
import org.example.parada.service.ParadaService;
import org.example.parada.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParadaServiceTest {
    @InjectMocks
    private ParadaService paradaService;
    @Mock
    private ParadaRepository paradaRepository;
    @Mock
    private MonopatinFeignClient monopatinFeignClient;

    private Monopatin monopatin;
    private Parada parada;

    @BeforeEach
    void setUp() {
        monopatin = new Monopatin();
        monopatin.setId(5L);
        parada = new Parada();
    }

    @Test
    void TEST_ubicarMonopatinEnParadaInexistente() {
        parada.setId(999L); //No existe
        when(monopatinFeignClient.getMonopatinById(monopatin.getId())).thenReturn(monopatin);
        when(paradaRepository.findById(parada.getId())).thenReturn(Optional.empty()); //La parada debería venir vacía ya que no existe

        assertThrows(NotFoundException.class, () -> {
            paradaService.ubicarMonopatinEnParada(parada.getId(), monopatin.getId());
        });
    }

    @Test
    void TEST_sacarMonopatinDeParada() {
        parada.setId(1L);
        when(paradaRepository.findById(parada.getId())).thenReturn(Optional.of(parada));
        ParadaResponseDto response = paradaService.retirarMonopatinDeParada(parada.getId(), monopatin.getId());

        assertTrue(response.isExito());
        assertFalse(parada.getId_monopatines().contains(monopatin.getId()));
    }
}
