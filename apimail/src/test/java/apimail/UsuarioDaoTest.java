package apimail;

import apimail.Dao.DaoUsuarios;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by fefe on 13/6/2017.
 */
public class UsuarioDaoTest extends TestCase{

    DaoUsuarios dao;

    public void setUp() throws Exception {
        super.setUp();
        dao=new DaoUsuarios();
    }

    public void testName() throws Exception {

    }
}
