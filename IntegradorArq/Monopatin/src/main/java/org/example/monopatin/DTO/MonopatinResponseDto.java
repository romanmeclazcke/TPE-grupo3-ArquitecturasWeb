package org.example.monopatin.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinResponseDto {

    private Long id;

    private Duration tiempo_uso;
    private Double kilometros;
    private Boolean disponible;
}
