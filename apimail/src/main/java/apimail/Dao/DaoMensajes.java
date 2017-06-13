/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;
import apimail.Model.Mensaje;
import java.util.ArrayList;

import apimail.Model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fefe
 */
@Repository
public class DaoMensajes {

    private Conexion conn;

    /*public DaoMensajes()
    {
        conn = Conexion.getInstancia();
    }*/

    public void cargarMensaje(Mensaje mensaje)
    {
        try {

            String query = "INSERT INTO MENSAJES(IDMENSAJE,IDREMITENTE,ASUNTO,BODY) values (?,?,?,?);";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(query);
            st.setInt(1,mensaje.getId());
            st.setInt(2,mensaje.getRemitente().getId());
            st.setString(3,mensaje.getAsunto());
            st.setString(4,mensaje.getBody());
            st.execute();

            if(mensaje.getDestinatarios().size()>=1)
            {
                for (Usuario u: mensaje.getDestinatarios())
                {
                    String qr="INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO) values(?,?)";
                    PreparedStatement st2 = conn.getConn().prepareStatement(query);
                    st2.setInt(1,mensaje.getId());
                    st2.setInt(2,u.getId());
                    st2.execute();
                }
            }

        }
        catch(Exception e)
        {
            e.getStackTrace();
        }

        finally {
            try{
                conn.desconectar();
            }
            catch(Exception ex)
            {
                ex.getStackTrace();
            }
        }
    }

    public void eliminarMensaje(Mensaje mensaje)
    {
        try{
            String sq = "delete from MENSAJES where IDMENSAJE=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, mensaje.getId());
            st.execute();
        }

        catch (Exception e)
        {
            e.getStackTrace();
        }

        finally {
            try{
                conn.desconectar();
            }
            catch(Exception ex)
            {
                ex.getStackTrace();
            }
        }
    }

    public ArrayList<Mensaje> traerTodos()
    {
        ArrayList<Mensaje> lista=null;
        try{
            //TRAIGO TODOS LOS MENSAJES

            String traerMensajes = "select * from MENSAJES";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(traerMensajes);
            ResultSet rs = st.executeQuery();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS
            while (rs.next())
            {
                Mensaje m=new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.getConn().prepareStatement(traerRemitente);
                statementRemitente.setInt(1,rs.getInt("IDREMITENTE"));
                ResultSet rsRemitente=statementRemitente.executeQuery();

                if(rsRemitente.next())
                {
                    Usuario remitente=new Usuario();
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

                String traerDestinatarios="SELECT U.IDUSUARIO,U.NOMBRE,U.APELLIDO,U.EMAIL,U.PASSWORD,U.DIRECCION,U.TELEFONO,U.PAIS,U.PROVINCIA,U.CIUDAD FROM USUARIOS AS U JOIN DESTINATARIOXMENSAJE AS D WHERE D.IDMENSAJE=? AND D.IDDESTINATARIO=U.IDUSUARIO";
                PreparedStatement stDestinatarios = conn.getConn().prepareStatement(traerDestinatarios);
                stDestinatarios.setInt(1,m.getId());
                ResultSet rsDestinatarios=stDestinatarios.executeQuery();

                while (rsDestinatarios.next())
                {
                    Usuario destinatario=new Usuario();
                    destinatario.setId(rsRemitente.getInt("IDUSUARIO"));
                    destinatario.setNombre(rsRemitente.getString("NOMBRE"));
                    destinatario.setApellido(rsRemitente.getString("APELLIDO"));
                    destinatario.setPassword(rsRemitente.getString("PASSWORD"));
                    destinatario.setEmail(rsRemitente.getString("EMAIL"));
                    destinatario.setDireccion(rsRemitente.getString("DIRECCION"));
                    destinatario.setTelefono(rsRemitente.getInt("TELEFONO"));
                    destinatario.setPais(rsRemitente.getString("PAIS"));
                    destinatario.setProvincia(rsRemitente.getString("PROVINCIA"));
                    destinatario.setCiudad(rsRemitente.getString("CIUDAD"));

                    m.agregarDestinatario(destinatario);
                }

                //UNA VEZ TRAIDOS TODOS LOS DATOS AGREGO A LA LISTA

                lista.add(m);
            }
        }

        catch (Exception e)
        {
            e.getStackTrace();
        }

        finally {
            try{
                conn.desconectar();
            }
            catch(Exception ex)
            {
                ex.getStackTrace();
            }
        }
        return lista;
    }

    public Mensaje traerMensajePorId(int id)
    {
        Mensaje m=null;

        try{
            String sq = "select * from MENSAJES where IDMENSAJE=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                    m=new Mensaje();
                    m.setId(rs.getInt("IDMENSAJE"));
                    m.setAsunto(rs.getString("ASUNTO"));
                    m.setBody(rs.getString("BODY"));

                    String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                    PreparedStatement statementRemitente = conn.getConn().prepareStatement(traerRemitente);
                    statementRemitente.setInt(1,rs.getInt("IDREMITENTE"));
                    ResultSet rsRemitente=statementRemitente.executeQuery();

                    if(rsRemitente.next())
                    {
                        Usuario remitente=new Usuario();
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

                    String traerDestinatarios="SELECT U.IDUSUARIO,U.NOMBRE,U.APELLIDO,U.EMAIL,U.PASSWORD,U.DIRECCION,U.TELEFONO,U.PAIS,U.PROVINCIA,U.CIUDAD FROM USUARIOS AS U JOIN DESTINATARIOXMENSAJE AS D WHERE D.IDMENSAJE=? AND D.IDDESTINATARIO=U.IDUSUARIO";
                    PreparedStatement stDestinatarios = conn.getConn().prepareStatement(traerDestinatarios);
                    stDestinatarios.setInt(1,m.getId());
                    ResultSet rsDestinatarios=stDestinatarios.executeQuery();

                    while (rsDestinatarios.next())
                    {
                        Usuario destinatario=new Usuario();
                        destinatario.setId(rsRemitente.getInt("IDUSUARIO"));
                        destinatario.setNombre(rsRemitente.getString("NOMBRE"));
                        destinatario.setApellido(rsRemitente.getString("APELLIDO"));
                        destinatario.setPassword(rsRemitente.getString("PASSWORD"));
                        destinatario.setEmail(rsRemitente.getString("EMAIL"));
                        destinatario.setDireccion(rsRemitente.getString("DIRECCION"));
                        destinatario.setTelefono(rsRemitente.getInt("TELEFONO"));
                        destinatario.setPais(rsRemitente.getString("PAIS"));
                        destinatario.setProvincia(rsRemitente.getString("PROVINCIA"));
                        destinatario.setCiudad(rsRemitente.getString("CIUDAD"));

                        m.agregarDestinatario(destinatario);
                    }
            }
        }

        catch (Exception e)
        {
            e.getStackTrace();
        }

        finally {
            try{
                conn.desconectar();
            }
            catch(Exception ex)
            {
                ex.getStackTrace();
            }
        }
        return m;
    }
    
}
