package org.rcastaneda.springcloud.msvc.usuarios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue()
    private long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;
}