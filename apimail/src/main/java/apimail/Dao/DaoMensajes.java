/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;

import apimail.Model.Mensaje;

import java.sql.*;
import java.util.ArrayList;

import apimail.Model.Usuario;
import apimail.Session.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * @author fefe
 */
@Repository
public class DaoMensajes extends AbstractDao{

    @Autowired
    Authentication authentication;


    public DaoMensajes(Connection connection) {
        super(connection);
    }


    public void cargarMensaje(Mensaje mensaje) {
        try {

            String sq = "INSERT INTO MESSAGES(IDSENDER,SUBJECT,BODY) VALUES(?,?,?)";
            PreparedStatement st = this.connection.prepareStatement(sq,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, authentication.getUsuario().getId());
            st.setString(2, mensaje.getAsunto());
            st.setString(3, mensaje.getBody());
            st.executeUpdate();
            ResultSet rs=st.getGeneratedKeys();

            for(String e:mensaje.getDestinatarios()){

                String query = "CALL saveRecipientByMessage(?,?)";
                CallableStatement st2 = this.connection.prepareCall(query);
                st2.setInt(1,rs.getInt(1));
                st2.setString(2,e);
                st2.executeUpdate();
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void eliminarMensaje(int idMensaje) {
        try {
            String sq = "deleteMessage(?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setInt(1, idMensaje);
            st.executeUpdate();
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
            st.execute();
            ResultSet rs = st.getResultSet();

            if (rs.next()) {
                m = new Mensaje();
                m.setId(rs.getInt("m.IDMENSAJE"));
                m.setAsunto(rs.getString("m.ASUNTO"));
                m.setBody(rs.getString("m.BODY"));

                Usuario remitente = new Usuario();
                remitente.setId(rs.getInt("uR.IDUSUARIO"));
                remitente.setNombre(rs.getString("uR.NOMBRE"));
                remitente.setApellido(rs.getString("uR.APELLIDO"));
                remitente.setPassword(rs.getString("uR.PASSWORD"));
                remitente.setEmail(rs.getString("uR.EMAIL"));
                remitente.setDireccion(rs.getString("uR.DIRECCION"));
                remitente.setTelefono(rs.getInt("uR.TELEFONO"));
                remitente.setPais(rs.getString("uR.PAIS"));
                remitente.setProvincia(rs.getString("uR.PROVINCIA"));
                remitente.setCiudad(rs.getString("uR.CIUDAD"));
                //m.setRemitente(remitente);

                Usuario dest = new Usuario();
                dest.setId(rs.getInt("uD.IDUSUARIO"));
                dest.setNombre(rs.getString("uD.NOMBRE"));
                dest.setApellido(rs.getString("uD.APELLIDO"));
                dest.setPassword(rs.getString("uD.PASSWORD"));
                dest.setEmail(rs.getString("uD.EMAIL"));
                dest.setDireccion(rs.getString("uD.DIRECCION"));
                dest.setTelefono(rs.getInt("uD.TELEFONO"));
                dest.setPais(rs.getString("uD.PAIS"));
                dest.setProvincia(rs.getString("uD.PROVINCIA"));
                dest.setCiudad(rs.getString("uD.CIUDAD"));
                //m.setDestinatario(dest);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return m;
    }

    public ArrayList<Mensaje> traerMensajesEnviados() {
        ArrayList<Mensaje> lista = null;

        try {
            String query = "Select m.IDMENSAJE, m.ASUNTO,m.BODY"
                    + ",uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD"
                    + ",uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD"
                    + " FROM MENSAJES as m join USUARIOS as uD on m.IDDESTINATARIO = uD.IDUSUARIO join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO"
                    + " WHERE m.IDREMITENTE=?";

            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, authentication.getUsuario().getId());
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            while (rs.next()) {

                Mensaje m = new Mensaje();
                m.setId(rs.getInt("m.IDMENSAJE"));
                m.setAsunto(rs.getString("m.ASUNTO"));
                m.setBody(rs.getString("m.BODY"));

                Usuario remitente = new Usuario();
                remitente.setId(rs.getInt("uR.IDUSUARIO"));
                remitente.setNombre(rs.getString("uR.NOMBRE"));
                remitente.setApellido(rs.getString("uR.APELLIDO"));
                remitente.setPassword(rs.getString("uR.PASSWORD"));
                remitente.setEmail(rs.getString("uR.EMAIL"));
                remitente.setDireccion(rs.getString("uR.DIRECCION"));
                remitente.setTelefono(rs.getInt("uR.TELEFONO"));
                remitente.setPais(rs.getString("uR.PAIS"));
                remitente.setProvincia(rs.getString("uR.PROVINCIA"));
                remitente.setCiudad(rs.getString("uR.CIUDAD"));
                //m.setRemitente(remitente);

                Usuario dest = new Usuario();
                dest.setId(rs.getInt("uD.IDUSUARIO"));
                dest.setNombre(rs.getString("uD.NOMBRE"));
                dest.setApellido(rs.getString("uD.APELLIDO"));
                dest.setPassword(rs.getString("uD.PASSWORD"));
                dest.setEmail(rs.getString("uD.EMAIL"));
                dest.setDireccion(rs.getString("uD.DIRECCION"));
                dest.setTelefono(rs.getInt("uD.TELEFONO"));
                dest.setPais(rs.getString("uD.PAIS"));
                dest.setProvincia(rs.getString("uD.PROVINCIA"));
                dest.setCiudad(rs.getString("uD.CIUDAD"));
                //m.setDestinatario(dest);

                lista.add(m);
            }

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesEliminados() {
        ArrayList<Mensaje> lista = null;
        try {
            String query = "Select m.IDMENSAJE, m.ASUNTO,m.BODY"
                    + ",uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD"
                    + ",uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD"
                    + " FROM MENSAJES as m join USUARIOS as uD on m.IDDESTINATARIO = uD.IDUSUARIO join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO"
                    + " WHERE m.IDDESTINATARIO=? AND m.ELIMINADO=TRUE";

            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, authentication.getUsuario().getId());
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            while (rs.next()) {

                Mensaje m = new Mensaje();
                m.setId(rs.getInt("m.IDMENSAJE"));
                m.setAsunto(rs.getString("m.ASUNTO"));
                m.setBody(rs.getString("m.BODY"));

                Usuario remitente = new Usuario();
                remitente.setId(rs.getInt("uR.IDUSUARIO"));
                remitente.setNombre(rs.getString("uR.NOMBRE"));
                remitente.setApellido(rs.getString("uR.APELLIDO"));
                remitente.setPassword(rs.getString("uR.PASSWORD"));
                remitente.setEmail(rs.getString("uR.EMAIL"));
                remitente.setDireccion(rs.getString("uR.DIRECCION"));
                remitente.setTelefono(rs.getInt("uR.TELEFONO"));
                remitente.setPais(rs.getString("uR.PAIS"));
                remitente.setProvincia(rs.getString("uR.PROVINCIA"));
                remitente.setCiudad(rs.getString("uR.CIUDAD"));
                //m.setRemitente(remitente);

                Usuario dest = new Usuario();
                dest.setId(rs.getInt("uD.IDUSUARIO"));
                dest.setNombre(rs.getString("uD.NOMBRE"));
                dest.setApellido(rs.getString("uD.APELLIDO"));
                dest.setPassword(rs.getString("uD.PASSWORD"));
                dest.setEmail(rs.getString("uD.EMAIL"));
                dest.setDireccion(rs.getString("uD.DIRECCION"));
                dest.setTelefono(rs.getInt("uD.TELEFONO"));
                dest.setPais(rs.getString("uD.PAIS"));
                dest.setProvincia(rs.getString("uD.PROVINCIA"));
                dest.setCiudad(rs.getString("uD.CIUDAD"));
                //m.setDestinatario(dest);

                lista.add(m);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesRecibidos() {
        ArrayList<Mensaje> lista = null;
        try {

            String query = "Select m.IDMENSAJE, m.ASUNTO,m.BODY"
                    + ",uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD"
                    + ",uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD"
                    + " FROM MENSAJES as m join USUARIOS as uD on m.IDDESTINATARIO = uD.IDUSUARIO join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO"
                    + " WHERE m.IDDESTINATARIO=? AND m.ELIMINADO=FALSE";

            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, authentication.getUsuario().getId());
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            while (rs.next()) {

                Mensaje m = new Mensaje();
                m.setId(rs.getInt("m.IDMENSAJE"));
                m.setAsunto(rs.getString("m.ASUNTO"));
                m.setBody(rs.getString("m.BODY"));

                Usuario remitente = new Usuario();
                remitente.setId(rs.getInt("uR.IDUSUARIO"));
                remitente.setNombre(rs.getString("uR.NOMBRE"));
                remitente.setApellido(rs.getString("uR.APELLIDO"));
                remitente.setPassword(rs.getString("uR.PASSWORD"));
                remitente.setEmail(rs.getString("uR.EMAIL"));
                remitente.setDireccion(rs.getString("uR.DIRECCION"));
                remitente.setTelefono(rs.getInt("uR.TELEFONO"));
                remitente.setPais(rs.getString("uR.PAIS"));
                remitente.setProvincia(rs.getString("uR.PROVINCIA"));
                remitente.setCiudad(rs.getString("uR.CIUDAD"));
                //m.setRemitente(remitente);

                Usuario dest = new Usuario();
                dest.setId(rs.getInt("uD.IDUSUARIO"));
                dest.setNombre(rs.getString("uD.NOMBRE"));
                dest.setApellido(rs.getString("uD.APELLIDO"));
                dest.setPassword(rs.getString("uD.PASSWORD"));
                dest.setEmail(rs.getString("uD.EMAIL"));
                dest.setDireccion(rs.getString("uD.DIRECCION"));
                dest.setTelefono(rs.getInt("uD.TELEFONO"));
                dest.setPais(rs.getString("uD.PAIS"));
                dest.setProvincia(rs.getString("uD.PROVINCIA"));
                dest.setCiudad(rs.getString("uD.CIUDAD"));
                //m.setDestinatario(dest);

                lista.add(m);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }


    public void cambiarEliminado(int idMensaje) {
        try {
            String query = "CALL setTrash(1)";
            CallableStatement st = this.connection.prepareCall(query);
            st.setInt(1, idMensaje);
            st.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
