package apimail;

import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Created by fefe on 19/6/2017.
 */
public class MensajeServiceTest {

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
        //Uso de Mockito
        try {
            when(conn.prepareStatement("select c.id_cliente, c.descripcion, c.cuit, tc.id_tipo_cliente, tc.descripcion as descrip_tipo from clientes c join tipo_cliente tc on c.id_tipo_cliente = tc.id_tipo_cliente where c.id_cliente = ?")).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.getInt("IDMENSAJE")).thenReturn(1);
            when(rs.getString("ASUNTO")).thenReturn("jaja");
            when(rs.getString("BODY")).thenReturn("body");
            when(rs.next()).thenReturn(true);
            Mensaje m = daoMensajes.traerMensajePorId(1);
            assertTrue(m.getAsunto().equals("jaja"));
            assertTrue(m.getAsunto().equals("body"));
            assertEquals(1,m.getId());
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

}
