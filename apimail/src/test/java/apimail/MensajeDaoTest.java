package apimail;

import apimail.Dao.Conexion;
import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Session.Authentication;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.groups.ConvertGroup;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by fefe on 13/6/2017.
 */

public class MensajeDaoTest extends TestCase {

    DaoMensajes daoMensajes;
    Connection conn;
    PreparedStatement ps;
    CallableStatement st;
    ResultSet rs;
    Mensaje mensaje;
    Authentication authentication;

    @Before
    public void setUp(){

        authentication=new Authentication();
        authentication.setUsuario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));

        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        mensaje.setNombreRemitente("FEDE");
        mensaje.setRemitente("fede@fede");
        mensaje.addDestinatario("admin@admin");

        conn=mock(Connection.class);
        daoMensajes=new DaoMensajes(conn);
        daoMensajes.setAuthentication(authentication);

        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
        st= mock(CallableStatement.class);
    }


    @Test
    public void testTraerPorIdOk() {
        try {
            when(conn.prepareCall("CALL getMessageById(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);

            when(rs.getInt("ME.IDMESSAGE")).thenReturn(1);
            when(rs.getString("ME.SUBJECT")).thenReturn("prueba asunto");
            when(rs.getString("ME.BODY")).thenReturn("probando body");
            when(rs.getString("SENDER")).thenReturn("fede@fede");
            when(rs.getTimestamp("ME.TS")).thenReturn(null);
            when(rs.getString("NAME")).thenReturn("FEDE");
            when(rs.getString("RECIPIENT")).thenReturn("admin@admin");

            assertNotNull(daoMensajes.traerMensajePorId(1));

        } catch(Exception e) {
            fail();
        }
    }



    @Test
    public void testTraerIdNull() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(null);
            Mensaje m = daoMensajes.traerMensajePorId(8000);
            assertEquals(m, daoMensajes.traerMensajePorId(8000));

        } catch(Exception e){
            e.getStackTrace();
            //assertTrue(true);
        }
    }


    @Test
    public void testTraerPorIdException() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenThrow(new Exception());
            Mensaje mensaje= daoMensajes.traerMensajePorId(8000);
            fail();
        } catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testTraerRecibidosOK()
    {
        try{
            when(conn.prepareStatement("CALL getInbox(?)")).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

            when(rs.getInt("ME.IDMESSAGE")).thenReturn(1);
            when(rs.getString("ME.SUBJECT")).thenReturn("prueba asunto");
            when(rs.getString("ME.BODY")).thenReturn("probando body");
            when(rs.getString("SENDER")).thenReturn("fede@fede");
            when(rs.getTimestamp("ME.TS")).thenReturn(null);
            when(rs.getString("NAME")).thenReturn("FEDE");
            when(rs.getString("RECIPIENT")).thenReturn("admin@admin");

            assertNotNull(daoMensajes.traerMensajesRecibidos());
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testTraerRecibidosException()
    {
        try{

            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenThrow(new Exception());
            ArrayList<Mensaje> lista= daoMensajes.traerMensajesRecibidos();
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testTraerRecibidosNull()
    {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(null);
            ArrayList<Mensaje> lista = daoMensajes.traerMensajesRecibidos();
            assertEquals(lista, daoMensajes.traerMensajesRecibidos());
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }
    }

    @Test
    public void testTraerEliminadosException()
    {
        try{
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenThrow(new Exception());
            ArrayList<Mensaje> lista= daoMensajes.traerMensajesEliminados();
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testTraerEnviadosException()
    {
        try{
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenThrow(new Exception());
            ArrayList<Mensaje> lista= daoMensajes.traerMensajesEnviados();
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarMensaje()
    {
        try {
            daoMensajes.eliminarMensaje(1);
            assertTrue(true);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testEliminarMensajeException()
    {
        try{
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenThrow(new Exception());
            daoMensajes.eliminarMensaje(1);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testCargarMensaje()
    {
        try {
            when(conn.prepareStatement("INSERT INTO MESSAGES(IDSENDER,SUBJECT,BODY) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);
            when(ps.getGeneratedKeys()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            daoMensajes.cargarMensaje(mensaje);
            assertTrue(true);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testCargarMensajeException()
    {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenThrow(new Exception());
            daoMensajes.cargarMensaje(mensaje);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testCambiarEliminado()
    {
        try {
            daoMensajes.cambiarEliminado(1);
            assertTrue(true);
        }

        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testCambiarEliminadoException()
    {
        try {
            when(conn.prepareCall("CALL setTrash(?)")).thenThrow(new Exception());
            daoMensajes.cambiarEliminado(1);
            fail();
        }

        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testCambiarMuchosEliminados()
    {
        try{
            when(conn.prepareCall("CALL setTrash(?)")).thenThrow(new Exception());
            List<Integer> lista=new ArrayList<Integer>();
            lista.add(1);
            lista.add(2);
            daoMensajes.cambiarEliminadoMuchos(lista);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testCambiarMuchosEliminadosException()
    {
        try{
            List<Integer> lista=new ArrayList<Integer>();
            lista.add(1);
            lista.add(2);
            daoMensajes.cambiarEliminadoMuchos(lista);
            assertTrue(true);
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testTraerTrash()
    {
        try{
            when(conn.prepareStatement("CALL getTrash(?)")).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

            when(rs.getInt("ME.IDMESSAGE")).thenReturn(1);
            when(rs.getString("ME.SUBJECT")).thenReturn("hola");
            when(rs.getString("ME.BODY")).thenReturn("hola");
            when(rs.getString("SENDER")).thenReturn("hola");
            when(rs.getString("NAME")).thenReturn("hola");
            when(rs.getTimestamp("ME.TS")).thenReturn(null);
            when(rs.getString("RECIPIENT")).thenReturn("hola");

            assertNotNull(daoMensajes.traerMensajesEliminados());
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testTraerEnviadosOK()
    {
        try{
            when(conn.prepareCall("CALL getSent(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);

            when(rs.getInt("ME.IDMESSAGE")).thenReturn(1);
            when(rs.getString("ME.SUBJECT")).thenReturn("hola");
            when(rs.getString("ME.BODY")).thenReturn("hola");
            when(rs.getString("SENDER")).thenReturn("hola");
            when(rs.getString("NAME")).thenReturn("hola");
            when(rs.getTimestamp("ME.TS")).thenReturn(null);
            when(rs.getString("RECIPIENT")).thenReturn("hola");

            assertNotNull(daoMensajes.traerMensajesEnviados());
        }
        catch (Exception e)
        {
            fail();
        }
    }



}
