package apimail;

import apimail.Control.ControlUsuarios;
import apimail.Converter.UsuarioConverter;
import apimail.Model.Usuario;
import apimail.Request.UsuarioRequest;
import apimail.Response.UsuarioResponse;
import apimail.Services.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.awt.HeadlessToolkit;

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

    UsuarioResponse wrapper;
    UsuarioRequest userRequest;

    @Before
    public void setUp() {
        usuario = new Usuario("fede", "fede", "fede", "fede", "fede", 123456, "fede", "fede", "fede");
        userRequest = new UsuarioRequest();
        userRequest.setTelefono(0);
        userRequest.setNombre("fede");
        userRequest.setApellido("fede");
        userRequest.setPais("fede");
        userRequest.setProvincia("fede");
        userRequest.setCiudad("fede");
        userRequest.setEmail("fede");
        userRequest.setDireccion("hola");
        userRequest.setPassword("hola");

        wrapper = new UsuarioResponse();
        wrapper.setId(0);
        wrapper.setTelefono(0);
        wrapper.setNombre("fede");
        wrapper.setApellido("fede");
        wrapper.setPais("fede");
        wrapper.setProvincia("fede");
        wrapper.setCiudad("fede");
        wrapper.setEmail("fede");
        wrapper.setDireccion("hola");
        wrapper.setPassword("hola");

        lista = new ArrayList<Usuario>();
        lista.add(usuario);
        service = Mockito.mock(UserService.class);

        converter = Mockito.mock(UsuarioConverter.class);
        controladora = new ControlUsuarios();

        controladora.setUserService(service);
        controladora.setConverter(converter);

        when(converter.convert(any())).thenReturn(wrapper);

    }

    @Test
    public void testTraerUsuarios() {
        when(service.traerTodos()).thenReturn(lista);
        assertEquals(HttpStatus.OK, controladora.getAll().getStatusCode());
    }

    @Test
    public void testTraerUsuariosNull()
    {
        when(service.traerTodos()).thenReturn(new ArrayList<Usuario>());
        assertEquals(HttpStatus.NOT_FOUND, controladora.getAll().getStatusCode());
    }

    @Test
    public void testCargarUsuario() {
        assertEquals(HttpStatus.CREATED, controladora.addUsuario(userRequest).getStatusCode());
    }

    @Test
    public void testTraerPorId() {
        when(service.traerPodId(anyInt())).thenReturn(usuario);
        assertEquals(HttpStatus.OK, controladora.getById(1).getStatusCode());
    }


    @Test
    public void testTraerPorIdNull() {
        when(service.traerPodId(anyInt())).thenReturn(null);
        assertEquals(HttpStatus.NOT_FOUND, controladora.getById(1).getStatusCode());
    }

    @Test
    public void testTraerPorNombre() {
        when(service.traerPorNombre(anyString())).thenReturn(usuario);
        assertEquals(HttpStatus.OK,controladora.traerUserPorNombre("fede").getStatusCode());
    }

    @Test
    public void testTraerPorNombreNull() {
        when(service.traerPorNombre(anyString())).thenReturn(null);
        assertEquals(HttpStatus.NOT_FOUND,controladora.traerUserPorNombre("fede").getStatusCode());
    }

    @Test
    public void testEliminar()
    {
        assertEquals(HttpStatus.ACCEPTED,controladora.removeUsuario(800).getStatusCode());
    }


    @Test
    public void testCargarException() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,controladora.addUsuario(null).getStatusCode());
    }

}
