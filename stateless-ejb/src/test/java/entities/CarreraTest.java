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
public class CarreraTest {
    
    public CarreraTest() {
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
     * Test of getIdCarrera method, of class Carrera.
     */
    @Test
    public void testGetIdCarrera() {
        System.out.println("getIdCarrera");
        Carrera instance = new Carrera(1);
        Integer expResult = 1;
        Integer result = instance.getIdCarrera();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdCarrera method, of class Carrera.
     */
    @Test
    public void testSetIdCarrera() {
        System.out.println("setIdCarrera");
        Integer idCarrera = 1;
        Carrera instance = new Carrera(1);
        instance.setIdCarrera(idCarrera);
        Integer result = instance.getIdCarrera();
        
        assertEquals(idCarrera, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombreCarrera method, of class Carrera.
     */
    @Test
    public void testGetNombreCarrera() {
        System.out.println("getNombreCarrera");
        Carrera instance = new Carrera();
        String expResult = "Carrera";
        instance.setNombreCarrera("Carrera");
        String result = instance.getNombreCarrera();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNombreCarrera method, of class Carrera.
     */
    @Test
    public void testSetNombreCarrera() {
        System.out.println("setNombreCarrera");
        String nombreCarrera = "Carrera";
        Carrera instance = new Carrera();
        instance.setNombreCarrera(nombreCarrera);
        String result = instance.getNombreCarrera();
        assertEquals(nombreCarrera,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPlanCollection method, of class Carrera.
     */
    @Test
    public void testGetPlanCollection() {
        System.out.println("getPlanCollection");
        Carrera instance = new Carrera();
        List<Plan> expResult = new ArrayList<>();
        Plan a = new Plan(1);
        Plan b = new Plan(2);
        Plan c = new Plan(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setPlanCollection(expResult);
        Collection<Plan> result = instance.getPlanCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPlanCollection method, of class Carrera.
     */
    @Test
    public void testSetPlanCollection() {
        System.out.println("setPlanCollection");
        List<Plan> planCollection = new ArrayList<>();
        Carrera instance = new Carrera();
        Plan a = new Plan(1);
        Plan b = new Plan(2);
        Plan c = new Plan(3);
        planCollection.add(a);
        planCollection.add(b);
        planCollection.add(c);
        instance.setPlanCollection(planCollection); 
        Collection<Plan> result = instance.getPlanCollection();
        assertEquals(planCollection,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class Carrera.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Carrera instance = new Carrera(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of equals method, of class Carrera.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new Carrera(5);
        Carrera instance = new Carrera(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Carrera(6)));
    }

    /**
     * Test of toString method, of class Carrera.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Carrera instance = new Carrera(5);
        String expResult = "entities.Carrera[ idCarrera=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPerfilCollection method, of class Carrera.
     */
    @Test
    public void testGetPerfilCollection() {
        System.out.println("getPerfilCollection");
        Carrera instance = new Carrera();
        List<Perfil> expResult = new ArrayList<>();
        Perfil a = new Perfil(1);
        Perfil b = new Perfil(2);
        Perfil c = new Perfil(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setPerfilCollection(expResult);
        List<Perfil> result = instance.getPerfilCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setPerfilCollection method, of class Carrera.
     */
    @Test
    public void testSetPerfilCollection() {
        System.out.println("setPerfilCollection");
        List<Perfil> perfilCollection = new ArrayList<>();
        Carrera instance = new Carrera();
        Perfil a = new Perfil(1);
        Perfil b = new Perfil(2);
        Perfil c = new Perfil(3);
        perfilCollection.add(a);
        perfilCollection.add(b);
        perfilCollection.add(c);
        instance.setPerfilCollection(perfilCollection);
        List<Perfil> result = instance.getPerfilCollection();
        assertEquals(perfilCollection,result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
