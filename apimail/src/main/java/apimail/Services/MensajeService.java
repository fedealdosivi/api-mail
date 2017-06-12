package apimail.Services;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fefe on 8/6/2017.
 */
@Service
public class MensajeService {

    @Autowired
    DaoMensajes daoMensajes;

    public MensajeService()
    {

    }

    public List<Mensaje> traerTodos()
    {
        return daoMensajes.traerTodos();
    }

    public Mensaje traerPorId(int id)
    {
        return daoMensajes.traerMensajePorId(id);
    }

    public void agregarMensaje(Mensaje mensaje)
    {

    }

    public void eliminarMensaje(Mensaje mensaje)
    {
        daoMensajes.eliminarMensaje(mensaje);
    }

}
