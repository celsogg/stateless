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
public class AsignaturaTest {
    
    public AsignaturaTest() {
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
     * Test of getIdAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetIdAsignatura() {
        System.out.println("getIdAsignatura");
        Asignatura instance = new Asignatura(5);
        Integer expResult = 5;
        Integer result = instance.getIdAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetIdAsignatura() {
        System.out.println("setIdAsignatura");
        Integer idAsignatura = 1;
        Asignatura instance = new Asignatura();
        instance.setIdAsignatura(1);
        Integer result = instance.getIdAsignatura();
        assertEquals(idAsignatura, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNombreAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetNombreAsignatura() {
        System.out.println("getNombreAsignatura");
        Asignatura instance = new Asignatura();
        instance.setNombreAsignatura("asignatura");
        String expResult = "asignatura";
        String result = instance.getNombreAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNombreAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetNombreAsignatura() {
        System.out.println("setNombreAsignatura");
        String nombreAsignatura = "asignatura";
        Asignatura instance = new Asignatura();
        instance.setNombreAsignatura("asignatura");
        assertEquals(instance.getNombreAsignatura(), nombreAsignatura);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getSctAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetSctAsignatura() {
        System.out.println("getSctAsignatura");
        Asignatura instance = new Asignatura();
        instance.setSctAsignatura(1);
        Integer expResult = 1;
        Integer result = instance.getSctAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setSctAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetSctAsignatura() {
        System.out.println("setSctAsignatura");
        Integer sctAsignatura = 1;
        Asignatura instance = new Asignatura();
        instance.setSctAsignatura(1);
        Integer result = instance.getSctAsignatura();
        assertEquals(sctAsignatura, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getNivelAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetNivelAsignatura() {
        System.out.println("getNivelAsignatura");
        Asignatura instance = new Asignatura();
        Integer expResult = 1;
        instance.setNivelAsignatura(1);
        Integer result = instance.getNivelAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNivelAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetNivelAsignatura() {
        System.out.println("setNivelAsignatura");
        Integer nivelAsignatura = 1;
        Asignatura instance = new Asignatura();
        instance.setNivelAsignatura(1);
        Integer result = instance.getNivelAsignatura();
        assertEquals(nivelAsignatura, result);
         // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getCodigoAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetCodigoAsignatura() {
        System.out.println("getCodigoAsignatura");
        Asignatura instance = new Asignatura();
        String expResult = "10121";
        instance.setCodigoAsignatura("10121");
        String result = instance.getCodigoAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setCodigoAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetCodigoAsignatura() {
        System.out.println("setCodigoAsignatura");
        String codigoAsignatura = "10121";
        Asignatura instance = new Asignatura();
        instance.setCodigoAsignatura(codigoAsignatura);
        String result = instance.getCodigoAsignatura();
        assertEquals(codigoAsignatura, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getHorasTeoria method, of class Asignatura.
     */
    @Test
    public void testGetHorasTeoria() {
        System.out.println("getHorasTeoria");
        Asignatura instance = new Asignatura();
        Integer expResult = 1;
        instance.setHorasTeoria(1);
        Integer result = instance.getHorasTeoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setHorasTeoria method, of class Asignatura.
     */
    @Test
    public void testSetHorasTeoria() {
        System.out.println("setHorasTeoria");
        Integer horasTeoria = 1;
        Asignatura instance = new Asignatura();
        instance.setHorasTeoria(horasTeoria);
        Integer result = instance.getHorasTeoria();
        assertEquals(horasTeoria,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getHorasEjercicio method, of class Asignatura.
     */
    @Test
    public void testGetHorasEjercicio() {
        System.out.println("getHorasEjercicio");
        Asignatura instance = new Asignatura();
        Integer expResult = 1;
        instance.setHorasEjercicio(1);
        Integer result = instance.getHorasEjercicio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setHorasEjercicio method, of class Asignatura.
     */
    @Test
    public void testSetHorasEjercicio() {
        System.out.println("setHorasEjercicio");
        Integer horasEjercicio = 1;
        Asignatura instance = new Asignatura();
        instance.setHorasEjercicio(horasEjercicio);
        Integer result = instance.getHorasEjercicio();
        assertEquals(horasEjercicio, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getHorasLaboratorio method, of class Asignatura.
     */
    @Test
    public void testGetHorasLaboratorio() {
        System.out.println("getHorasLaboratorio");
        Asignatura instance = new Asignatura();
        Integer expResult = 1;
        instance.setHorasLaboratorio(1);
        Integer result = instance.getHorasLaboratorio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setHorasLaboratorio method, of class Asignatura.
     */
    @Test
    public void testSetHorasLaboratorio() {
        System.out.println("setHorasLaboratorio");
        Integer horasLaboratorio = 1;
        Asignatura instance = new Asignatura();
        instance.setHorasLaboratorio(horasLaboratorio);
        Integer result = instance.getHorasLaboratorio();
        assertEquals(horasLaboratorio, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getResumenAsignatura method, of class Asignatura.
     */
    @Test
    public void testGetResumenAsignatura() {
        System.out.println("getResumenAsignatura");
        Asignatura instance = new Asignatura();
        String expResult = "Resumen";
        instance.setResumenAsignatura("Resumen");
        String result = instance.getResumenAsignatura();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setResumenAsignatura method, of class Asignatura.
     */
    @Test
    public void testSetResumenAsignatura() {
        System.out.println("setResumenAsignatura");
        String resumenAsignatura = "Resumen";
        Asignatura instance = new Asignatura();
        instance.setResumenAsignatura(resumenAsignatura);
        String result = instance.getResumenAsignatura();
        assertEquals(resumenAsignatura, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAsignaturaCollection method, of class Asignatura.
     */
    @Test
    public void testGetAsignaturaCollection() {
        System.out.println("getAsignaturaCollection");
        Asignatura instance = new Asignatura();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setAsignaturaCollection(expResult);
        Collection<Asignatura> result = instance.getAsignaturaCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setAsignaturaCollection method, of class Asignatura.
     */
    @Test
    public void testSetAsignaturaCollection() {
        System.out.println("setAsignaturaCollection");
        List<Asignatura> asignaturaCollection = new ArrayList<>();
        
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        asignaturaCollection.add(a);
        asignaturaCollection.add(b);
        asignaturaCollection.add(c);
        Asignatura instance = new Asignatura();
        instance.setAsignaturaCollection(asignaturaCollection);
        Collection<Asignatura> result = instance.getAsignaturaCollection();
        assertEquals(asignaturaCollection,result);
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAsignaturaCollection1 method, of class Asignatura.
     */
    @Test
    public void testGetAsignaturaCollection1() {
        System.out.println("getAsignaturaCollection1");
        Asignatura instance = new Asignatura();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setAsignaturaCollection1(expResult);
        Collection<Asignatura> result = instance.getAsignaturaCollection1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setAsignaturaCollection1 method, of class Asignatura.
     */
    @Test
    public void testSetAsignaturaCollection1() {
        System.out.println("setAsignaturaCollection1");
        List<Asignatura> asignaturaCollection1 = new ArrayList<>();
        
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        asignaturaCollection1.add(a);
        asignaturaCollection1.add(b);
        asignaturaCollection1.add(c);
        Asignatura instance = new Asignatura();
        instance.setAsignaturaCollection1(asignaturaCollection1);
        Collection<Asignatura> result = instance.getAsignaturaCollection1();
        assertEquals(asignaturaCollection1,result);
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getIdPlan method, of class Asignatura.
     */
    @Test
    public void testGetIdPlan() {
        System.out.println("getIdPlan");
        Asignatura instance = new Asignatura();
        Plan expResult = new Plan(1);
        instance.setIdPlan(expResult);
        Plan result = instance.getIdPlan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setIdPlan method, of class Asignatura.
     */
    @Test
    public void testSetIdPlan() {
        System.out.println("setIdPlan");
        Plan idPlan = new Plan(1);
        Asignatura instance = new Asignatura();
        instance.setIdPlan(idPlan);
        Plan result = instance.getIdPlan();
        assertEquals(idPlan,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getConvalidaciones method, of class Asignatura.
     */
    @Test
    public void testGetConvalidaciones() {
        System.out.println("getConvalidaciones");
        Asignatura instance = new Asignatura();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setConvalidaciones(expResult);
        Collection<Asignatura> result = instance.getConvalidaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setConvalidaciones method, of class Asignatura.
     */
    @Test
    public void testSetConvalidaciones() {
        System.out.println("setConvalidaciones");
        List<Asignatura> convalidaciones = new ArrayList<>();
        Asignatura instance = new Asignatura();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        convalidaciones.add(a);
        convalidaciones.add(b);
        convalidaciones.add(c);
        instance.setConvalidaciones(convalidaciones);
        Collection<Asignatura> result = instance.getConvalidaciones();
        assertEquals(convalidaciones, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getConvalidadaPor method, of class Asignatura.
     */
    @Test
    public void testGetConvalidadaPor() {
        System.out.println("getConvalidadaPor");
        Asignatura instance = new Asignatura();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setConvalidadaPor(expResult);
        Collection<Asignatura> result = instance.getConvalidadaPor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setConvalidadaPor method, of class Asignatura.
     */
    @Test
    public void testSetConvalidadaPor() {
        System.out.println("setConvalidadaPor");
        Asignatura instance = new Asignatura();
        List<Asignatura> convalidadaPor = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        convalidadaPor.add(a);
        convalidadaPor.add(b);
        convalidadaPor.add(c);
        instance.setConvalidadaPor(convalidadaPor);
        Collection<Asignatura> result = instance.getConvalidadaPor();
        assertEquals(convalidadaPor, result);
    

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class Asignatura.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Asignatura instance = new Asignatura(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of equals method, of class Asignatura.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new Asignatura(5);
        Asignatura instance = new Asignatura(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Asignatura(6)));
    }

    /**
     * Test of toString method, of class Asignatura.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Asignatura instance = new Asignatura(5);
        String expResult = "entities.Asignatura[ idAsignatura=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEsAnual method, of class Asignatura.
     */
    @Test
    public void testGetEsAnual() {
        System.out.println("getEsAnual");
        Asignatura instance = new Asignatura();
        instance.setEsAnual(true);
        Boolean expResult = true;
        Boolean result = instance.getEsAnual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setEsAnual method, of class Asignatura.
     */
    @Test
    public void testSetEsAnual() {
        System.out.println("setEsAnual");
        Boolean esAnual = true;
        Asignatura instance = new Asignatura();
        instance.setEsAnual(esAnual);
        Boolean expResult = true;
        Boolean result = instance.getEsAnual();
        assertEquals(expResult,result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
