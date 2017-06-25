package apimail;

import apimail.Converter.UsuarioConverter;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Response.UsuarioResponse;
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
public class UsuarioConverterTest extends TestCase {

    Usuario up;

    private MockMvc mockMvc;

    @Autowired
    UsuarioConverter converter;

    UsuarioResponse wrapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup()
    {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        up=new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola");
        wrapper=converter.convert(up);
    }

    @Test
    public void testConvert()
    {
        assertFalse(wrapper.equals(null));
    }

}
