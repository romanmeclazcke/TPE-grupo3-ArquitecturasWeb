package org.example.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipo_rol;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Usuario> usuarios;

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", tipo_rol='" + tipo_rol + '\'' +
                '}';
    }
}
