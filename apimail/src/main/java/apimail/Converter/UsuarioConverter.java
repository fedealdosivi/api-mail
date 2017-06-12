package apimail.Converter;

/**
 * Created by fefe on 12/6/2017.
 */
import apimail.Response.UsuarioResponse;
import apimail.Model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UsuarioConverter {

    public UsuarioResponse convert(Usuario usuario)
    {
        UsuarioResponse wrapper=new UsuarioResponse();
        wrapper.setId(usuario.getId());
        wrapper.setApellido(usuario.getApellido());
        wrapper.setCiudad(usuario.getCiudad());
        wrapper.setDireccion(usuario.getDireccion());
        wrapper.setEmail(usuario.getEmail());
        wrapper.setNombre(usuario.getNombre());
        wrapper.setPais(usuario.getPais());
        wrapper.setPassword(usuario.getPassword());
        wrapper.setProvincia(usuario.getProvincia());
        wrapper.setTelefono(usuario.getTelefono());
        return wrapper;
    }
}
