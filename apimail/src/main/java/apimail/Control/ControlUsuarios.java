/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Dao.DaoUsuarios;
import apimail.Model.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author fefe
 */
@Controller

public class ControlUsuarios {
    
    @Autowired
    private DaoUsuarios acceso;
    
      @RequestMapping(value = "/traerUsuarios",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Usuario> traerTodos()
    {
        return acceso.traerTodos();
    }

    @RequestMapping(value = "/Usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Usuario userPorId(@PathVariable("id") Integer id){
        try{
        return acceso.traerUsuarioPorId(id);
        }
        catch(Exception e)
        {
            e.getStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/Usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity agregarUsuario(@RequestBody Usuario usuario)
    {
        try {
            acceso.cargarUsuario(usuario);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
