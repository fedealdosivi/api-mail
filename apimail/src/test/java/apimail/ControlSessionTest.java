package apimail;

import apimail.Control.ControlSession;
import apimail.Model.Usuario;
import apimail.Response.LoginResponse;
import apimail.Services.UserService;
import apimail.Session.SessionData;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by fefe on 28/6/2017.
 */

@RunWith(PowerMockRunner.class)
public class ControlSessionTest extends TestCase{

    ControlSession controladora;
    UserService service;

    LoginResponse wrapper;
    Usuario usuario;

    SessionData sessionData;

    @Before
    public void setUp() {

        usuario = new Usuario("fede", "fede", "fede", "fede", "fede", 123456, "fede", "fede", "fede");

        wrapper=new LoginResponse();
        wrapper.setSessionId("2e77610b-ad66-4bfb-bc2d-7982081381b2");

        sessionData =new SessionData();
        //sessionData.addSession(usuario);

        service= Mockito.mock(UserService.class);
        controladora=new ControlSession();
        controladora.setUserService(service);
        controladora.setSessionData(sessionData);
    }

    @Test
    public void testGetSession()
    {
        assertEquals("2e77610b-ad66-4bfb-bc2d-7982081381b2",wrapper.getSessionId());
    }

    @Test
    public void loginTest()
    {
        when(service.login(anyString(),anyString())).thenReturn(usuario);
        assertEquals(HttpStatus.OK,controladora.getById("fede","fede").getStatusCode());
        assertEquals(HttpStatus.ACCEPTED,controladora.logout("2e77610b-ad66-4bfb-bc2d-7982081381b2").getStatusCode());
    }


    @Test
    public void loginTestError()
    {
        when(service.login(anyString(),anyString())).thenReturn(null);
        assertEquals(HttpStatus.UNAUTHORIZED,controladora.getById("fede","fede").getStatusCode());
        //assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,controladora.logout("2e77610b-ad66-4bfb-bc2d-7982081381b2").getStatusCode());
    }





}