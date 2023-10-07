package com.cursojava.curso.controllers;
import com.cursojava.curso.DAO.UsuarioDAO;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
       public String login(@RequestBody Usuario usuario) {
        Usuario usuarioLogeado = usuarioDAO.obtenerUsuarioPorCredenciales(usuario);

        if (usuarioLogeado!=null) {
            String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()),usuarioLogeado.getEmail());
            return tokenJWT;
        }//Fin if
        return "FAIL";
    }//Fin Usuario
}//Fin AuthController
