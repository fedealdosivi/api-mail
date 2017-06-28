package apimail;

import apimail.Control.ControlUsuarios;
import apimail.Converter.UsuarioConverter;
import apimail.Model.Usuario;
import apimail.Request.UsuarioRequest;
import apimail.Services.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by fefe on 27/6/2017.
 */
@RunWith(PowerMockRunner.class)
public class ControlUsuarioTest extends TestCase {

    UserService service;
    Usuario usuario;
    ControlUsuarios controladora;
    ArrayList<Usuario> lista;
    UsuarioConverter converter;

    UsuarioRequest userRequest;

    @Before
    public void setUp()
    {
        usuario= new Usuario("fede","fede","fede","fede","fede",123456,"fede","fede","fede");
        userRequest=new UsuarioRequest();
        userRequest.setId(0);
        userRequest.setTelefono(0);
        userRequest.setNombre("fede");
        userRequest.setApellido("fede");
        userRequest.setPais("fede");
        userRequest.setProvincia("fede");
        userRequest.setCiudad("fede");
        userRequest.setEmail("fede");
        userRequest.setDireccion("hola");
        userRequest.setPassword("hola");


        lista=new ArrayList<Usuario>();
        lista.add(usuario);
        service= Mockito.mock(UserService.class);

        converter= Mockito.mock(UsuarioConverter.class);
        controladora=new ControlUsuarios();

        controladora.setUserService(service);

    }

    @Test
    public void testTraerUsuarios()
    {
        assertEquals(1,1);
    }

    @Test
    public void testCargarUsuario()
    {
        assertEquals(HttpStatus.CREATED,controladora.addUsuario(userRequest).getStatusCode());
    }

}
