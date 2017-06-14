package apimail;

import apimail.Model.Usuario;
//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fefe on 13/6/2017.
 */
public class UsuarioTest extends TestCase{

    Usuario usuario;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        usuario=new Usuario();
        usuario.setDireccion("JBjusto");
        usuario.setId(1);
        usuario.setCiudad("Mar del Plata");
        usuario.setPais("Argentina");
        usuario.setProvincia("Buenos Aires");
        usuario.setEmail("hola@hola");
        usuario.setApellido("AAA");
        usuario.setNombre("AAA");
        usuario.setPassword("123456");
        usuario.setTelefono(123456);
    }


    @Test
    public void testDireccion() {
        assertEquals("probando direccion" , usuario.getDireccion(), "JBjusto" );
    }


    @Test
    public void testId()
    {
        assertEquals("probando id",usuario.getId(),1);
    }


    @Test
    public void testApellido()
    {
        assertEquals("probando apellido",usuario.getApellido(),"AAA");
    }


    @Test
    public void testNombre()
    {
        assertEquals("probando nombre",usuario.getNombre(),"AAA");
    }



    @Test
    public void testPassword()
    {
        assertEquals("probando password",usuario.getPassword(),"123456");
    }


    @Test
    public void testTelefono()
    {
        assertEquals("probando telefono",usuario.getTelefono(),123456);
    }


    @Test
    public void testEmail()
    {
        assertEquals("probando email",usuario.getEmail(),"hola@hola");
    }


    @Test
    public void testPais()
    {
        assertEquals("probando pais",usuario.getPais(),"Argentina");
    }


    @Test
    public void testProvincia()
    {
        assertEquals("probando provincia",usuario.getProvincia(),"Buenos Aires");
    }


    @Test
    public void testCiudad()
    {
        assertEquals("probando Ciudad",usuario.getCiudad(),"Mar del Plata");
    }
}
