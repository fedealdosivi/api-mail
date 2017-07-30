package apimail.Request;

import apimail.Model.Usuario;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by fefe on 8/6/2017.
 */
public class MensajeRequest {

    @JsonProperty("asunto")
    private String asunto;
    @JsonProperty("body")
    private String body;
    @JsonProperty("destinatarios")
    private ArrayList<String> destinatarios;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(ArrayList<String> destinatarios) {
        this.destinatarios = destinatarios;
    }
}
