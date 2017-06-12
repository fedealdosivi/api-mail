package apimail.Model;

import java.util.ArrayList;
import apimail.Model.Usuario;
/**
 * Created by fefe on 7/6/2017.
 */
public class Mensaje {


    private int id;
    private static int nextid = 0;
    private String asunto;
    private Usuario remitente;
    private ArrayList<Usuario> destinatarios;
    private String body;

    public Mensaje()
    {
        id = nextid++;
        asunto="";
        remitente = new Usuario();
        destinatarios= new ArrayList<Usuario>();
        body="";
    }

    public void agregarDestinatario(Usuario destinatario)
    {
        destinatarios.add(destinatario);
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

