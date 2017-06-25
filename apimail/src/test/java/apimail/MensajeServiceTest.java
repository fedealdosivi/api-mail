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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;
import java.sql.ResultSet;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Assert.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by fefe on 19/6/2017.
 */
@ActiveProfiles("default")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class MensajeServiceTest {

    @Autowired
    MensajeService service;

    @Autowired
    DaoMensajes daoMensajes;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    Mensaje mensaje;


    @Before
    public void setup()
    {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        /*
        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(8000);
        mensaje.setBody("probando body");
        mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        mensaje.setDestinatario(null);

        daoMensajes.cargarMensaje(mensaje);*/
    }

    @After
    public void after()
    {
        //daoMensajes.eliminarMensaje(mensaje.getId());
    }

    @Test
    public void TestTraerMensajeId()
    {
        //when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(new Mensaje());
        assertNotNull(service.traerPorId(1));
    }

    @Test
    public void TestTraerMensajeNullId()
    {
        //when(daoMensajes.traerMensajePorId(anyInt())).thenReturn(null);
        assertNotNull(service.traerPorId(1));
    }

    @Test
    public void TestTraerMensajeExceptionId()
    {
        //when(daoMensajes.traerMensajePorId(anyInt())).thenThrow(new Exception());
        assertNotEquals(new Exception(),service.traerPorId(1));
    }

    @Test
    public void TestTraerMensajesEliminadosOK()
    {
        //when(daoMensajes.traerMensajesEliminados(anyInt())).thenReturn(new Mensaje());
        assertNotNull(service.traerEliminados(1));
    }


    @Test
    public void TestTraerMensajesEnviadosOK()
    {
        //when(daoMensajes.traerMensajesEnviados(anyInt())).thenReturn(new Mensaje());
        assertNotNull(service.traerEnviados(1));
    }

    @Test
    public void TestTraerMensajesRecibidosOK()
    {
        //when(daoMensajes.traerMensajesEliminados(anyInt())).thenReturn(new Mensaje());
        assertNotNull(service.traerRecibidos(1));
    }



}
