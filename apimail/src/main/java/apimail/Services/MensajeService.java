package apimail.Services;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by fefe on 8/6/2017.
 */
@Service
public class MensajeService {

    @Autowired
    private
    DaoMensajes daoMensajes;

    public Mensaje traerPorId(int id) {
        try {
            return getDaoMensajes().traerMensajePorId(id);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public boolean agregarMensaje(int id, String asunto, String body, Usuario remitente, Usuario destinatario) {
        try {
            Mensaje mensaje = new Mensaje();
            mensaje.setId(id);
            mensaje.setRemitente(remitente);
            mensaje.setBody(body);
            mensaje.setAsunto(asunto);
            mensaje.setDestinatario(destinatario);
            getDaoMensajes().cargarMensaje(mensaje);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean eliminarMensaje(int id) {
        try {
            getDaoMensajes().eliminarMensaje(id);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean cambiarAEliminado(int idMensaje) {
        try {
            getDaoMensajes().cambiarEliminado(idMensaje);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public ArrayList<Mensaje> traerEliminados() {
        try {
            return getDaoMensajes().traerMensajesEliminados();
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<Mensaje> traerEnviados() {
        try {
            return getDaoMensajes().traerMensajesEnviados();
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public ArrayList<Mensaje> traerRecibidos() {
        try {
            return getDaoMensajes().traerMensajesRecibidos();
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public DaoMensajes getDaoMensajes() {
        return daoMensajes;
    }

    public void setDaoMensajes(DaoMensajes daoMensajes) {
        this.daoMensajes = daoMensajes;
    }
}

