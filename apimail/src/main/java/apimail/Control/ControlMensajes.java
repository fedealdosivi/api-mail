/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajeria.api.avanzada.Control;

import com.mycompany.mensajeria.api.avanzada.Dao.DaoMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fefe
 */
@Controller

public class ControlMensajes {
    
    @Autowired
    private DaoMensajes acceso;
}
