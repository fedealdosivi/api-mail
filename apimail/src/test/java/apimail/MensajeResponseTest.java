package apimail;

import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Response.MensajeResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fefe on 24/6/2017.
 */
public class MensajeResponseTest extends TestCase{

    MensajeResponse mensaje;

    @Before
    public void setUp()
    {
        mensaje=new MensajeResponse();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        mensaje.setNombreRemitente("fede");
        mensaje.setRemitente("fede@fede");
        mensaje.setDestinatarios(null);
        mensaje.setDateTime(null);
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
        assertEquals("probando remitente","fede@fede",mensaje.getRemitente());
    }

    @Test
    public void testNombreRemitente()
    {
        assertEquals("probando remitente","fede",mensaje.getNombreRemitente());
    }

    @Test
    public void testDestinatarios()
    {
        assertEquals("probando destinatarios",mensaje.getDestinatarios(),null);
    }

    @Test
    public void testDateestinatarios()
    {
        assertEquals("probando destinatarios",mensaje.getDateTime(),null);
    }

}
