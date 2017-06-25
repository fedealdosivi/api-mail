package apimail.Model;

/**
 * Created by fefe on 7/6/2017.
 */
public class Mensaje {


    private int id;
    private String asunto;
    private Usuario remitente;
    private Usuario destinatario;
    private String body;

    public Mensaje() {
        setAsunto("");
        setRemitente(new Usuario());
        setDestinatario(new Usuario());
        setBody("");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mensaje mensaje = (Mensaje) o;

        if (id != mensaje.id) return false;
        if (asunto != null ? !asunto.equals(mensaje.asunto) : mensaje.asunto != null) return false;
        if (remitente != null ? !remitente.equals(mensaje.remitente) : mensaje.remitente != null) return false;
        if (destinatario != null ? !destinatario.equals(mensaje.destinatario) : mensaje.destinatario != null)
            return false;
        return body != null ? body.equals(mensaje.body) : mensaje.body == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (remitente != null ? remitente.hashCode() : 0);
        result = 31 * result + (destinatario != null ? destinatario.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", remitente=" + remitente +
                ", destinatario=" + destinatario +
                ", body='" + body + '\'' +
                '}';
    }
}

