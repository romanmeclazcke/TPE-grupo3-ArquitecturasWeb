package org.example.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;
    private String apellido;
    private String email;
    private Long telefono;

    @ManyToMany()
    private List<Cuenta> cuentas;

    @ManyToOne()
    private Rol rol;


}
