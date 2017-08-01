package apimail;

/**
 * Created by fefe on 31/7/2017.
 */

import apimail.Model.Usuario;
import apimail.Session.Authentication;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutenticationTes {
    Authentication auth;
    Usuario user;
    DateTime last;

    @Before
    public void setUp() throws Exception {

        auth=new Authentication();
        user=new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola");
        last=new DateTime();
        auth.setLastAction(last);
        auth.setUsuario(user);
    }

    @Test
    public void testUser()
    {
        assertEquals(user,auth.getUsuario());
    }


    @Test
    public void testAction()
    {
        assertEquals(last,auth.getLastAction());
    }




}
