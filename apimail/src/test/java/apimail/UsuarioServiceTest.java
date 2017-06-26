package apimail;

import apimail.Dao.DaoUsuarios;
import apimail.Model.Usuario;
import apimail.Services.UserService;
import junit.framework.TestCase;

/**
 * Created by fefe on 19/6/2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.constraints.AssertTrue;
import java.sql.ResultSet;

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

    DaoUsuarios dao = Mockito.mock(DaoUsuarios.class);

    Usuario usuario;

    @Before
    public void setUp() throws Exception{
        super.setUp();
        service = new UserService();

        usuario=new Usuario();
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

        dao.cargarUsuario(usuario);
    }

    @After
    public void tearDown()
    {
        dao.eliminarUsuario(usuario.getId());
    }

    @Test
    public void traerPorIdnull()
    {
        when(dao.traerUsuarioPorId(8000)).thenReturn(null);
        assertNull(service.traerPodId(8000));
    }

}
