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
        value = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ControlMensajes {
    
    @Autowired
    MensajeService mensajeService;

    @Autowired
    MensajeConverter converter;



    @RequestMapping("Mensaje/traerMensajes")
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


    @RequestMapping("/Mensaje/{id}")
    public @ResponseBody ResponseEntity<MensajeResponse> getById(@PathVariable("id") int id){
        Mensaje mensaje= mensajeService.traerPorId(id);
        if(mensaje != null){
            MensajeResponse wrapper = converter.convert(mensaje); //Convierte de JSON a objeto
            return new ResponseEntity<MensajeResponse>(wrapper,HttpStatus.OK);
        }else{
            return new ResponseEntity<MensajeResponse>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "Mensaje/cargarMensaje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMensaje(@RequestBody MensajeRequest mensajeRequest){
        try{
            mensajeService.agregarMensaje(mensajeRequest.getId(),mensajeRequest.getAsunto(),mensajeRequest.getBody(),mensajeRequest.getRemitente(),mensajeRequest.getDestinatarios());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //TRAER SIN WRAPPER SOLO PARA PROBAR

    @RequestMapping(value = "/bandeja",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Mensaje> traerTodos() {
        try {
            return mensajeService.traerArrayTodos();
        } catch (Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }
/*
    @RequestMapping(value = "/Mensaje/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mensaje mensajePorId(@PathVariable("id") Integer id){
        try{
            return acceso.traerMensajePorId(id);
        }
        catch(Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/Mensaje", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarMensaje(@RequestBody Mensaje mensaje)
    {
        try {
            acceso.cargarMensaje(mensaje);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    */
}
