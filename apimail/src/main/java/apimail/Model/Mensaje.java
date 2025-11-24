package apimail.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefe on 7/6/2017.
 */
@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    private String nombreRemitente;

    @Column(nullable = false)
    private String remitente;

    @ElementCollection
    @CollectionTable(name = "mensaje_destinatarios", joinColumns = @JoinColumn(name = "mensaje_id"))
    @Column(name = "destinatario")
    private List<String> destinatarios;

    public Mensaje() {
        setAsunto("");
        setBody("");
        setRemitente("");
        setNombreRemitente("");
        destinatarios = new ArrayList<>();
        dateTime = null;
    }

    public void addDestinatario(String e)
    {
        destinatarios.add(e);
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }


    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
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
        if (nombreRemitente != null ? !nombreRemitente.equals(mensaje.nombreRemitente) : mensaje.nombreRemitente != null)
            return false;
        if (remitente != null ? !remitente.equals(mensaje.remitente) : mensaje.remitente != null) return false;
        return destinatarios != null ? destinatarios.equals(mensaje.destinatarios) : mensaje.destinatarios == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (nombreRemitente != null ? nombreRemitente.hashCode() : 0);
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
                ", nombreRemitente='" + nombreRemitente + '\'' +
                ", remitente='" + remitente + '\'' +
                ", destinatarios=" + destinatarios +
                '}';
    }
}

