package com.cursojava.curso.services;

import com.cursojava.curso.DAO.UsuarioDAO;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.repositories.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //ESta anotacion es para que spring sepa que es un servicio de tu app
@Slf4j // Para que se puedan ver los logs (consola)
public class UsuarioService implements UsuarioDAO {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true) //Este es solo de lectura
    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    @Override
    @Transactional //Si modifica el registro va asi
    public void eliminar(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void registrar(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true) //Este es solo de lectura
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(usuario.getEmail());
        Usuario usuarioEncontrado = null;
        if(usuarioOptional.isPresent()){ //este usuario esta presente?
            usuarioEncontrado = usuarioOptional.get();
            String passwordHashed = usuarioEncontrado.getPassword();
            log.info("Password hashed: " + passwordHashed); //System.out.println("")
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (argon2.verify(passwordHashed, usuario.getPassword())){
                log.info("Usuario encontrado! {}", usuarioEncontrado);
                return usuarioEncontrado;
            }
        }
        return usuarioEncontrado;
    }
}
