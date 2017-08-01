package apimail;

import apimail.Control.ControlMensajes;
import apimail.Converter.MensajeConverter;
import apimail.Model.Mensaje;
import apimail.Model.Usuario;
import apimail.Request.IdRequest;
import apimail.Request.MensajeRequest;
import apimail.Response.MensajeResponse;
import apimail.Services.MensajeService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by fefe on 28/6/2017.
 */

@RunWith(PowerMockRunner.class)
public class ControlMensajeTest extends TestCase {

    ControlMensajes controladora;

    MensajeService service;
    Mensaje mensaje;
    ArrayList<Mensaje> lista;
    MensajeConverter converter;

    MensajeResponse wrapper;
    MensajeRequest mensajeRequest;

    @Before
    public void setUp() {

        mensaje=new Mensaje();
        mensaje.setAsunto("prueba asunto");
        mensaje.setId(1);
        mensaje.setBody("probando body");
        //mensaje.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        //mensaje.setDestinatario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));


        wrapper=new MensajeResponse();
        wrapper.setAsunto("prueba asunto");
        wrapper.setId(1);
        wrapper.setBody("probando body");
        //wrapper.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        //wrapper.setDestinatario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));


        mensajeRequest = new MensajeRequest();
        mensajeRequest.setAsunto("prueba asunto");
        mensajeRequest.setBody("probando body");
        //mensajeRequest.setRemitente(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));
        //mensajeRequest.setDestinatario(new Usuario("hola","hola","hola","hola","hola",1,"hola","hola","hola"));

        lista=new ArrayList<Mensaje>();
        lista.add(mensaje);

        converter= Mockito.mock(MensajeConverter.class);
        controladora=new ControlMensajes();
        service=Mockito.mock(MensajeService.class);

        when(converter.convert(mensaje)).thenReturn(wrapper);

        controladora.setConverter(converter);
        controladora.setMensajeService(service);
    }

    @Test
    public void traerRecibidosTest()
    {
        when(service.traerRecibidos()).thenReturn(lista);
        assertEquals(HttpStatus.OK,controladora.getRecibidos().getStatusCode());
    }


    @Test
    public void traerRecibidosNullTest()
    {
        when(service.traerRecibidos()).thenReturn(new ArrayList<Mensaje>());
        assertEquals(HttpStatus.NO_CONTENT,controladora.getRecibidos().getStatusCode());
    }


    @Test
    public void traerEliminadosTest()
    {
        when(service.traerEliminados()).thenReturn(lista);
        assertEquals(HttpStatus.OK,controladora.getEliminados().getStatusCode());
    }

    @Test
    public void traerEliminadosNullTest()
    {
        when(service.traerEliminados()).thenReturn(new ArrayList<Mensaje>());
        assertEquals(HttpStatus.NO_CONTENT,controladora.getEliminados().getStatusCode());
    }


    @Test
    public void traerEnviadosTest()
    {
        when(service.traerEnviados()).thenReturn(lista);
        assertEquals(HttpStatus.OK,controladora.getEnviados().getStatusCode());
    }


    @Test
    public void traerEnviadosNullTest()
    {
        when(service.traerEnviados()).thenReturn(new ArrayList<Mensaje>());
        assertEquals(HttpStatus.NO_CONTENT,controladora.getEnviados().getStatusCode());
    }

    @Test
    public void cargarMensajeTest()
    {
        assertEquals(HttpStatus.CREATED,controladora.addMensaje(mensajeRequest).getStatusCode());
    }

    @Test
    public void cargarMensajeNullTest()
    {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,controladora.addMensaje(null).getStatusCode());
    }

    @Test
    public void moverPapeleraMensajeTest()
    {
        assertEquals(HttpStatus.ACCEPTED,controladora.mandarPapelera(1).getStatusCode());
    }

    @Test
    public void eliminarMensajeTest()
    {
        assertEquals(HttpStatus.ACCEPTED,controladora.removeMensaje(1).getStatusCode());
    }

    @Test
    public void eliminarMensajesTest()
    {
        List<Integer> lista =new ArrayList<Integer>();
        lista.add(1);
        IdRequest request =new IdRequest();
        request.setLista(lista);
        assertEquals(HttpStatus.ACCEPTED,controladora.removeMensajes(request).getStatusCode());
    }


    @Test
    public void traerPorIdTest()
    {
        when(service.traerPorId(anyInt())).thenReturn(mensaje);
        assertEquals(HttpStatus.OK,controladora.getById(1).getStatusCode());
    }

    @Test
    public void traerPorIdNullTest()
    {
        when(service.traerPorId(anyInt())).thenReturn(null);
        assertEquals(HttpStatus.NOT_FOUND,controladora.getById(1).getStatusCode());
    }
}
