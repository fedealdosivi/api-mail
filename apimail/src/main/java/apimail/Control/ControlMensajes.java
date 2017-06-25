/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Converter.MensajeConverter;
import apimail.Dao.DaoMensajes;
import apimail.Request.MensajeRequest;
import apimail.Response.MensajeResponse;
import apimail.Services.MensajeService;
import apimail.Session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import apimail.Model.Mensaje;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author fefe
 */
@RestController
@RequestMapping(
        value = "/api/Mensaje",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ControlMensajes {
    
    @Autowired
    MensajeService mensajeService;

    @Autowired
    MensajeConverter converter;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value= "/traerMensajes",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getAll(){
        List<Mensaje> lista = mensajeService.traerTodos();
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    public List<MensajeResponse> convertList(List<Mensaje> listaMensajes){
        List<MensajeResponse> lista = new ArrayList<MensajeResponse>();
        for(Mensaje mensaje: listaMensajes){
            lista.add(converter.convert(mensaje));
        }
        return lista;
    }


    @RequestMapping("/{id}")
    public @ResponseBody ResponseEntity<MensajeResponse> getById(@PathVariable("id") int id){
        Mensaje mensaje= mensajeService.traerPorId(id);
        if(mensaje != null){
            MensajeResponse wrapper = converter.convert(mensaje); //Convierte de JSON a objeto
            return new ResponseEntity<MensajeResponse>(wrapper,HttpStatus.OK);
        }else{
            return new ResponseEntity<MensajeResponse>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/cargarMensaje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMensaje(@RequestBody MensajeRequest mensajeRequest){
        try{
            mensajeService.agregarMensaje(mensajeRequest.getId(),mensajeRequest.getAsunto(),mensajeRequest.getBody(),mensajeRequest.getRemitente(),mensajeRequest.getDestinatario());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeMensaje(@PathVariable ("id") int id)
    {
        try{
            mensajeService.eliminarMensaje(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/traerRecibidos",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getRecibidos(@RequestHeader("idUsuario") int id){
        List<Mensaje> lista = mensajeService.traerRecibidos(id);
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/traerEliminados",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEliminados(@RequestHeader("idUsuario") int id){
        List<Mensaje> lista = mensajeService.traerEliminados(id);
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/traerEnviados",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEnviados(@RequestHeader("idUsuario") int id){
        List<Mensaje> lista = mensajeService.traerEnviados(id);
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/papelera/{id}", method = RequestMethod.PATCH)
    public ResponseEntity mandarPapelera(@PathVariable ("id") int id)
    {
        try{
            mensajeService.cambiarAEliminado(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/leidos/{id}", method = RequestMethod.PATCH)
    public ResponseEntity marcarLeido(@PathVariable ("id") int id)
    {
        try{
            mensajeService.cambiarALeido(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
