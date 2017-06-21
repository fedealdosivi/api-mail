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
    Usuario usuario1;

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


        usuario1=new Usuario();
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

    @Test
    public void testEquals()
    {
        assertTrue(usuario.equals(usuario1));
    }

    @Test
    public void testTostring()
    {
        assertEquals(usuario.toString(),usuario1.toString());
    }

    @Test
    public void testHashcode()
    {
        assertEquals(usuario.hashCode(),usuario1.hashCode());
    }
}
