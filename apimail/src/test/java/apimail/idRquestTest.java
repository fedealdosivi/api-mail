package apimail;

import apimail.Request.IdRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by fefe on 31/7/2017.
 */
public class idRquestTest {

    IdRequest request;
    List<Integer> lista;

    @Before
    public void setUp()
    {
        request=new IdRequest();
        request.setLista(lista);
    }

    @Test
    public void testLista()
    {
        assertEquals(lista, request.getLista());
    }
}
