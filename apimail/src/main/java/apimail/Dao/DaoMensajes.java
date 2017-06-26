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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author fefe
 */
@Repository
public class DaoMensajes extends Conexion{


    @Autowired
    public DaoMensajes(@Value("${db.username}") String dbUserName, @Value("${db.name}") String dbName, @Value("${db.password}") String dbPassword, @Value("${db.port}") String dbPort, @Value("${db.host}") String dbHost) {
        super(dbUserName,dbName,dbPassword,dbPort,dbHost);
    }


    public void cargarMensaje(Mensaje mensaje) {
        try {

            String query = "INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, mensaje.getRemitente().getId());
            st.setInt(1, mensaje.getDestinatario().getId());
            st.setString(3, mensaje.getAsunto());
            st.setString(4, mensaje.getBody());
            st.execute();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void eliminarMensaje(int idMensaje) {
        try {
            String sq = "delete from MENSAJES where IDMENSAJE=?";
            PreparedStatement st = conn.prepareStatement(sq);
            st.setInt(1, idMensaje);
            st.execute();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<Mensaje> traerTodos() {
        ArrayList<Mensaje> lista = null;
        try {
            //TRAIGO TODOS LOS MENSAJES

            String traerMensajes = "select * from MENSAJES";
            PreparedStatement st = conn.prepareStatement(traerMensajes);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS

            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente = rs.getInt("IDREMITENTE");
                int idDestinatario = rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.prepareStatement(traerRemitente);
                statementRemitente.setInt(1, idRemitente);
                ResultSet rsRemitente = statementRemitente.executeQuery();

                if (rsRemitente.next()) {
                    Usuario remitente = new Usuario();
                    remitente.setId(rsRemitente.getInt("IDUSUARIO"));
                    remitente.setNombre(rsRemitente.getString("NOMBRE"));
                    remitente.setApellido(rsRemitente.getString("APELLIDO"));
                    remitente.setPassword(rsRemitente.getString("PASSWORD"));
                    remitente.setEmail(rsRemitente.getString("EMAIL"));
                    remitente.setDireccion(rsRemitente.getString("DIRECCION"));
                    remitente.setTelefono(rsRemitente.getInt("TELEFONO"));
                    remitente.setPais(rsRemitente.getString("PAIS"));
                    remitente.setProvincia(rsRemitente.getString("PROVINCIA"));
                    remitente.setCiudad(rsRemitente.getString("CIUDAD"));
                    m.setRemitente(remitente);
                }

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.prepareStatement(traerRemitente);
                statementDestinatario.setInt(1, idDestinatario);
                ResultSet rsDest = statementDestinatario.executeQuery();

                if (rsDest.next()) {
                    Usuario dest = new Usuario();
                    dest.setId(rsDest.getInt("IDUSUARIO"));
                    dest.setNombre(rsDest.getString("NOMBRE"));
                    dest.setApellido(rsDest.getString("APELLIDO"));
                    dest.setPassword(rsDest.getString("PASSWORD"));
                    dest.setEmail(rsDest.getString("EMAIL"));
                    dest.setDireccion(rsDest.getString("DIRECCION"));
                    dest.setTelefono(rsDest.getInt("TELEFONO"));
                    dest.setPais(rsDest.getString("PAIS"));
                    dest.setProvincia(rsDest.getString("PROVINCIA"));
                    dest.setCiudad(rsDest.getString("CIUDAD"));
                    m.setDestinatario(dest);
                }


                //UNA VEZ TRAIDOS TODOS LOS DATOS AGREGO A LA LISTA

                lista.add(m);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }

    public Mensaje traerMensajePorId(int id) {
        Mensaje m = null;

        try {
            String sq = "select * from MENSAJES where IDMENSAJE=?";
            PreparedStatement st = conn.prepareStatement(sq);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                m = new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente = rs.getInt("IDREMITENTE");
                int idDestinatario = rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.prepareStatement(traerRemitente);
                statementRemitente.setInt(1, idRemitente);
                ResultSet rsRemitente = statementRemitente.executeQuery();

                if (rsRemitente.next()) {
                    Usuario remitente = new Usuario();
                    remitente.setId(rsRemitente.getInt("IDUSUARIO"));
                    remitente.setNombre(rsRemitente.getString("NOMBRE"));
                    remitente.setApellido(rsRemitente.getString("APELLIDO"));
                    remitente.setPassword(rsRemitente.getString("PASSWORD"));
                    remitente.setEmail(rsRemitente.getString("EMAIL"));
                    remitente.setDireccion(rsRemitente.getString("DIRECCION"));
                    remitente.setTelefono(rsRemitente.getInt("TELEFONO"));
                    remitente.setPais(rsRemitente.getString("PAIS"));
                    remitente.setProvincia(rsRemitente.getString("PROVINCIA"));
                    remitente.setCiudad(rsRemitente.getString("CIUDAD"));
                    m.setRemitente(remitente);
                }

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.prepareStatement(traerRemitente);
                statementDestinatario.setInt(1, idDestinatario);
                ResultSet rsDest = statementDestinatario.executeQuery();

                if (rsDest.next()) {
                    Usuario dest = new Usuario();
                    dest.setId(rsDest.getInt("IDUSUARIO"));
                    dest.setNombre(rsDest.getString("NOMBRE"));
                    dest.setApellido(rsDest.getString("APELLIDO"));
                    dest.setPassword(rsDest.getString("PASSWORD"));
                    dest.setEmail(rsDest.getString("EMAIL"));
                    dest.setDireccion(rsDest.getString("DIRECCION"));
                    dest.setTelefono(rsDest.getInt("TELEFONO"));
                    dest.setPais(rsDest.getString("PAIS"));
                    dest.setProvincia(rsDest.getString("PROVINCIA"));
                    dest.setCiudad(rsDest.getString("CIUDAD"));
                    m.setDestinatario(dest);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return m;
    }

    public void cambiarLeido(int idMensaje) {
        try {
            String query = "update MENSAJES set LEIDO = TRUE WHERE IDMENSAJE = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idMensaje);
            st.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void cambiarEliminado(int idMensaje) {
        try {
            String query = "update MENSAJES set ELIMINADO = TRUE WHERE IDMENSAJE = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idMensaje);
            st.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<Mensaje> traerMensajesEnviados(int idUsuario) {
        ArrayList<Mensaje> lista = null;

        try {
            String traerMensajes = "select * from MENSAJES where IDREMITENTE=?";
            PreparedStatement st = conn.prepareStatement(traerMensajes);
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS

            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente = rs.getInt("IDREMITENTE");
                int idDestinatario = rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.prepareStatement(traerRemitente);
                statementRemitente.setInt(1, idRemitente);
                ResultSet rsRemitente = statementRemitente.executeQuery();

                if (rsRemitente.next()) {
                    Usuario remitente = new Usuario();
                    remitente.setId(rsRemitente.getInt("IDUSUARIO"));
                    remitente.setNombre(rsRemitente.getString("NOMBRE"));
                    remitente.setApellido(rsRemitente.getString("APELLIDO"));
                    remitente.setPassword(rsRemitente.getString("PASSWORD"));
                    remitente.setEmail(rsRemitente.getString("EMAIL"));
                    remitente.setDireccion(rsRemitente.getString("DIRECCION"));
                    remitente.setTelefono(rsRemitente.getInt("TELEFONO"));
                    remitente.setPais(rsRemitente.getString("PAIS"));
                    remitente.setProvincia(rsRemitente.getString("PROVINCIA"));
                    remitente.setCiudad(rsRemitente.getString("CIUDAD"));
                    m.setRemitente(remitente);
                }

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.prepareStatement(traerRemitente);
                statementDestinatario.setInt(1, idDestinatario);
                ResultSet rsDest = statementDestinatario.executeQuery();

                if (rsDest.next()) {
                    Usuario dest = new Usuario();
                    dest.setId(rsDest.getInt("IDUSUARIO"));
                    dest.setNombre(rsDest.getString("NOMBRE"));
                    dest.setApellido(rsDest.getString("APELLIDO"));
                    dest.setPassword(rsDest.getString("PASSWORD"));
                    dest.setEmail(rsDest.getString("EMAIL"));
                    dest.setDireccion(rsDest.getString("DIRECCION"));
                    dest.setTelefono(rsDest.getInt("TELEFONO"));
                    dest.setPais(rsDest.getString("PAIS"));
                    dest.setProvincia(rsDest.getString("PROVINCIA"));
                    dest.setCiudad(rsDest.getString("CIUDAD"));
                    m.setDestinatario(dest);
                }


                //UNA VEZ TRAIDOS TODOS LOS DATOS AGREGO A LA LISTA

                lista.add(m);
            }

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesEliminados(int idUsuario) {
        ArrayList<Mensaje> lista = null;
        try {
            String traerMensajes = "select * from MENSAJES where ELIMINADO=TRUE and IDREMITENTE=?";
            PreparedStatement st = conn.prepareStatement(traerMensajes);
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS

            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente = rs.getInt("IDREMITENTE");
                int idDestinatario = rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.prepareStatement(traerRemitente);
                statementRemitente.setInt(1, idRemitente);
                ResultSet rsRemitente = statementRemitente.executeQuery();

                if (rsRemitente.next()) {
                    Usuario remitente = new Usuario();
                    remitente.setId(rsRemitente.getInt("IDUSUARIO"));
                    remitente.setNombre(rsRemitente.getString("NOMBRE"));
                    remitente.setApellido(rsRemitente.getString("APELLIDO"));
                    remitente.setPassword(rsRemitente.getString("PASSWORD"));
                    remitente.setEmail(rsRemitente.getString("EMAIL"));
                    remitente.setDireccion(rsRemitente.getString("DIRECCION"));
                    remitente.setTelefono(rsRemitente.getInt("TELEFONO"));
                    remitente.setPais(rsRemitente.getString("PAIS"));
                    remitente.setProvincia(rsRemitente.getString("PROVINCIA"));
                    remitente.setCiudad(rsRemitente.getString("CIUDAD"));
                    m.setRemitente(remitente);
                }

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.prepareStatement(traerRemitente);
                statementDestinatario.setInt(1, idDestinatario);
                ResultSet rsDest = statementDestinatario.executeQuery();

                if (rsDest.next()) {
                    Usuario dest = new Usuario();
                    dest.setId(rsDest.getInt("IDUSUARIO"));
                    dest.setNombre(rsDest.getString("NOMBRE"));
                    dest.setApellido(rsDest.getString("APELLIDO"));
                    dest.setPassword(rsDest.getString("PASSWORD"));
                    dest.setEmail(rsDest.getString("EMAIL"));
                    dest.setDireccion(rsDest.getString("DIRECCION"));
                    dest.setTelefono(rsDest.getInt("TELEFONO"));
                    dest.setPais(rsDest.getString("PAIS"));
                    dest.setProvincia(rsDest.getString("PROVINCIA"));
                    dest.setCiudad(rsDest.getString("CIUDAD"));
                    m.setDestinatario(dest);
                }


                //UNA VEZ TRAIDOS TODOS LOS DATOS AGREGO A LA LISTA

                lista.add(m);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }

    public ArrayList<Mensaje> traerMensajesRecibidos(int idUsuario) {
        ArrayList<Mensaje> lista = null;
        try {

            String traerMensajes = "select * from MENSAJES where ELIMINADO=FALSE and IDDESTINATARIO=?";
            PreparedStatement st = conn.prepareStatement(traerMensajes);
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<Mensaje>();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS

            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente = rs.getInt("IDREMITENTE");
                int idDestinatario = rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.prepareStatement(traerRemitente);
                statementRemitente.setInt(1, idRemitente);
                ResultSet rsRemitente = statementRemitente.executeQuery();

                if (rsRemitente.next()) {
                    Usuario remitente = new Usuario();
                    remitente.setId(rsRemitente.getInt("IDUSUARIO"));
                    remitente.setNombre(rsRemitente.getString("NOMBRE"));
                    remitente.setApellido(rsRemitente.getString("APELLIDO"));
                    remitente.setPassword(rsRemitente.getString("PASSWORD"));
                    remitente.setEmail(rsRemitente.getString("EMAIL"));
                    remitente.setDireccion(rsRemitente.getString("DIRECCION"));
                    remitente.setTelefono(rsRemitente.getInt("TELEFONO"));
                    remitente.setPais(rsRemitente.getString("PAIS"));
                    remitente.setProvincia(rsRemitente.getString("PROVINCIA"));
                    remitente.setCiudad(rsRemitente.getString("CIUDAD"));
                    m.setRemitente(remitente);
                }

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.prepareStatement(traerRemitente);
                statementDestinatario.setInt(1, idDestinatario);
                ResultSet rsDest = statementDestinatario.executeQuery();

                if (rsDest.next()) {
                    Usuario dest = new Usuario();
                    dest.setId(rsDest.getInt("IDUSUARIO"));
                    dest.setNombre(rsDest.getString("NOMBRE"));
                    dest.setApellido(rsDest.getString("APELLIDO"));
                    dest.setPassword(rsDest.getString("PASSWORD"));
                    dest.setEmail(rsDest.getString("EMAIL"));
                    dest.setDireccion(rsDest.getString("DIRECCION"));
                    dest.setTelefono(rsDest.getInt("TELEFONO"));
                    dest.setPais(rsDest.getString("PAIS"));
                    dest.setProvincia(rsDest.getString("PROVINCIA"));
                    dest.setCiudad(rsDest.getString("CIUDAD"));
                    m.setDestinatario(dest);
                }


                //UNA VEZ TRAIDOS TODOS LOS DATOS AGREGO A LA LISTA

                lista.add(m);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lista;
    }

}
