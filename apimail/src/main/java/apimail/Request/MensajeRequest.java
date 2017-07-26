package apimail.Request;

import apimail.Model.Usuario;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by fefe on 8/6/2017.
 */
public class MensajeRequest {

    @JsonProperty("id")
    private int id;
    @JsonProperty("asunto")
    private String asunto;
    @JsonProperty("remitente")
    private String remitente;
    @JsonProperty("body")
    private String body;
    @JsonProperty("datetime")
    private DateTime dateTime;
    @JsonProperty("destinatarios")
    private ArrayList<String> destinatarios;


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

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<String> destinatarios) {
        this.destinatarios = destinatarios;
    }
}
