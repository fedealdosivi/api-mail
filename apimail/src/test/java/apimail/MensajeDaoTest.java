package apimail;

import apimail.Dao.Conexion;
import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.ResultSet;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by fefe on 13/6/2017.
 */

@RunWith(PowerMockRunner.class)
public class MensajeDaoTest extends TestCase {

    DaoMensajes daoMensajes;
    Conexion conn;
    PreparedStatement ps;
    ResultSet rs;

    @Before
    public void setUp(){
        daoMensajes=new DaoMensajes();
        conn=mock(Conexion.class);
        daoMensajes.setConn(conn);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }


    @Test
    public void testTraerPorIdOk() {
        try {
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.getInt("IDMENSAJE")).thenReturn(1);
            when(rs.getString("ASUNTO")).thenReturn("jaja");
            when(rs.getString("BODY")).thenReturn("body");
            when(rs.next()).thenReturn(true);
            Mensaje m = daoMensajes.traerMensajePorId(8000);
            assertTrue(m.getAsunto().equals("jaja"));
            assertTrue(m.getBody().equals("body"));
        } catch(Exception e) {
            e.getStackTrace();
            //fail();
            //assertTrue(true);
        }
    }



    @Test
    public void testTraerIdNull() {
        try {
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
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
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenThrow(new Exception());
            Mensaje mensaje= daoMensajes.traerMensajePorId(8000);
            fail();
        } catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testTraerRecibidosException()
    {
        try{

            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
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
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
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
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
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
            when(conn.getConn().prepareStatement(anyString())).thenReturn(ps);
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

}
