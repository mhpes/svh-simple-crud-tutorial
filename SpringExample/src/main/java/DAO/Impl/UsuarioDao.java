package dao.impl;

import dao.IUsuarioDao;
import entities.Usuario;
import org.springframework.stereotype.Repository;

/**
 * Created by Edu on 19/02/2016.
 */

@Repository
public class UsuarioDao implements IUsuarioDao {

    public String getMessage(Usuario usuario) {
        return "Mi nombre es " + usuario.getNombre() + " " + usuario.getApellido();
    }
}
