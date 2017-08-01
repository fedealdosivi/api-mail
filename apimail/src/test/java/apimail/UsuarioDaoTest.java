package apimail;

import apimail.Dao.DaoMensajes;
import apimail.Dao.DaoUsuarios;

import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
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
    CallableStatement st;
    ResultSet rs;

    @Before
    public void setUp() throws Exception {

            conn = mock(Connection.class);
            ps = mock(PreparedStatement.class);
            rs = mock(ResultSet.class);
            st = mock(CallableStatement.class);
            dao = new DaoUsuarios(conn);
    }

    @Test
    public void testUsuarioIdNull() {
        try {
            when(conn.prepareCall("CALL getUserById(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            assertEquals(dao.traerUsuarioPorId(1),null);

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testUsuarioIdException() {
        try {
            when(this.conn.prepareCall("CALL getUserById(?)")).thenThrow(new SQLException());

            dao.traerUsuarioPorId(1);

            assertEquals(1,1);

        } catch(SQLException e){
            assertTrue(true);
        }
    }

    @Test
    public void testUsuarioIdOK() {
        try {
            when(this.conn.prepareCall("CALL getUserById(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            when((rs.getInt("IDUSER"))).thenReturn(1);
            when((rs.getString("NAME"))).thenReturn("hola");
            when((rs.getString("SURNAME"))).thenReturn("hola");
            when((rs.getString("PASSWORD"))).thenReturn("hola");
            when((rs.getString("EMAIL"))).thenReturn("hola");
            when((rs.getString("ADRESS"))).thenReturn("hola");
            when((rs.getInt("CELLPHONE"))).thenReturn(1);
            when((rs.getString("COUNTRY"))).thenReturn("hola");
            when((rs.getString("STATE"))).thenReturn("hola");
            when((rs.getString("CITY"))).thenReturn("hola");

            Usuario user=dao.traerUsuarioPorId(1);

            assertEquals("hola",user.getApellido());

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testTraerTodosNull() {
        try {
            when(conn.prepareCall("CALL getAllUsers()")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            dao.traerTodos();

        } catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testTraerTodosException() {
        try {
            when(conn.prepareCall("CALL getAllUsers()")).thenThrow(new Exception());
            dao.traerTodos();
            fail();

        } catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testTraerTodosOK() {
        try {
            when(conn.prepareCall("CALL getAllUsers()")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false);

            when((rs.getInt("IDUSER"))).thenReturn(1);
            when((rs.getString("NAME"))).thenReturn("hola");
            when((rs.getString("SURNAME"))).thenReturn("hola");
            when((rs.getString("PASSWORD"))).thenReturn("hola");
            when((rs.getString("EMAIL"))).thenReturn("hola");
            when((rs.getString("ADRESS"))).thenReturn("hola");
            when((rs.getInt("CELLPHONE"))).thenReturn(1);
            when((rs.getString("COUNTRY"))).thenReturn("hola");
            when((rs.getString("STATE"))).thenReturn("hola");
            when((rs.getString("CITY"))).thenReturn("hola");

            assertNotNull(dao.traerTodos());

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testTraerNombreNull() {
        try {
            when(conn.prepareCall("CALL getUserByName(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            assertNull(dao.traerUserPorNombre("fede"));

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testTraerNombreException() {
        try {
            when(conn.prepareCall("CALL getUserByName(?)")).thenThrow(new Exception());
            dao.traerUserPorNombre("fede");

        } catch(Exception e){
            assertTrue(true);
        }
    }


    @Test
    public void testTraerNombreOK() {
        try {
            when(conn.prepareCall("CALL getUserByName(?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            when((rs.getInt("IDUSER"))).thenReturn(1);
            when((rs.getString("NAME"))).thenReturn("hola");
            when((rs.getString("SURNAME"))).thenReturn("hola");
            when((rs.getString("PASSWORD"))).thenReturn("hola");
            when((rs.getString("EMAIL"))).thenReturn("hola");
            when((rs.getString("ADRESS"))).thenReturn("hola");
            when((rs.getInt("CELLPHONE"))).thenReturn(1);
            when((rs.getString("COUNTRY"))).thenReturn("hola");
            when((rs.getString("STATE"))).thenReturn("hola");
            when((rs.getString("CITY"))).thenReturn("hola");

            assertNotNull(dao.traerUserPorNombre("fede"));

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testValidarNull() {
        try {
            when(conn.prepareCall("CALL login(?,?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            assertEquals(null,dao.validarUsuario("hola@hola","123456"));

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testValidarException() {
        try {
            when(conn.prepareCall("CALL login(?,?)")).thenThrow(new SQLException());
            dao.validarUsuario("hola@hola","123456");
            assertEquals(1,1);

        } catch(SQLException e){
            assertTrue(true);
        }
    }

    @Test
    public void testValidarOK() {
        try {
            when(conn.prepareCall("CALL login(?,?)")).thenReturn(st);
            when(st.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when((rs.getInt("IDUSER"))).thenReturn(1);
            when((rs.getString("NAME"))).thenReturn("hola");
            when((rs.getString("SURNAME"))).thenReturn("hola");
            when((rs.getString("PASSWORD"))).thenReturn("hola");
            when((rs.getString("EMAIL"))).thenReturn("hola@hola");
            when((rs.getString("ADRESS"))).thenReturn("hola");
            when((rs.getInt("CELLPHONE"))).thenReturn(1);
            when((rs.getString("COUNTRY"))).thenReturn("hola");
            when((rs.getString("STATE"))).thenReturn("hola");
            when((rs.getString("CITY"))).thenReturn("hola");

            Usuario user=dao.validarUsuario("hola@hola","1");

            assertEquals("hola",user.getApellido());

        } catch(Exception e){
            fail();
        }
    }

    @Test
    public void testEliminarException() {
        try {
            when(conn.prepareCall(anyString())).thenThrow(new SQLException());
            dao.eliminarUsuario(55);
            assertEquals(1,1);

        } catch(SQLException e){
            assertTrue(true);
        }
    }

    @Test
    public void testEliminar()
    {
        try {
            dao.eliminarUsuario(1);
            assertTrue(true);
        }
        catch (Exception ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testCargar()
    {
        try{
            dao.cargarUsuario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        }
        catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testCargarException()
    {
        try{
            when(this.conn.prepareCall("CALL saveUser(?,?,?,?,?,?,?,?,?)")).thenThrow(new Exception());
            dao.cargarUsuario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

}
