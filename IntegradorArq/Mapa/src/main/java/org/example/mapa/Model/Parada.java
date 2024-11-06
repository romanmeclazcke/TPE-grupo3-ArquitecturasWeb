package org.example.mapa.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parada {
    private Long idParada;
    private List<Long> monopatines;

}
