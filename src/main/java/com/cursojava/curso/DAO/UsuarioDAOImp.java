package com.cursojava.curso.DAO;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Podra acceder al repositorio de la base de datos
@Transactional // Como ejecutara la base de datos
public class UsuarioDAOImp implements UsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager; //JPA puro

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario"; // llamas a la tabla usuario
        return entityManager.createQuery(query).getResultList();
    }//Fin getUsuarios

    @Override
    @Transactional
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }//Fin eliminar


    @Override
    @Transactional
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario); // Guarda en la base de datos
    }//Fin registrar

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email=:email"; //evitas inyeccion SQL
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) { // Lista vacia
            return null; // nulo,
        }//Fin if

        String passwordHashed = lista.get(0).getPassword(); // Obtienes la contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())){
            return lista.get(0);
        }
        return null;
        // Se evaluua la contraseña hash con la contraseña del usuario
    }//Fin verificarCredenciales
}//Fin UsuarioDAOImp
