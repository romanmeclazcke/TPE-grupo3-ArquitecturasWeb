

import org.example.viaje.ViajeApplication;
import org.example.viaje.DTO.ViajeResponseDTO;
import org.example.viaje.service.ViajeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ViajeApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ViajeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViajeService viajeService;


    @Test
    void testCrearViaje() throws Exception {
        
        ViajeResponseDTO responseDTO = new ViajeResponseDTO(
                14L, 
                3L,  
                8L,  
                9L,  
                null, 
                null, 
                0.0, 
                "Viaje creado exitosamente", 
                true 
        );

        when(viajeService.save(anyLong(), anyLong(), anyLong())).thenReturn(responseDTO);

        mockMvc.perform(post("/viaje/crear/{monopatinId}/usuario/{usuarioId}/paradaDestino/{paradaId}", 3L, 14L, 9L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value(14))
                .andExpect(jsonPath("$.idMonopatin").value(3))
                .andExpect(jsonPath("$.idParadaDestino").value(9))
                .andExpect(jsonPath("$.mensaje").value("Viaje creado exitosamente"));
    }

    @Test
    void testObtenerViajesPorMonopatin() throws Exception {
        
        ViajeResponseDTO responseDTO = new ViajeResponseDTO(
                3L, 
                10L, 
                8L, 
                7L, 
                LocalDate.now(), 
                null, 
                0.0, 
                "Viaje registrado exitosamente",
                true 
        );

        Mockito.when(viajeService.getViajesPorMonopatin(any())).thenReturn(List.of(responseDTO));

        
        mockMvc.perform(get("/viaje/monopatin/10")
                        .contentType(MediaType.APPLICATION_JSON))                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idMonopatin").value(10))
                .andExpect(jsonPath("$[0].idUsuario").value(3));
    }
}
