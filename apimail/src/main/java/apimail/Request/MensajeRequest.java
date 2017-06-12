package apimail.Request;
import apimail.Model.Usuario;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fefe on 8/6/2017.
 */
public class MensajeRequest {

    @JsonProperty("id")
    private int id;
    @JsonProperty("asunto")
    private String asunto;
    @JsonProperty("remitente")
    private Usuario remitente;
    @JsonProperty("destinatarios")
    private ArrayList<Usuario> destinatarios;
    @JsonProperty("body")
    private String body;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public ArrayList<Usuario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<Usuario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
