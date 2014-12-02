/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matias
 */
public class UsuarioTest {
    
    public UsuarioTest() {
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
     * Test of getUid method, of class Usuario.
     */
    @Test
    public void testGetUid() {
        System.out.println("getUid");
        Usuario instance = new Usuario();
        String expResult = "1";
        instance.setUid("1");
        String result = instance.getUid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombre method, of class Usuario.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Usuario instance = new Usuario();
        String expResult = "nombre";
        instance.setNombre("nombre");
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getApellido method, of class Usuario.
     */
    @Test
    public void testGetApellido() {
        System.out.println("getApellido");
        Usuario instance = new Usuario();
        String expResult = "apellido";
        instance.setApellido("apellido");
        String result = instance.getApellido();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getRol method, of class Usuario.
     */
    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Usuario instance = new Usuario();
        String expResult = "rol";
        instance.setRol("rol");
        String result = instance.getRol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getId method, of class Usuario.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Usuario instance = new Usuario();
        Integer expResult = 1;
        instance.setId(1);
        Integer result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setId method, of class Usuario.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = 1;
        Usuario instance = new Usuario();
        instance.setId(id);
        Integer result = instance.getId();
        assertEquals(id, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUid method, of class Usuario.
     */
    @Test
    public void testSetUid() {
        System.out.println("setUid");
        String uid = "uid";
        Usuario instance = new Usuario();
        instance.setUid(uid);
        String result = instance.getUid();
        assertEquals(uid,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNombre method, of class Usuario.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "nombre";
        Usuario instance = new Usuario();
        instance.setNombre(nombre);
        String result = instance.getNombre();
        assertEquals(nombre,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setApellido method, of class Usuario.
     */
    @Test
    public void testSetApellido() {
        System.out.println("setApellido");
        String apellido = "apellido";
        Usuario instance = new Usuario();
        instance.setApellido(apellido);
        String result = instance.getApellido();
        assertEquals(apellido,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setRol method, of class Usuario.
     */
    @Test
    public void testSetRol() {
        System.out.println("setRol");
        String rol = "rol";
        Usuario instance = new Usuario();
        instance.setRol(rol);
        String result = instance.getRol();
        assertEquals(rol,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class Usuario.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Usuario instance = new Usuario(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of equals method, of class Usuario.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new Usuario(5);
        Usuario instance = new Usuario(5);     
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Usuario(6)));
    }

    /**
     * Test of toString method, of class Usuario.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuario instance = new Usuario(5);
        String expResult = "entities.Usuario[ id=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
