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

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        mensaje.setDestinatario(null);
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
        assertEquals("probando remitente",mensaje.getRemitente(),new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
    }

    @Test
    public void testDestinatarios()
    {
        assertEquals("probando destinatarios",mensaje.getDestinatario(),null);
    }

}
