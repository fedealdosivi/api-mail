package apimail;


import apimail.Control.ControlSession;
import apimail.Control.ControlUsuarios;
import apimail.Converter.MensajeConverter;
import apimail.Converter.UsuarioConverter;
import apimail.Dao.DaoUsuarios;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Response.MensajeResponse;
import apimail.Session.SessionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by fefe on 21/6/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("default")
@WebAppConfiguration
public class SessionTestIT {

    private MockMvc mockMvc;

    @Autowired
    private ControlSession controlSession;

    @Autowired
    private ControlUsuarios controlUsuarios;

    @Autowired
    private SessionData sessionData;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DaoUsuarios daoUsuarios;

    @Autowired
    private MensajeConverter converterMensaje;

    @Autowired
    private UsuarioConverter converterUsuario;

    private Usuario user;

    private Mensaje mensaje;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        user = new Usuario("fede","fede","fede","fede","fede",123456,"fede","fede","fede");
        daoUsuarios.cargarUsuario(user);
    }

    @After
    public void after() throws Exception {
        daoUsuarios.eliminarUsuario(user.getId());
    }

    @Test
    public void loginSuccess() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                );
                //.andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
