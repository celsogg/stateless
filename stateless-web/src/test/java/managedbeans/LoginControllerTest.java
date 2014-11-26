/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author miguel
 */
public class LoginControllerTest {
    
    public LoginControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class LoginController.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        LoginController instance = new LoginController();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class LoginController.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.login();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class LoginController.
     */
    @Test
    public void testLogout() throws Exception {
        System.out.println("logout");
        LoginController instance = new LoginController();
        instance.logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUserNotLogin method, of class LoginController.
     */
    @Test
    public void testIsUserNotLogin() {
        System.out.println("isUserNotLogin");
        LoginController instance = new LoginController();
        instance.isUserNotLogin();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoginUserName method, of class LoginController.
     */
    @Test
    public void testGetLoginUserName() {
        System.out.println("getLoginUserName");
        LoginController instance = new LoginController();
        instance.getLoginUserName();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRol method, of class LoginController.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.getRol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRol method, of class LoginController.
     */
    @Test
    public void testSetRol() {
        System.out.println("setRol");
        String rol = "";
        LoginController instance = new LoginController();
        instance.setRol(rol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esAdmin method, of class LoginController.
     */
    @Test
    public void testEsAdmin() {
        System.out.println("esAdmin");
        LoginController instance = new LoginController();
        Boolean expResult = null;
        Boolean result = instance.esAdmin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noEsAdmin method, of class LoginController.
     */
    @Test
    public void testNoEsAdmin() {
        System.out.println("noEsAdmin");
        LoginController instance = new LoginController();
        Boolean expResult = null;
        Boolean result = instance.noEsAdmin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class LoginController.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginController.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginController instance = new LoginController();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class LoginController.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        LoginController instance = new LoginController();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class LoginController.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        LoginController instance = new LoginController();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsLoggedIn method, of class LoginController.
     */
    @Test
    public void testGetIsLoggedIn() {
        System.out.println("getIsLoggedIn");
        LoginController instance = new LoginController();
        boolean expResult = false;
        boolean result = instance.getIsLoggedIn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsLoggedIn method, of class LoginController.
     */
    @Test
    public void testSetIsLoggedIn() {
        System.out.println("setIsLoggedIn");
        boolean isloggedIn = false;
        LoginController instance = new LoginController();
        instance.setIsLoggedIn(isloggedIn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
