import java.util.Arrays;
import java.util.List;

import org.example.mapa.MapaApplication;
import org.example.mapa.DTO.MonopatinResponseDto;
import org.example.mapa.DTO.UbicacionRequestDto;
import org.example.mapa.service.MapsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;





import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MapaApplication.class)
@AutoConfigureMockMvc
public class MapsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapsService mapsService;

    @Test
    public void obtenerMonopatinesCercanos() throws Exception {
        
        UbicacionRequestDto ubicacionRequestDto = new UbicacionRequestDto(37L,-122L);  
        MonopatinResponseDto monopatinResponseDto1 = new MonopatinResponseDto(1L,null,35.3,37,-122);
        

        MonopatinResponseDto monopatinResponseDto2 = new MonopatinResponseDto(2L,null,3.3,37,-122);
        

        List<MonopatinResponseDto> monopatines = Arrays.asList(monopatinResponseDto1, monopatinResponseDto2);

        
        given(mapsService.getMonopatinByUbicacion(ubicacionRequestDto)).willReturn(monopatines);

        
        mockMvc.perform(get("/mapa/obtener-monopatin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ubicacionRequestDto)))
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.length()").value(2)) 
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].x").value(37))
                .andExpect(jsonPath("$[0].y").value(-122));
    }
}