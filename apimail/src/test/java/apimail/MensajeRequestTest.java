package apimail;

import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Request.MensajeRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fefe on 24/6/2017.
 */
public class MensajeRequestTest {

    MensajeRequest mensaje;

    @Before
    public void setUp()
    {
        mensaje=new MensajeRequest();
        mensaje.setAsunto("prueba asunto");
        mensaje.setBody("probando body");
        mensaje.setDestinatarios(null);
    }


    @Test
    public void testAsunto()
    {
        assertEquals("probando asunto",mensaje.getAsunto(),"prueba asunto");
    }


    @Test
    public void testBody()
    {
        assertEquals("probando body",mensaje.getBody(),"probando body");
    }


    @Test
    public void testDestinatarios()
    {
        assertEquals("probando destinatarios",mensaje.getDestinatarios(),null);
    }

}
