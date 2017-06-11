/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Dao.DaoMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import apimail.Model.Mensaje;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author fefe
 */
@Controller

public class ControlMensajes {
    
    @Autowired
    private DaoMensajes acceso;

    @RequestMapping(value = "/traerMensajes",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Mensaje> traerTodos()
    {
        return acceso.traerTodos();
    }

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
}
