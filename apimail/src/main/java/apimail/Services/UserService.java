package apimail.Services;

import apimail.Dao.DaoUsuarios;
import apimail.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fefe on 8/6/2017.
 */
@Service
public class UserService {

    @Autowired
    DaoUsuarios daoUsuarios;

    public void agregarUsuario(int id, String nombre, String apellido, String direccion, int telefono, String password, String email, String pais, String provincia, String ciudad)
    {
        Usuario user=new Usuario(id,nombre,apellido,email,password,direccion,telefono,pais,provincia,ciudad);
        daoUsuarios.cargarUsuario(user);
    }

    public List<Usuario> traerTodos()
    {
        return daoUsuarios.traerTodos();
    }

    public Usuario traerPodId(int id)
    {
        return daoUsuarios.traerUsuarioPorId(id);
    }

    public Usuario login(String email, String password)
    {
        return daoUsuarios.validarUsuario(email,password);
    }

}
