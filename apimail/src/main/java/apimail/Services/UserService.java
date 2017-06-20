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

    public boolean agregarUsuario(String nombre, String apellido, String direccion, int telefono, String password, String email, String pais, String provincia, String ciudad)
    {
        try {
            Usuario user = new Usuario(nombre, apellido, email, password, direccion, telefono, pais, provincia, ciudad);
            daoUsuarios.cargarUsuario(user);
            return true;
        }
        catch(Exception e)
        {
            e.getStackTrace();
            return false;
        }
    }

    public List<Usuario> traerTodos()
    {
        try {
            return daoUsuarios.traerTodos();
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return  null;
        }
    }

    public Usuario traerPodId(int id)
    {
        try {
            return daoUsuarios.traerUsuarioPorId(id);
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    public Usuario login(String email, String password)
    {
        try {
            return daoUsuarios.validarUsuario(email, password);
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return  null;
        }
    }


    public void eliminarUsuario(int id)
    {
        try {
            daoUsuarios.eliminarUsuario(id);
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

    }
}
