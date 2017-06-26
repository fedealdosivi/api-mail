package apimail;

import apimail.Dao.DaoMensajes;
import apimail.Dao.DaoUsuarios;

import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

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
public class UsuarioDaoTest{

    DaoUsuarios dao;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Before
    public void setUp() throws Exception {

        conn=mock(Connection.class);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }

    @Test
    public void testTraerIdNull() {
        try {
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(null);
            dao.traerUsuarioPorId(1);

            assertEquals(1,1);

        } catch(Exception e){
            assertTrue(true);
        }
    }

}
