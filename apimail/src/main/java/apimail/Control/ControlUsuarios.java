/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Converter.UsuarioConverter;
import apimail.Dao.DaoUsuarios;
import apimail.Model.Usuario;
import java.util.ArrayList;
import java.util.List;

import apimail.Request.UsuarioRequest;
import apimail.Response.UsuarioResponse;
import apimail.Services.UserService;
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
    UserService userService;

    @Autowired
    UsuarioConverter converter;


    @RequestMapping("/traerUsuarios")
    public @ResponseBody ResponseEntity<List<UsuarioResponse>> getAll(){
        List<Usuario> lista = userService.traerTodos();
        if(lista.size() > 0){
            return new ResponseEntity<List<UsuarioResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<UsuarioResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    public List<UsuarioResponse> convertList(List<Usuario> listaUsuarios){
        List<UsuarioResponse> lista = new ArrayList<UsuarioResponse>();
        for(Usuario usuario: listaUsuarios){
            lista.add(converter.convert(usuario));
        }
        return lista;
    }


    @RequestMapping("/Usuario/{id}")
    public @ResponseBody ResponseEntity<UsuarioResponse> getById(@PathVariable("id") int id){
        Usuario user= userService.traerPodId(id);
        if(user != null){
            UsuarioResponse wrapper = converter.convert(user); //Convierte de JSON a objeto
            return new ResponseEntity<UsuarioResponse>(wrapper,HttpStatus.OK);
        }else{
            return new ResponseEntity<UsuarioResponse>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/cargarUsuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUsuario(@RequestBody UsuarioRequest userRequest){
        try{
            userService.agregarUsuario(userRequest.getId(),userRequest.getNombre(),userRequest.getApellido(),userRequest.getDireccion(),userRequest.getTelefono(),userRequest.getPassword(),userRequest.getEmail(),userRequest.getPais(),userRequest.getProvincia(),userRequest.getCiudad());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
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

    }*/
}
