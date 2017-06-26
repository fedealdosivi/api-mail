package apimail;

import apimail.Session.Authentication;
import apimail.Session.SessionData;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by fefe on 14/6/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("default")
@WebAppConfiguration
public class UsuarioControlIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SessionData sessionData;

    @Autowired
    Authentication authentication;

    @Before
    public void setup()
    {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();


    }

    @Test
    public void hola()
    {
        assertEquals(1,1);
    }

}
