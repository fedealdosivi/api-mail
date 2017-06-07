/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajeria.api.avanzada.Model;

import java.util.ArrayList;

/**
 *
 * @author fefe
 */
public class Mensaje {
    
    
    private int id;
    private static int nextid = 0;
    String asunto;
    Usuario remitente;
    ArrayList<Usuario> destinatarios;
    String body;
    
    public Mensaje()
    {
        id = nextid++;
        asunto="";
        remitente = new Usuario();
        destinatarios= new ArrayList<Usuario>();
        body="";
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
