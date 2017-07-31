package apimail.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by fefe on 30/7/2017.
 */
public class IdRequest {

    @JsonProperty("idmensajes")
    List<Integer> lista;

    public List<Integer> getLista() {
        return lista;
    }

    public void setLista(List<Integer> lista) {
        this.lista = lista;
    }
}
