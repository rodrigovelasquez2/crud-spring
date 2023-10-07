package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Rodrigo Andres Velasquez Quiroz
 * @date 28/09/23
 */
@Entity
@Table(name = "usuarios")

@Data //Incluye anotaciones de get and set
@Builder //
@EqualsAndHashCode

//Constructores
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id") // Clave primaria
    private Long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @Column(name="password")
    private String password;

}//Fin Usuario
