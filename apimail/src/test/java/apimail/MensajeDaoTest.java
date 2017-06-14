package apimail;

import apimail.Dao.DaoMensajes;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by fefe on 13/6/2017.
 */
public class MensajeDaoTest extends TestCase {

    DaoMensajes dao;

    public void setUp() throws Exception {
        super.setUp();
        dao =new DaoMensajes();

    }


    public void testName() throws Exception {

    }
}
