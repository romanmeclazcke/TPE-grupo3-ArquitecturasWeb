package org.example.pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenPagosDTO {

    private int anio;
    private Integer mesAnterior;
    private Integer mesPosterior;
    private double total;

    public ResumenPagosDTO(int anio, double total, Integer mesAnterior, Integer mesPosterior) {
        this.anio = anio;
        this.total = total;
        this.mesAnterior = mesAnterior;
        this.mesPosterior = mesPosterior;
    }
}