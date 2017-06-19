package apimail;

/**
 * Created by fefe on 18/6/2017.
 */
import apimail.Dao.DaoUsuarios;
import apimail.Model.Mensaje;
import apimail.Dao.DaoMensajes;
import apimail.Model.Usuario;

import java.util.ArrayList;

public class prueba {


    public static void main(String[] args) {

        ArrayList<Mensaje> lista=new ArrayList<Mensaje>();
        DaoMensajes acceso;

        DaoUsuarios accesoUsuario;

        try {

            accesoUsuario=new DaoUsuarios();

            acceso=new DaoMensajes();
            lista=acceso.traerTodos();

            for (Usuario u:accesoUsuario.traerTodos()
                 ) {
                System.out.println(u.toString());
            }

            for (Mensaje m:acceso.traerTodos()
                 ) {
                System.out.println(m.toString());
            }
        }

        catch(Exception e)
        {
            e.getStackTrace();
        }

    }
}
