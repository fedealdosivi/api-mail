/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Control;

import apimail.Converter.UsuarioConverter;
import apimail.Model.Usuario;
import java.util.ArrayList;
import java.util.List;

import apimail.Request.UsuarioRequest;
import apimail.Response.UsuarioResponse;
import apimail.Services.UserService;
import apimail.Session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
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
        value = "/api/Usuario",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ControlUsuarios {
    
    @Autowired
    private
    UserService userService;

    @Autowired
    private
    UsuarioConverter converter;

    @Autowired
    SessionData sessionData;

    @RequestMapping(value="/traerUsuarios",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<UsuarioResponse>> getAll(){
        List<Usuario> lista = getUserService().traerTodos();
        if(lista.size() > 0){
            return new ResponseEntity<List<UsuarioResponse>>(this.convertList(lista), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<UsuarioResponse>>(HttpStatus.NOT_FOUND);
        }
    }

    public List<UsuarioResponse> convertList(List<Usuario> listaUsuarios){
        List<UsuarioResponse> lista = new ArrayList<UsuarioResponse>();
        for(Usuario usuario: listaUsuarios){
            lista.add(getConverter().convert(usuario));
        }
        return lista;
    }


    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<UsuarioResponse> getById(@PathVariable("id") int id){
        Usuario user= getUserService().traerPodId(id);
        if(user != null){
            UsuarioResponse wrapper = getConverter().convert(user);
            return new ResponseEntity<UsuarioResponse>(wrapper,HttpStatus.OK);
        }else{
            return new ResponseEntity<UsuarioResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cargarUsuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUsuario(@RequestBody UsuarioRequest userRequest){
        try{
            getUserService().agregarUsuario(userRequest.getNombre(),userRequest.getApellido(),userRequest.getDireccion(),userRequest.getTelefono(),userRequest.getPassword(),userRequest.getEmail(),userRequest.getPais(),userRequest.getProvincia(),userRequest.getCiudad());
            return new ResponseEntity(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUsuario(@PathVariable ("id") int id)
    {
        try{
            getUserService().eliminarUsuario(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/traerPorNombre",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity traerUserPorNombre(@RequestHeader("nombre") String nombre)
    {
        try{
            Usuario user= getUserService().traerPorNombre(nombre);

            if(user!=null)
            {
                UsuarioResponse wrapper = getConverter().convert(user);
                return new ResponseEntity<UsuarioResponse>(wrapper,HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<UsuarioResponse>(HttpStatus.NOT_FOUND);
            }
        }

        catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UsuarioConverter getConverter() {
        return converter;
    }

    public void setConverter(UsuarioConverter converter) {
        this.converter = converter;
    }
}
