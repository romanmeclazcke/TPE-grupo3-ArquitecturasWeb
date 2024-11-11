package org.example.pago.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    private Long id;
    private Date fecha_alta;
    private Double credito;
    private boolean activa;
}
