package services.servicesimpl;

import dao.IUsuarioDao;
import entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.IUserService;

/**
 * Created by Edu on 19/02/2016.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    IUsuarioDao iUsuarioDao;

    public String getMessage(Usuario usuario) {
        return iUsuarioDao.getMessage(usuario);
    }
}
