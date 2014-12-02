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
public class SeccionTest {
    
    public SeccionTest() {
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
     * Test of getIdSeccion method, of class Seccion.
     */
    @Test
    public void testGetIdSeccion() {
        System.out.println("getIdSeccion");
        Seccion instance = new Seccion(1);
        Integer expResult = 1;
        Integer result = instance.getIdSeccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdSeccion method, of class Seccion.
     */
    @Test
    public void testSetIdSeccion() {
        System.out.println("setIdSeccion");
        Integer idSeccion = 1;
        Seccion instance = new Seccion(1);
        instance.setIdSeccion(idSeccion);
        Integer result = instance.getIdSeccion();
        assertEquals(idSeccion,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombreSeccion method, of class Seccion.
     */
    @Test
    public void testGetNombreSeccion() {
        System.out.println("getNombreSeccion");
        Seccion instance = new Seccion();
        String expResult = "Seccion";
        instance.setNombreSeccion("Seccion");
        String result = instance.getNombreSeccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNombreSeccion method, of class Seccion.
     */
    @Test
    public void testSetNombreSeccion() {
        System.out.println("setNombreSeccion");
        String nombreSeccion = "Seccion";
        Seccion instance = new Seccion();
        instance.setNombreSeccion(nombreSeccion);
        String result = instance.getNombreSeccion();
        assertEquals(nombreSeccion,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDescripcionSeccion method, of class Seccion.
     */
    @Test
    public void testGetDescripcionSeccion() {
        System.out.println("getDescripcionSeccion");
        Seccion instance = new Seccion();
        String expResult = "Descripcion";
        instance.setDescripcionSeccion(expResult);
        String result = instance.getDescripcionSeccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDescripcionSeccion method, of class Seccion.
     */
    @Test
    public void testSetDescripcionSeccion() {
        System.out.println("setDescripcionSeccion");
        String descripcionSeccion = "Descripcion";
        Seccion instance = new Seccion();
        instance.setDescripcionSeccion(descripcionSeccion);
        String result = instance.getDescripcionSeccion();
        assertEquals(descripcionSeccion,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getIdPerfil method, of class Seccion.
     */
    @Test
    public void testGetIdPerfil() {
        System.out.println("getIdPerfil");
        Seccion instance = new Seccion();
        Perfil expResult = new Perfil(1);
        instance.setIdPerfil(expResult);
        Perfil result = instance.getIdPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdPerfil method, of class Seccion.
     */
    @Test
    public void testSetIdPerfil() {
        System.out.println("setIdPerfil");
        Perfil idPerfil = new Perfil(1);
        Seccion instance = new Seccion();
        instance.setIdPerfil(idPerfil);
        Perfil result = instance.getIdPerfil();
        assertEquals(idPerfil,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class Seccion.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Seccion instance = new Seccion(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of equals method, of class Seccion.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new Seccion(5);
        Seccion instance = new Seccion(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Seccion(6)));
    }
    /**
     * Test of toString method, of class Seccion.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Seccion instance = new Seccion(5);
        String expResult = "entities.Seccion[ idSeccion=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
