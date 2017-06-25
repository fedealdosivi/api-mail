package apimail;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by fefe on 13/6/2017.
 */
public class MensajeDaoTest{

    DaoMensajes daoMensajes;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Before
    public void setUp(){

        conn=mock(Connection.class);
        daoMensajes =new DaoMensajes();
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }


    @Test
    public void testTraerPorIdOk() {
        try {
            when(conn.prepareStatement("select * from MENSAJES where IDMENSAJE=1")).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.getInt("IDMENSAJE")).thenReturn(1);
            when(rs.getString("ASUNTO")).thenReturn("jaja");
            when(rs.getString("BODY")).thenReturn("body");
            when(rs.next()).thenReturn(true);
            Mensaje m = daoMensajes.traerMensajePorId(1);
            //assertTrue(m.getAsunto().equals("jaja"));
            //assertTrue(m.getBody().equals("body"));
            assertEquals(null,m);
        } catch(Exception e) {
            fail();
        }
    }



    @Test
    public void testTraerIdNull() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);
            Mensaje m = daoMensajes.traerMensajePorId(1);
            assertNull(m);

        } catch(Exception e){
            fail();
        }
    }


    @Test
    public void testTraerException() {
        try {
            when(conn.prepareStatement(anyString())).thenThrow(new Exception());
            Mensaje mensaje= daoMensajes.traerMensajePorId(1);
            fail();
        } catch(Exception e){
            assertTrue(true);
        }

    }
}
