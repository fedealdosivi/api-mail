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

    public DaoMensajes()
    {
        conn = Conexion.getInstancia();
    }

    public void cargarMensaje(Mensaje mensaje)
    {
        try {

            String query = "INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (?,?,?,?)";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(query);
            st.setInt(1,mensaje.getRemitente().getId());
            st.setInt(1,mensaje.getDestinatario().getId());
            st.setString(3,mensaje.getAsunto());
            st.setString(4,mensaje.getBody());
            st.execute();

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

    public void eliminarMensaje(int idMensaje)
    {
        try{
            String sq = "delete from MENSAJES where IDMENSAJE=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, idMensaje);
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
            lista=new ArrayList<Mensaje>();

            //POR CADA MENSAJE QUE TRAJE, BUSCO SU REMITENTE Y SUS DESTINATARIOS

            while (rs.next())
            {
                Mensaje m=new Mensaje();
                m.setId(rs.getInt("IDMENSAJE"));
                m.setAsunto(rs.getString("ASUNTO"));
                m.setBody(rs.getString("BODY"));
                int idRemitente=rs.getInt("IDREMITENTE");
                int idDestinatario=rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.getConn().prepareStatement(traerRemitente);
                statementRemitente.setInt(1,idRemitente);
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

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.getConn().prepareStatement(traerRemitente);
                statementDestinatario.setInt(1,idDestinatario);
                ResultSet rsDest=statementDestinatario.executeQuery();

                if(rsDest.next())
                {
                    Usuario dest=new Usuario();
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
                int idRemitente=rs.getInt("IDREMITENTE");
                int idDestinatario=rs.getInt("IDDESTINATARIO");


                //TRAIGO EL REMITENTE CON LA ID DEL RESULT SET.

                String traerRemitente = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementRemitente = conn.getConn().prepareStatement(traerRemitente);
                statementRemitente.setInt(1,idRemitente);
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

                //TRAIGO EL DESTINATARIO CON EL ID DEL RESULT SET

                String traerDestinatario = "select * from USUARIOS where IDUSUARIO=?";
                PreparedStatement statementDestinatario = conn.getConn().prepareStatement(traerRemitente);
                statementDestinatario.setInt(1,idDestinatario);
                ResultSet rsDest=statementDestinatario.executeQuery();

                if(rsDest.next())
                {
                    Usuario dest=new Usuario();
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
