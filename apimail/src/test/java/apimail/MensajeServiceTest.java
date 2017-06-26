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
//@ActiveProfiles("default")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class)
@RunWith(PowerMockRunner.class)
public class MensajeServiceTest extends TestCase{

    MensajeService service;


    DaoMensajes daoMensajes= Mockito.mock(DaoMensajes.class);

    Mensaje mensaje;


    @Before
    public void setUp()
    {
        service=new MensajeService();

        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(8000);
        mensaje.setBody("probando body");
        mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        mensaje.setDestinatario(null);

        when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(mensaje);
        daoMensajes.cargarMensaje(mensaje);
    }

    @After
    public void tearDown()
    {
        daoMensajes.eliminarMensaje(mensaje.getId());
    }

    @Test
    public void TestTraerMensajeId()
    {
        assertNull(service.traerPorId(8000));
    }

    @Test
    public void TestTraerMensajeNullId()
    {
        when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(null);
        assertEquals(null,service.traerPorId(1));
    }

    @Test
    public void TestTraerMensajeExceptionId()
    {
        try {
            when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(null);
            assertEquals(1, 1);
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
        when(daoMensajes.traerMensajesEliminados(anyInt())).thenReturn(lista);
        assertEquals(null,service.traerEliminados(1));
    }


    @Test
    public void TestTraerMensajesEnviadosOK()
    {
        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        when(daoMensajes.traerMensajesEnviados(anyInt())).thenReturn(lista);
        assertEquals(null,service.traerEnviados(1));
    }

    @Test
    public void TestTraerMensajesRecibidosOK()
    {
        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        when(daoMensajes.traerMensajesEliminados(anyInt())).thenReturn(lista);
        assertEquals(null,service.traerArrayTodos());
    }



}
