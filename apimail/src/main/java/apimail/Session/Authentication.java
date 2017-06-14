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
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
