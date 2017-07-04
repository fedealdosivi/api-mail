/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Converter.MensajeConverter;
import apimail.Request.MensajeRequest;
import apimail.Response.MensajeResponse;
import apimail.Services.MensajeService;
import apimail.Session.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
    private
    MensajeService mensajeService;

    @Autowired
    private
    MensajeConverter converter;

    @Autowired
    Authentication aData;

/// estas trabajando las peticiones como si fuera un servicio SOAP


    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<MensajeResponse> getById(@PathVariable("id") int id){
        Mensaje mensaje= getMensajeService().traerPorId(id);
        if(mensaje != null){
            MensajeResponse wrapper = getConverter().convert(mensaje);
            return new ResponseEntity<MensajeResponse>(wrapper,HttpStatus.OK);
        }else{
            return new ResponseEntity<MensajeResponse>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMensaje(@RequestBody MensajeRequest mensajeRequest){
        try{
            getMensajeService().agregarMensaje(mensajeRequest.getId(),mensajeRequest.getAsunto(),mensajeRequest.getBody(),mensajeRequest.getRemitente(),mensajeRequest.getDestinatario());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeMensaje(@PathVariable ("id") int id)
    {
        try{
            getMensajeService().eliminarMensaje(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/inbox",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getRecibidos(){
        List<Mensaje> lista = getMensajeService().traerRecibidos();
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/trash",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEliminados(){

        List<Mensaje> lista = getMensajeService().traerEliminados();
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/sent",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<MensajeResponse>> getEnviados(){

        List<Mensaje> lista = getMensajeService().traerEnviados();
        if(lista.size() > 0){
            return new ResponseEntity<List<MensajeResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<MensajeResponse>>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public ResponseEntity mandarPapelera(@PathVariable ("id") int id)
    {
        try{
            getMensajeService().cambiarAEliminado(id);
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
            lista.add(getConverter().convert(mensaje));
        }
        return lista;
    }

    public MensajeService getMensajeService() {
        return mensajeService;
    }

    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    public MensajeConverter getConverter() {
        return converter;
    }

    public void setConverter(MensajeConverter converter) {
        this.converter = converter;
    }
}
