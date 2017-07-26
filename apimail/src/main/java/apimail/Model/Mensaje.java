package apimail.Model;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by fefe on 7/6/2017.
 */
public class Mensaje {


    private int id;
    private String asunto;
    private String body;
    private DateTime dateTime;
    private String remitente;
    private ArrayList<String> destinatarios;

    public Mensaje() {
        setAsunto("");
        setBody("");
        setRemitente("");
        destinatarios=new ArrayList<String>();
        dateTime=new DateTime();
    }

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


    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mensaje mensaje = (Mensaje) o;

        if (id != mensaje.id) return false;
        if (asunto != null ? !asunto.equals(mensaje.asunto) : mensaje.asunto != null) return false;
        if (body != null ? !body.equals(mensaje.body) : mensaje.body != null) return false;
        if (dateTime != null ? !dateTime.equals(mensaje.dateTime) : mensaje.dateTime != null) return false;
        if (remitente != null ? !remitente.equals(mensaje.remitente) : mensaje.remitente != null) return false;
        return destinatarios != null ? destinatarios.equals(mensaje.destinatarios) : mensaje.destinatarios == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (remitente != null ? remitente.hashCode() : 0);
        result = 31 * result + (destinatarios != null ? destinatarios.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", body='" + body + '\'' +
                ", dateTime=" + dateTime +
                ", remitente='" + remitente + '\'' +
                ", destinatarios=" + destinatarios +
                '}';
    }
}

