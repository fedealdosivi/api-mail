package apimail;

import apimail.Dao.DaoUsuarios;
import apimail.Model.Usuario;
import apimail.Services.UserService;
import junit.framework.TestCase;

/**
 * Created by fefe on 19/6/2017.
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.constraints.AssertTrue;
import java.sql.ResultSet;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
public class UsuarioServiceTest extends TestCase {


    UserService service;

    DaoUsuarios dao;

    Usuario usuario;

    ArrayList<Usuario> lista;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new UserService();
        dao = Mockito.mock(DaoUsuarios.class);

        service.setDaoUsuarios(dao);

        usuario = new Usuario();
        usuario.setDireccion("JBjusto");
        usuario.setId(8000);
        usuario.setCiudad("Mar del Plata");
        usuario.setPais("Argentina");
        usuario.setProvincia("Buenos Aires");
        usuario.setEmail("hola@hola");
        usuario.setApellido("AAA");
        usuario.setNombre("AAA");
        usuario.setPassword("123456");
        usuario.setTelefono(123456);

        lista = new ArrayList<Usuario>();
        lista.add(usuario);
    }

    @Test
    public void traerPorId() {
        when(dao.traerUsuarioPorId(8000)).thenReturn(usuario);
        assertNotNull(service.traerPodId(8000));
    }

    @Test
    public void traerPorIdException() {
        try {
            when(dao.traerUsuarioPorId(8000)).thenThrow(new Exception());
            service.traerPodId(8000);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAgregarUsuario() {
        try {
            service.agregarUsuario("hola", "hola", "hola", 1, "hola", "hola", "hola", "hola", "hola");
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testTraerTodos() {
        when(dao.traerTodos()).thenReturn(lista);
        assertEquals(lista, service.traerTodos());
    }

    @Test
    public void testTraerTodosException() {
        try {
            when(dao.traerTodos()).thenThrow(new Exception());
            service.traerTodos();
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminar() {
        try {
            service.eliminarUsuario(1);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testTraerPorNombre() {
        try {
            when(dao.traerUserPorNombre("hola")).thenReturn(usuario);
            assertEquals(usuario, service.traerPorNombre("hola"));
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testTraerPorNombreException() {
        try {
            when(dao.traerUserPorNombre("hola")).thenThrow(new Exception());
            service.traerPorNombre("hola");
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLogin() {
        try {
            when(dao.validarUsuario("hola", "hola")).thenReturn(usuario);
            assertEquals(usuario, service.login("hola", "hola"));
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testLoginException() {
        try {
            when(dao.validarUsuario("hola", "hola")).thenThrow(new Exception());
            service.login("hola", "hola");
            fail();
        } catch (Exception e) {
            assertEquals(null, service.login("hola", "hola"));
        }
    }

}
