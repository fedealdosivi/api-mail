package apimail;

import apimail.Model.Mensaje;
import apimail.Model.Usuario;
//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by fefe on 13/6/2017.
 */
public class MensajeTest extends TestCase {

    Mensaje mensaje;
    Mensaje mensaje1;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        mensaje.setRemitente("pepe");
        mensaje.setDestinatarios(null);


        mensaje1=new Mensaje();
        mensaje1.setAsunto("prueba asunto");
        mensaje1.setId(1);
        mensaje1.setBody("probando body");
        mensaje1.setRemitente("pepe");
        mensaje1.setDestinatarios(null);
    }

    @Test
    public void testAsunto()
    {
        assertEquals("probando asunto",mensaje.getAsunto(),"prueba asunto");
    }


    @Test
    public void testId()
    {
        assertEquals("probando id",mensaje.getId(),1);
    }


    @Test
    public void testBody()
    {
        assertEquals("probando body",mensaje.getBody(),"probando body");
    }


    @Test
    public void testRemitente()
    {
        assertEquals("probando remitente","pepe",mensaje.getRemitente());
    }

    @Test
    public void testDestinatarios()
    {
        assertEquals("probando destinatarios",mensaje.getDestinatarios(),null);
    }

    @Test
    public void testEquals()
    {
        assertTrue(mensaje.equals(mensaje1));
    }

    @Test
    public void testToString()
    {
        assertEquals(mensaje.toString(),mensaje1.toString());
    }

    @Test
    public void testHashCode()
    {
        assertEquals(mensaje.hashCode(),mensaje1.hashCode());
    }
}
