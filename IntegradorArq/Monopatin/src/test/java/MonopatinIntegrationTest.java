import static org.mockito.ArgumentMatchers.any;

import org.example.monopatin.MonopatinApplication;
import org.example.monopatin.DTO.MonopatinRequestDto;

import org.example.monopatin.entity.Monopatin;
import org.example.monopatin.repository.MonopatinRepository;
import org.example.monopatin.service.MonopatinServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(classes = MonopatinApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MonopatinIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MonopatinRepository monopatinRepository;

    @InjectMocks
    private MonopatinServices monopatinServices;

    @Test
    public void testCrearMonopatinExitoso() throws Exception {

        MonopatinRequestDto monopatinRequestDto = new MonopatinRequestDto();
        monopatinRequestDto.setId(1L);
        monopatinRequestDto.setY(40);
        monopatinRequestDto.setX(30);
        monopatinRequestDto.setTiempo_uso(null);
        monopatinRequestDto.setKilometros(100);
        monopatinRequestDto.setDisponible(true);

        Monopatin mockMonopatin = new Monopatin();
        mockMonopatin.setId(1L);
        mockMonopatin.setY(40);
        mockMonopatin.setX(30);
        mockMonopatin.setTiempo_uso(null);
        mockMonopatin.setKilometros(100.0);
        mockMonopatin.setDisponible(true);

        Mockito.when(monopatinRepository.save(any(Monopatin.class))).thenReturn(mockMonopatin);

        mockMvc.perform(post("/monopatines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(monopatinRequestDto)))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.y").value(40))
                .andExpect(jsonPath("$.x").value(30))
                .andExpect(jsonPath("$.kilometros").value(100))
                .andExpect(jsonPath("$.disponible").value(true));
    }

    
}
