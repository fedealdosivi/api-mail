/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author fefe
 */
@Service
public class DaoMensajes {
    
    ArrayList<Mensaje> lista;
    
    public DaoMensajes()
    {
        lista=new ArrayList<Mensaje>();
    }
    
}
