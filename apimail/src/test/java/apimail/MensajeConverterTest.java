package apimail;

import apimail.Converter.MensajeConverter;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Request.MensajeRequest;
import apimail.Response.MensajeResponse;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by fefe on 21/6/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("default")
@WebAppConfiguration
public class MensajeConverterTest extends TestCase {

    private MockMvc mockMvc;

    Mensaje mensaje;

    @Autowired
    MensajeConverter converter;

    MensajeResponse wrapper;

    MensajeRequest request;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup()
    {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        //mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        //mensaje.setDestinatario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));

        wrapper=converter.convert(mensaje);
    }

    @Test
    public void testConvertWrapper()
    {
        assertFalse(wrapper.equals(null));
    }
}
