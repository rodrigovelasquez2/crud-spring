package com.cursojava.curso.DAO;

import com.cursojava.curso.models.Usuario;

import java.util.List;

/**
 *
 * Son las funcioes sin inicializar con las que otras clases trabajara
 */
public interface UsuarioDAO {
    List<Usuario> getUsuarios();
    void eliminar(Long id);
    void registrar(Usuario usuario);
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}//Fin UsarioDAO
