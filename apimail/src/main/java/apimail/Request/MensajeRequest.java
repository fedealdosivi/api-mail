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
    @JsonProperty("destinatario")
    private Usuario destinatario;
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

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
