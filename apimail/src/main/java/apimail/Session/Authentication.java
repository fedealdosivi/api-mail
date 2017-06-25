package apimail.Session;

import apimail.Model.Usuario;
import org.joda.time.DateTime;

/**
 * Created by fefe on 14/6/2017.
 */
public class Authentication {

    private Usuario usuario;
    private DateTime lastAction;

    public DateTime getLastAction() {
        try {
            return lastAction;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public void setLastAction(DateTime lastAction) {
        try {
            this.lastAction = lastAction;
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Usuario getUsuario() {
        try {
            return usuario;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public void setUsuario(Usuario usuario) {
        try {
            this.usuario = usuario;
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
