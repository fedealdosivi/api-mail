package apimail.Services;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefe on 8/6/2017.
 */
@Service
public class MensajeService {

    @Autowired
    DaoMensajes daoMensajes;

    public List<Mensaje> traerTodos()
    {
        try {
            return daoMensajes.traerTodos();
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    public Mensaje traerPorId(int id)
    {
        try {
            return daoMensajes.traerMensajePorId(id);
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    public boolean agregarMensaje(int id, String asunto, String body, Usuario remitente, Usuario destinatario)
    {
        try {
            Mensaje mensaje = new Mensaje();
            mensaje.setId(id);
            mensaje.setRemitente(remitente);
            mensaje.setBody(body);
            mensaje.setAsunto(asunto);
            mensaje.setDestinatario(destinatario);
            daoMensajes.cargarMensaje(mensaje);
            return true;
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return false;
        }
    }

    public ArrayList<Mensaje> traerArrayTodos()
    {
        try {
            return daoMensajes.traerTodos();
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    public boolean eliminarMensaje(int id)
    {
        try {
            daoMensajes.eliminarMensaje(id);
            return true;
        }
        catch(Exception e)
        {
            e.getStackTrace();
            return false;
        }
    }

}
