/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajeria.api.avanzada.Dao;
import com.mycompany.mensajeria.api.avanzada.Model.Mensaje;
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
