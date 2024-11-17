import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.DTO.MonopatinSumaKilometrosDto;
import org.example.monopatin.entity.Monopatin;
import org.example.monopatin.feignClient.ViajeFeignClient;
import org.example.monopatin.repository.MonopatinRepository;
import org.example.monopatin.service.MonopatinServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonopatinServiceTest {
    @InjectMocks
    private MonopatinServices monopatinServices;
    @Mock
    private MonopatinRepository monopatinRepository;
    @Mock
    private ViajeFeignClient viajeFeignClient;

    private Monopatin monopatin;

    @BeforeEach
    void setUp() {
        monopatin = new Monopatin();
        monopatin.setId(1L);
        monopatin.setDisponible(true);
        monopatin.setKilometros(100.0);
    }

    @Test
    void TEST_activarMonopatinNoDisponible() {
        Long idUsuario = 1L;
        Long idParada = 1L;
        monopatin.setDisponible(false);

        when(monopatinRepository.findById(monopatin.getId())).thenReturn(Optional.of(monopatin));
        assertThrows(Exception.class, () -> {
            monopatinServices.activarMonopatin(monopatin.getId(), idUsuario, idParada);
        });
    }

    @Test
    void TEST_sumarKilometros() throws Exception {
        MonopatinSumaKilometrosDto sumaKilometrosDto = new MonopatinSumaKilometrosDto();
        sumaKilometrosDto.setKilometros(50.5);

        when(monopatinRepository.findById(monopatin.getId())).thenReturn(Optional.of(monopatin));
        MonopatinResponseDto response = monopatinServices.sumarKilometros(monopatin.getId(), sumaKilometrosDto);
        assertNotNull(response);
        assertEquals(150.5, monopatin.getKilometros()); //100 iniciales + 50.5 agregados

        verify(monopatinRepository).save(monopatin);
    }


}
