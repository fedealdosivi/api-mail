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
import apimail.Session.Authentication;
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
    Authentication aData;

/// estas trabajando las peticiones como si fuera un servicio SOAP




    @RequestMapping("/{id}")
    public @ResponseBody ResponseEntity<MensajeResponse> getById(@PathVariable("id") int id){
        Mensaje mensaje= mensajeService.traerPorId(id);
        if(mensaje != null){
            MensajeResponse wrapper = converter.convert(mensaje);
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

    @RequestMapping(value="/traerRecibidos",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getRecibidos(){
        List<Mensaje> lista = mensajeService.traerRecibidos();

        //List<Mensaje> lista = mensajeService.traerRecibidos(aData.getUsuario().getId());//NO ANDA USER LOGEADO

        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/traerEliminados",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEliminados(){

        List<Mensaje> lista = mensajeService.traerEliminados();
        //List<Mensaje> lista = mensajeService.traerEnviados(aData.getUsuario().getId());//NO ANDA USER LOGEADO

        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/traerEnviados",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEnviados(){

        List<Mensaje> lista = mensajeService.traerEnviados();
        //List<Mensaje> lista = mensajeService.traerEnviados(aData.getUsuario().getId());//NO ANDA USER LOGEADO

        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/MoverPapelera", method = RequestMethod.PATCH)
    public ResponseEntity mandarPapelera(@RequestHeader("idMensaje") int id)
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

    public List<MensajeResponse> convertList(List<Mensaje> listaMensajes){
        List<MensajeResponse> lista = new ArrayList<MensajeResponse>();
        for(Mensaje mensaje: listaMensajes){
            lista.add(converter.convert(mensaje));
        }
        return lista;
    }
}
