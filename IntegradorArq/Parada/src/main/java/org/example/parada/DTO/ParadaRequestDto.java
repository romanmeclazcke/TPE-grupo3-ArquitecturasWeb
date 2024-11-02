package org.example.parada.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaRequestDto {
    private long id;
    private List<Long> monopatines;
}
