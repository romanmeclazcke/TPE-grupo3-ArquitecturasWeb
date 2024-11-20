package org.example.usuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Cuenta> cuentas;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;



}
