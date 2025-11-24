package apimail.Session;

import apimail.Model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by fefe on 14/6/2017.
 */
@Service
public class Authentication {

    private Usuario usuario;
    private LocalDateTime lastAction;

    public LocalDateTime getLastAction() {
        try {
            return lastAction;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public void setLastAction(LocalDateTime lastAction) {
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
