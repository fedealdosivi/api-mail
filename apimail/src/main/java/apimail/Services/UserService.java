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
    private
    DaoUsuarios daoUsuarios;

    public boolean agregarUsuario(String nombre, String apellido, String direccion, int telefono, String password, String email, String pais, String provincia, String ciudad) {
        try {
            Usuario user = new Usuario(nombre, apellido, email, password, direccion, telefono, pais, provincia, ciudad);
            getDaoUsuarios().cargarUsuario(user);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public List<Usuario> traerTodos() {
        try {
            return getDaoUsuarios().traerTodos();
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public Usuario traerPodId(int id) {
        try {
            return getDaoUsuarios().traerUsuarioPorId(id);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public Usuario traerPorNombre(String nombre) {
        try {
            return getDaoUsuarios().traerUserPorNombre(nombre);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public Usuario login(String email, String password) {
        try {
            return getDaoUsuarios().validarUsuario(email, password);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }


    public void eliminarUsuario(int id) {
        try {
            getDaoUsuarios().eliminarUsuario(id);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public DaoUsuarios getDaoUsuarios() {
        return daoUsuarios;
    }

    public void setDaoUsuarios(DaoUsuarios daoUsuarios) {
        this.daoUsuarios = daoUsuarios;
    }
}
