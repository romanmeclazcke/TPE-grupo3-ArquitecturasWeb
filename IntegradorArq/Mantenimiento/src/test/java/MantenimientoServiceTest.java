import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.DTO.MantenimientoResponseDto;
import org.example.mantenimiento.entity.Mantenimiento;
import org.example.mantenimiento.feignClients.MonopatinFeignClient;
import org.example.mantenimiento.repository.MantenimientoRepository;
import org.example.mantenimiento.service.MantenimientoService;
import org.example.mantenimiento.service.exception.NotFoundException;
import org.example.monopatin.entity.Monopatin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MantenimientoServiceTest {
    @InjectMocks //Para que los objetos de los que depende también sean mockeados
    private MantenimientoService mantenimientoService;

    @Mock
    private MantenimientoRepository mantenimientoRepository;

    @Mock
    private MonopatinFeignClient monopatinFeignClient;

    @Test
    void TEST_saveMonopatinEnMantenimiento() throws Exception {
        Long idMonopatin = 1L;
        Monopatin monopatin = new Monopatin();
        monopatin.setId(idMonopatin);
        MantenimientoRequestDto request = new MantenimientoRequestDto();

        when(monopatinFeignClient.getMonopatinById(idMonopatin)).thenReturn(monopatin);
        when(mantenimientoRepository.existsById(idMonopatin)).thenReturn(false); //Ya que el monopatín no debería estar en mantenimiento

        MantenimientoResponseDto response = mantenimientoService.save(idMonopatin, request);

        //Verifico si se llamó al repositorio
        verify(mantenimientoRepository).save(any(Mantenimiento.class));
        //Verifico que se actualizó la disponibilidad del monopatín
        verify(monopatinFeignClient).updateMonopatin(idMonopatin, monopatin);

        assertNotNull(response);
        assertTrue(response.isExito());
    }

    @Test
    void TEST_saveMonopatinInexistente() throws NotFoundException {
        Long idMonopatinInexistente = 888L;
        when(monopatinFeignClient.getMonopatinById(idMonopatinInexistente)).thenReturn(null); //Simula que el monopatín no existe
        assertThrows(NotFoundException.class, () -> mantenimientoService.save(idMonopatinInexistente, new MantenimientoRequestDto()));
    }
}
