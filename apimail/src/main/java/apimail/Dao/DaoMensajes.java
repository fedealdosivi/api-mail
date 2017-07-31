/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;

import apimail.Model.Mensaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import apimail.Session.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @author fefe
 */
@Repository
public class DaoMensajes extends AbstractDao {

    @Autowired
    Authentication authentication;


    @Autowired
    public DaoMensajes(Connection connection) {
        super(connection);
    }


    public void cargarMensaje(Mensaje mensaje) throws SQLException {
        try {

            mensaje.toString();
            String sq = "INSERT INTO MESSAGES(IDSENDER,SUBJECT,BODY) VALUES(?,?,?)";
            PreparedStatement st = this.connection.prepareStatement(sq, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, authentication.getUsuario().getId());
            st.setString(2, mensaje.getAsunto());
            st.setString(3, mensaje.getBody());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {

                int id=rs.getInt(1);
                System.out.println("ENTRO AL RESUL KEY");
                for (String e : mensaje.getDestinatarios()) {
                    System.out.println("ENTRO AL DEST");
                    String query = "CALL saveRecipientByMessage(?,?)";
                    CallableStatement st2 = this.connection.prepareCall(query);
                    st2.setInt(1,id);
                    st2.setString(2, e);
                    st2.execute();
                    System.out.println("EJECUTO");
                }
            }
            else
            {
                throw new Exception("no hay results key");
            }


        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void eliminarMensaje(int idMensaje) {
        try {
            String sq = "CALL deleteMessage(?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setInt(1, idMensaje);
            st.execute();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Mensaje traerMensajePorId(int id) {
        Mensaje m = null;

        try {
            String query = ("CALL getMessageById(?)");
            CallableStatement st = this.connection.prepareCall(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (m == null) {
                    System.out.println("ENTRO PORQUE ES NULL");
                    m = new Mensaje();
                    m.setId(rs.getInt("ME.IDMESSAGE"));
                    m.setAsunto(rs.getString("ME.SUBJECT"));
                    m.setBody(rs.getString("ME.BODY"));
                    m.setRemitente(rs.getString("SENDER"));
                    m.setDateTime(rs.getTimestamp("ME.TS"));
                    m.addDestinatario(rs.getString("RECIPIENT"));
                } else {
                    System.out.println("AGREGO UN DEST PORQUE NO ES NULL");
                    m.addDestinatario(rs.getString("RECIPIENT"));
                }

            }
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("EXPLOTO TODOOOOO");
        }
        return m;
    }

    public ArrayList<Mensaje> traerMensajesEnviados() {
        ArrayList<Mensaje> lista = null;

        try {
            String query = "CALL getSent(?)";
            CallableStatement st = this.connection.prepareCall(query);
            st.setInt(1, authentication.getUsuario().getId());
            System.out.println("antes de ejectuar");
            ResultSet rs = st.executeQuery();
            System.out.println("despues de ejecutar");
            lista = new ArrayList<Mensaje>();
            Mensaje m = new Mensaje();

            while (rs.next()) {

                System.out.println("TRAJO UN RESULT SET");


                int numero = rs.getInt("ME.IDMESSAGE");
                if (numero == m.getId()) {

                    for (Mensaje me : lista) {
                        if (me.getId() == rs.getInt("ME.IDMESSAGE")) {
                            me.addDestinatario(rs.getString("RECIPIENT"));
                            System.out.println("AGREGO UN DESTINATARIO");
                        }
                    }
                } else {
                    System.out.println("ENTROOOO");
                    m = new Mensaje();
                    m.setId(rs.getInt("ME.IDMESSAGE"));
                    m.setAsunto(rs.getString("ME.SUBJECT"));
                    m.setBody(rs.getString("ME.BODY"));
                    m.setRemitente(rs.getString("SENDER"));
                    m.setDateTime(rs.getTimestamp("ME.TS"));
                    m.addDestinatario(rs.getString("RECIPIENT"));
                    lista.add(m);
                }
            }
            if (lista != null) {
                for (Mensaje men : lista
                        ) {
                    System.out.println(men.toString());
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("EXPLOTAAAAA TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            return null;
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesEliminados() {
        ArrayList<Mensaje> lista = null;
        try {
            String query = "CALL getTrash(?)";

            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, authentication.getUsuario().getId());
            System.out.println("antes de ejectuar");
            ResultSet rs = st.executeQuery();
            System.out.println("despues de ejecutar");
            lista = new ArrayList<Mensaje>();
            Mensaje m = new Mensaje();

            while (rs.next()) {

                System.out.println("TRAJO UN RESULT SET");


                int numero = rs.getInt("ME.IDMESSAGE");
                if (numero == m.getId()) {

                    for (Mensaje me : lista) {
                        if (me.getId() == rs.getInt("ME.IDMESSAGE")) {
                            me.addDestinatario(rs.getString("RECIPIENT"));
                            System.out.println("AGREGO UN DESTINATARIO");
                        }
                    }
                } else {
                    System.out.println("ENTROOOO");
                    m = new Mensaje();
                    m.setId(rs.getInt("ME.IDMESSAGE"));
                    m.setAsunto(rs.getString("ME.SUBJECT"));
                    m.setBody(rs.getString("ME.BODY"));
                    m.setRemitente(rs.getString("SENDER"));
                    m.setDateTime(rs.getTimestamp("ME.TS"));
                    m.addDestinatario(rs.getString("RECIPIENT"));
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesRecibidos() {
        ArrayList<Mensaje> lista = null;
        try {

            String query = "CALL getInbox(?)";

            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, authentication.getUsuario().getId());
            System.out.println("antes de ejectuar");
            ResultSet rs = st.executeQuery();
            System.out.println("despues de ejecutar");
            lista = new ArrayList<Mensaje>();
            Mensaje m = new Mensaje();

            while (rs.next()) {

                System.out.println("TRAJO UN RESULT SET");


                int numero = rs.getInt("ME.IDMESSAGE");
                if (numero == m.getId()) {

                    for (Mensaje me : lista) {
                        if (me.getId() == rs.getInt("ME.IDMESSAGE")) {
                            me.addDestinatario(rs.getString("RECIPIENT"));
                            System.out.println("AGREGO UN DESTINATARIO");
                        }
                    }
                } else {
                    System.out.println("ENTROOOO");
                    m = new Mensaje();
                    m.setId(rs.getInt("ME.IDMESSAGE"));
                    m.setAsunto(rs.getString("ME.SUBJECT"));
                    m.setBody(rs.getString("ME.BODY"));
                    m.setRemitente(rs.getString("SENDER"));
                    m.setDateTime(rs.getTimestamp("ME.TS"));
                    m.addDestinatario(rs.getString("RECIPIENT"));
                    lista.add(m);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }


    public void cambiarEliminado(int idMensaje) {
        try {
            String query = "CALL setTrash(?)";
            CallableStatement st = this.connection.prepareCall(query);
            st.setInt(1, idMensaje);
            st.execute();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void cambiarEliminadoMuchos(List<Integer> lista)
    {
        try{
            String query = "CALL setTrash(?)";
            CallableStatement st = this.connection.prepareCall(query);
            for (int id:lista
                 ) {
                st.setInt(1, id);
                st.executeUpdate();
            }
        }catch(Exception e)
        {
            e.getStackTrace();
        }
    }

}
