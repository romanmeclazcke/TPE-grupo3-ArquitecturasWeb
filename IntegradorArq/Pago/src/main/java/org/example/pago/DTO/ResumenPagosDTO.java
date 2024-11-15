package org.example.pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumenPagosDTO {

    private Double total;

    public ResumenPagosDTO(double total) {
        this.total = total;
    }
}