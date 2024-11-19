import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.parada.DTO.ParadaRequestDto;
import org.example.parada.DTO.ParadaResponseDto;
import org.example.parada.service.ParadaService;
import org.example.parada.repository.ParadaRepository;
import org.example.parada.feignClients.MonopatinFeignClient;
import org.example.monopatin.entity.Monopatin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.parada.ParadaApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ParadaApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ParadaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParadaService paradaService;

    @MockBean
    private ParadaRepository paradaRepository;

    @MockBean
    private MonopatinFeignClient monopatinFeignClient;

    @Autowired
    private ObjectMapper objectMapper;

    private ParadaRequestDto paradaRequestDto;
    private ParadaResponseDto paradaResponseDto;

    @BeforeEach
    void setUp() {
        paradaRequestDto = new ParadaRequestDto(1L, null, 10, 20);
        paradaResponseDto = new ParadaResponseDto(1L, null, 10, 20, "Parada creada con éxito", true);
    }

    @Test
    void testCrearParada() throws Exception {
        when(paradaService.save(Mockito.any(ParadaRequestDto.class))).thenReturn(paradaResponseDto);

        mockMvc.perform(post("/parada")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paradaRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.x", is(10)))
                .andExpect(jsonPath("$.y", is(20)));
    }

    @Test
    void testEstacionarMonopatinEnParada() throws Exception {
        Long idParada = 1L;
        Long idMonopatin = 10L;

        Monopatin monopatin = new Monopatin();
        monopatin.setId(idMonopatin);

        when(monopatinFeignClient.getMonopatinById(idMonopatin)).thenReturn(monopatin);
        when(paradaService.ubicarMonopatinEnParada(idParada, idMonopatin)).thenReturn(paradaResponseDto);

        mockMvc.perform(post("/parada/{idParada}/estacionar/monopatin/{idMonopatin}", idParada, idMonopatin)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.x", is(10)))
                .andExpect(jsonPath("$.y", is(20)));
    }

    @Test
    void testRetirarMonopatinDeParada() throws Exception {
        Long idParada = 1L;
        Long idMonopatin = 10L;

        when(paradaService.retirarMonopatinDeParada(idParada, idMonopatin)).thenReturn(paradaResponseDto);

        mockMvc.perform(patch("/parada/{idParada}/retirar/monopatin/{idMonopatin}", idParada, idMonopatin)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.x", is(10)))
                .andExpect(jsonPath("$.y", is(20)));
    }
}