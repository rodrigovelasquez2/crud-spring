package com.cursojava.curso.repositories;

import com.cursojava.curso.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // == Uusario findByEmail(String email);
}
