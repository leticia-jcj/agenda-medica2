package ControllerTeste;

import Controller.LoginController;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class LoginControllerTeste extends TestCase {

    public LoginController loginController;

    @Override
    @Before

    public void setUp() {
        loginController = new LoginController();

    }
    
    @Test
    public void testUsuarioFake() {
        assertNull(loginController.login("Fake", "123"));

    }
    
    @Test
    public void testUsernameNulo() {
        assertNull(loginController.login(null, "123"));

    }
     
    @Test
    public void testSenhaNula() {
        assertNull(loginController.login("IOLANDA.IF", null));

    }
    
    @Test
    public void testCamposNulos() {
        assertNull(loginController.login(null, null));

    }
    
    @Test
    public void testUsuarioReal() {
        assertNotNull(loginController.login("MIRELA.MC", "PAT45"));

    }

}
