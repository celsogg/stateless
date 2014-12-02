/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
public class PerfilTest {
    
    public PerfilTest() {
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
     * Test of getIdPerfil method, of class Perfil.
     */
    @Test
    public void testGetIdPerfil() {
        System.out.println("getIdPerfil");
        Perfil instance = new Perfil(1);
        Integer expResult = 1;
        instance.setIdPerfil(1);
        Integer result = instance.getIdPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdPerfil method, of class Perfil.
     */
    @Test
    public void testSetIdPerfil() {
        System.out.println("setIdPerfil");
        Integer idPerfil = 1;
        Perfil instance = new Perfil(1);
        instance.setIdPerfil(idPerfil);
        Integer result = instance.getIdPerfil();
        assertEquals(idPerfil,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombrePerfil method, of class Perfil.
     */
    @Test
    public void testGetNombrePerfil() {
        System.out.println("getNombrePerfil");
        Perfil instance = new Perfil();
        String expResult = "nombre";
        instance.setNombrePerfil("nombre");
        String result = instance.getNombrePerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDescripcionPerfil method, of class Perfil.
     */
    @Test
    public void testGetDescripcionPerfil() {
        System.out.println("getDescripcionPerfil");
        Perfil instance = new Perfil();
        String expResult = "descripcion";
        instance.setDescripcionPerfil("descripcion");
        String result = instance.getDescripcionPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDescripcionPerfil method, of class Perfil.
     */
    @Test
    public void testSetDescripcionPerfil() {
        System.out.println("setDescripcionPerfil");
        String descripcionPerfil = "descripcion";
        Perfil instance = new Perfil();
        instance.setDescripcionPerfil(descripcionPerfil);
        String result = instance.getDescripcionPerfil();
        assertEquals(descripcionPerfil,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNombrePerfil method, of class Perfil.
     */
    @Test
    public void testSetNombrePerfil() {
        System.out.println("setNombrePerfil");
        String nombrePerfil = "nombre";
        Perfil instance = new Perfil();
        instance.setNombrePerfil(nombrePerfil);
        String result = instance.getNombrePerfil();
        assertEquals(nombrePerfil,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getVersionPerfil method, of class Perfil.
     */
    @Test
    public void testGetVersionPerfil() {
        System.out.println("getVersionPerfil");
        Perfil instance = new Perfil();
        String expResult = "version";
        instance.setVersionPerfil("version");
        String result = instance.getVersionPerfil();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setVersionPerfil method, of class Perfil.
     */
    @Test
    public void testSetVersionPerfil() {
        System.out.println("setVersionPerfil");
        String versionPerfil = "version";
        Perfil instance = new Perfil();
        instance.setVersionPerfil(versionPerfil);
        String result = instance.getVersionPerfil();
        assertEquals(versionPerfil,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getIdCarrera method, of class Perfil.
     */
    @Test
    public void testGetIdCarrera() {
        System.out.println("getIdCarrera");
        Perfil instance = new Perfil();
        Carrera expResult = new Carrera(1);
        instance.setIdCarrera(expResult);
        Carrera result = instance.getIdCarrera();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdCarrera method, of class Perfil.
     */
    @Test
    public void testSetIdCarrera() {
        System.out.println("setIdCarrera");
        Carrera idCarrera = new Carrera(1);
        Perfil instance = new Perfil();
        instance.setIdCarrera(idCarrera);
        Carrera result = instance.getIdCarrera();
        assertEquals(idCarrera,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getSeccionCollection method, of class Perfil.
     */
    @Test
    public void testGetSeccionCollection() {
        System.out.println("getSeccionCollection");
        Perfil instance = new Perfil();
        List<Seccion> expResult = new ArrayList<>();
        Seccion a = new Seccion(1);
        Seccion b = new Seccion(2);
        Seccion c = new Seccion(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setSeccionCollection(expResult);
        List<Seccion> result = instance.getSeccionCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setSeccionCollection method, of class Perfil.
     */
    @Test
    public void testSetSeccionCollection() {
        System.out.println("setSeccionCollection");
        List<Seccion> seccionCollection = new ArrayList<>();
        Perfil instance = new Perfil();
        Seccion a = new Seccion(1);
        Seccion b = new Seccion(2);
        Seccion c = new Seccion(3);
        seccionCollection.add(a);
        seccionCollection.add(b);
        seccionCollection.add(c);
        instance.setSeccionCollection(seccionCollection);
        List<Seccion> result = instance.getSeccionCollection();
        assertEquals(seccionCollection,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class Perfil.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Perfil instance = new Perfil(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of equals method, of class Perfil.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Perfil object = new Perfil(5);
        Perfil instance = new Perfil(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Seccion(6)));
    }

    /**
     * Test of toString method, of class Perfil.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Perfil instance = new Perfil(5);
        String expResult = "entities.Perfil[ idPerfil=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
