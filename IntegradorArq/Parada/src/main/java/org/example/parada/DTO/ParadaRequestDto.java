package org.example.parada.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParadaRequestDto {

    private Long id;
    private List<Long> monopatines;
    private Integer x;
    private Integer y;
}
