package apimail;

import apimail.Converter.MensajeConverter;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Response.MensajeResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fefe on 21/6/2017.
 */
public class MensajeConverterTest extends TestCase {
    Mensaje mensaje;
    MensajeConverter converter;
    MensajeConverter converter2;
    MensajeResponse wrapper;

    @Before
    public void setup()
    {
        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        mensaje.setDestinatario(new Usuario());

    }

    @Test
    public void testConvert()
    {
        assertEquals(converter.convert(mensaje),converter2.convert(mensaje));
    }

}
