package apimail;

import apimail.Model.Mensaje;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


import apimail.Model.Usuario;
import apimail.Services.MensajeService;
import apimail.Dao.DaoMensajes;
import apimail.Model.Mensaje;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;
import java.sql.ResultSet;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Assert.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by fefe on 19/6/2017.
 */
@RunWith(PowerMockRunner.class)
public class MensajeServiceTest extends TestCase{

    MensajeService service;

    DaoMensajes daoMensajes;

    Mensaje mensaje;


    @Before
    public void setUp()
    {
        service=new MensajeService();
        daoMensajes= Mockito.mock(DaoMensajes.class);

        service.setDaoMensajes(daoMensajes);

        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(8000);
        mensaje.setBody("probando body");
        mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        mensaje.setDestinatario(null);

        when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(mensaje);
    }

    @Test
    public void TestTraerMensajeId()
    {
        when(daoMensajes.traerMensajePorId(8000)).thenReturn(mensaje);
        assertNotNull(service.traerPorId(8000));
    }

    @Test
    public void TestTraerMensajeNullId()
    {
        when(daoMensajes.traerMensajePorId(8000)).thenReturn(null);
        assertEquals(null,service.traerPorId(8000));
    }

    @Test
    public void TestTraerMensajeExceptionId()
    {
        try {
            when(daoMensajes.traerMensajePorId(8000)).thenThrow(new Exception());
            service.traerPorId(8000);
            fail();
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void TestTraerMensajesEliminadosOK()
    {
        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        when(daoMensajes.traerMensajesEliminados()).thenReturn(lista);
        assertEquals(lista,service.traerEliminados());
    }


    @Test
    public void TestTraerMensajesEnviadosOK()
    {
        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        when(daoMensajes.traerMensajesEnviados()).thenReturn(lista);
        assertEquals(lista,service.traerEnviados());
    }

    @Test
    public void TestTraerMensajesRecibidosOK()
    {
        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        when(daoMensajes.traerMensajesEliminados()).thenReturn(lista);
        assertEquals(lista,service.traerRecibidos());
    }



}
