package ControllerTeste;

import Controller.PerfilController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PerfilControllerTeste extends TestCase {

    public PerfilController perfilController;
    
    @Override
    @Before
    
    public void setUp() {
        perfilController = new PerfilController();

    }
    
    @Test
    public void testNomeNulo() {
        //assertNull(perfilController.toString(null, "123"));

    }
     
    @Test
    public void testDescricaoNula() {
        //assertNull(loginController.login("IOLANDA.IF", null));

    }
    
    @Test
    public void testCamposNulos() {
        //assertNull(loginController.login(null, null));

    }
    
    @Test
    public void testPerfilReal() {
        //assertNotNull(loginController.login("MIRELA.MC", "PAT45"));

    }

}
