package apimail.Services;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return daoMensajes.traerTodos();
    }

    public Mensaje traerPorId(int id)
    {
        return daoMensajes.traerMensajePorId(id);
    }

    public void agregarMensaje(int id, String asunto, String body, Usuario remitente, Usuario destinatario)
    {
        Mensaje mensaje=new Mensaje();
        mensaje.setId(id);
        mensaje.setRemitente(remitente);
        mensaje.setBody(body);
        mensaje.setAsunto(asunto);
        mensaje.setDestinatario(destinatario);
        daoMensajes.cargarMensaje(mensaje);
    }

    public ArrayList<Mensaje> traerArrayTodos()
    {
        return daoMensajes.traerTodos();
    }

    public void eliminarMensaje(int id)
    {
        daoMensajes.eliminarMensaje(id);
    }

}
