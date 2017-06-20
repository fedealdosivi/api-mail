package apimail;

import apimail.Model.Mensaje;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


import apimail.Model.Usuario;
import apimail.Services.MensajeService;
import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.sql.ResultSet;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by fefe on 19/6/2017.
 */
public class MensajeServiceTest {

    MensajeService service;
    DaoMensajes daoMensajes;

    @Before
    public void setup()
    {
        service=mock(MensajeService.class);
        daoMensajes=mock(DaoMensajes.class);
    }

    @Test
    public void TestAgregarServiceTrue()
    {
        Usuario user1=new Usuario("fede","fede","fede","fede","fede",123,"fede","fede","fede");
        when(service.agregarMensaje(1,"asd","asd",user1,user1)).thenReturn(true);
        assertTrue(service.agregarMensaje(1,"asd","asd",user1,user1));
    }

}
