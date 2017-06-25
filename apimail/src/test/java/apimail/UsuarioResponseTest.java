package apimail;

import apimail.Request.UsuarioRequest;
import apimail.Response.UsuarioResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fefe on 24/6/2017.
 */
public class UsuarioResponseTest {

    UsuarioResponse usuario1;


    @Before
    public void setup()
    {
        usuario1=new UsuarioResponse();
        usuario1.setDireccion("JBjusto");
        usuario1.setId(1);
        usuario1.setCiudad("Mar del Plata");
        usuario1.setPais("Argentina");
        usuario1.setProvincia("Buenos Aires");
        usuario1.setEmail("hola@hola");
        usuario1.setApellido("AAA");
        usuario1.setNombre("AAA");
        usuario1.setPassword("123456");
        usuario1.setTelefono(123456);
    }


    @Test
    public void testDireccion() {
        assertEquals("probando direccion" , usuario1.getDireccion(), "JBjusto" );
    }


    @Test
    public void testId()
    {
        assertEquals("probando id",usuario1.getId(),1);
    }


    @Test
    public void testApellido()
    {
        assertEquals("probando apellido",usuario1.getApellido(),"AAA");
    }


    @Test
    public void testNombre()
    {
        assertEquals("probando nombre",usuario1.getNombre(),"AAA");
    }



    @Test
    public void testPassword()
    {
        assertEquals("probando password",usuario1.getPassword(),"123456");
    }


    @Test
    public void testTelefono()
    {
        assertEquals("probando telefono",usuario1.getTelefono(),123456);
    }


    @Test
    public void testEmail()
    {
        assertEquals("probando email",usuario1.getEmail(),"hola@hola");
    }


    @Test
    public void testPais()
    {
        assertEquals("probando pais",usuario1.getPais(),"Argentina");
    }


    @Test
    public void testProvincia()
    {
        assertEquals("probando provincia",usuario1.getProvincia(),"Buenos Aires");
    }


    @Test
    public void testCiudad()
    {
        assertEquals("probando Ciudad",usuario1.getCiudad(),"Mar del Plata");
    }

}
