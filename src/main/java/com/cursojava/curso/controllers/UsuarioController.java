package com.cursojava.curso.controllers;

import com.cursojava.curso.DAO.UsuarioDAO;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @autor Rodrigo Andres Velasquez Quiroz
 * @date 27/09/2023
 * <p>
 * Clase que sirve para manejar direcciones URLS
 */
@RestController
public class UsuarioController {
    @Autowired // Hace que la clase usuario cree el objeto y la guarde en la variable usuuarioDap
    private UsuarioDAO usuarioDAO;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("09992929");
        return usuario;
    }//Fin Usuario

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) { // Guarda Token en cabecera
        if (!validarToken(token)) {
            return null;
        }
        return usuarioDAO.getUsuarios(); // obtiene los datos en la base de datos
    }//Fin getUsuarios

    private boolean validarToken(String token) {

        //Verificar el token sea correcto:
        String usuarioid = jwtUtil.getKey(token); // extrae el id del uusuario
        return usuarioid != null;
    }//Fin validarToken

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);//Encriptacion de contraseña:
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());//Cantidad de iteraciones
        usuario.setPassword(hash);
        usuarioDAO.registrar(usuario);
    }//Fin registrarUsuario

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token,
                         @PathVariable Long id) {
        if (!validarToken(token)) { return; }
        usuarioDAO.eliminar(id);
    }//Fin eliminar
}//Fin UsuarioController
