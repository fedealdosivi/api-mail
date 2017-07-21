package apimail.Response;


import apimail.Model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by fefe on 8/6/2017.
 */
public class MensajeResponse {

    @JsonProperty
    private int id;
    @JsonProperty
    private String asunto;
    @JsonProperty
    private Usuario remitente;
    @JsonProperty
    private Usuario destinatario;
    @JsonProperty
    private String body;
    @JsonProperty("datetime")
    private DateTime dateTime;
    @JsonProperty("destinatarios")
    private ArrayList<Usuario> destinatarios;

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

    public ArrayList<Usuario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<Usuario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
