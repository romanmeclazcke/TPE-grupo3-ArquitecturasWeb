package org.example.mapa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinResponseDto {
    private Long id;
    private Duration tiempo_uso;
    private Double kilometros;
}
