import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.mantenimiento.MantenimientoApplication;
import org.example.mantenimiento.DTO.MantenimientoRequestDto;
import org.example.mantenimiento.DTO.MantenimientoResponseDto;
import org.example.mantenimiento.DTO.MonopatinEnMantenimientoResponseDto;
import org.example.mantenimiento.entity.Mantenimiento;
import org.example.mantenimiento.feignClients.MonopatinFeignClient;
import org.example.mantenimiento.repository.MantenimientoRepository;
import org.example.mantenimiento.service.MantenimientoService;
import org.example.monopatin.DTO.MonopatinResponseDto;
import org.example.monopatin.entity.Monopatin;
import org.example.monopatin.service.MonopatinServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MantenimientoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MantenimientoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MantenimientoService mantenimientoService;

    @MockBean
    private MonopatinFeignClient monopatinesClient;

    @BeforeEach
    public void setup() {
        mantenimientoRepository.deleteAll(); 
    }

    @Test
    public void testCrearMantenimientoExitoso() throws Exception {
        
        Monopatin mockMonopatin = new Monopatin();
        mockMonopatin.setId(1L);
        
        Mockito.when(monopatinesClient.getMonopatinById(1L)).thenReturn(mockMonopatin);
        
        MantenimientoRequestDto mantenimientoRequestDto = new MantenimientoRequestDto(LocalDate.now(), Arrays.asList("Reparación de freno", "Cambio de rueda"));

        mockMvc.perform(post("/mantenimiento/1") 
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mantenimientoRequestDto)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testFinalizarMantenimiento() throws Exception {
    
    Monopatin mockMonopatin = new Monopatin();
    mockMonopatin.setId(1L);
    mockMonopatin.setDisponible(false);

    MantenimientoResponseDto mockMantenimiento = new MantenimientoResponseDto(
            1L,                         
            1L,                        
            LocalDate.now().minusDays(3), 
            LocalDate.now(),            
            Arrays.asList("Cambio de ruedas", "Revisión general"), 
            "Mantenimiento finalizado exitosamente", 
            true                        
    );

    
    when(monopatinesClient.getMonopatinById(1L)).thenReturn(mockMonopatin);
    when(mantenimientoService.endMantenimiento(1L)).thenReturn(mockMantenimiento);

    
    mockMvc.perform(patch("/mantenimiento/finalizar/1") 
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.id_monopatin").value(1L))
        .andExpect(jsonPath("$.fecha_inicio").value(LocalDate.now().minusDays(3).toString()))
        .andExpect(jsonPath("$.fecha_fin").value(LocalDate.now().toString()))
        .andExpect(jsonPath("$.acciones[0]").value("Cambio de ruedas"))
        .andExpect(jsonPath("$.acciones[1]").value("Revisión general"))
        .andExpect(jsonPath("$.mensaje").value("Mantenimiento finalizado exitosamente"))
        .andExpect(jsonPath("$.exito").value(true));
    }
}
