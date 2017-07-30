package apimail.Converter;

/**
 * Created by fefe on 12/6/2017.
 */

import apimail.Response.MensajeResponse;
import apimail.Model.Mensaje;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MensajeConverter {

    public MensajeResponse convert(Mensaje mensaje) {
        MensajeResponse wrapper = new MensajeResponse();
        wrapper.setRemitente(mensaje.getRemitente());
        wrapper.setBody(mensaje.getBody());
        wrapper.setAsunto(mensaje.getAsunto());
        wrapper.setId(mensaje.getId());
        wrapper.setDestinatarios(mensaje.getDestinatarios());
        wrapper.setDateTime(mensaje.getDateTime());
        return wrapper;
    }
}
