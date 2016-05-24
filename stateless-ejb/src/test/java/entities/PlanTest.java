package entities;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlanTest {

    public PlanTest() {
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
     * Test of getIdPlan method, of class Plan.
     */
    @Test
    public void testGetIdPlan() {
        System.out.println("getIdPlan");
        Plan instance = new Plan(5);
        Integer expResult = 5;
        Integer result = instance.getIdPlan();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdPlan method, of class Plan.
     */
    @Test
    public void testSetIdPlan() {
        System.out.println("setIdPlan");
        Integer idPlan = 1;
        Plan instance = new Plan();
        instance.setIdPlan(1);
        assertEquals(idPlan, instance.getIdPlan());
    }

    @Test
    public void testGetNombrePlan() {
        System.out.println("getNombrePlan");
        Plan instance = new Plan();
        instance.setNombrePlan("hola");
        String expResult = "hola";
        String result = instance.getNombrePlan();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNombrePlan() {
        System.out.println("setNombrePlan");
        String nombrePlan = "hola";
        Plan instance = new Plan();
        instance.setNombrePlan("hola");
        assertEquals(instance.getNombrePlan(), nombrePlan);
    }

    @Test
    public void testGetAnioPlan() {
        System.out.println("getAnioPlan");
        Plan instance = new Plan();
        Integer expResult = 2001;
        instance.setAnioPlan(2001);
        Integer result = instance.getAnioPlan();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAnioPlan() {
        System.out.println("setAnioPlan");
        Integer anioPlan = 2001;
        Plan instance = new Plan();
        instance.setAnioPlan(2001);
        assertEquals(instance.getAnioPlan(), anioPlan);
    }

    @Test
    public void testGetCodigoPlan() {
        System.out.println("getCodigoPlan");
        Plan instance = new Plan();
        String expResult = "10130";
        instance.setCodigoPlan("10130");
        String result = instance.getCodigoPlan();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCodigoPlan() {
        System.out.println("setCodigoPlan");
        String codigoPlan = "10130";
        Plan instance = new Plan();
        instance.setCodigoPlan(codigoPlan);
        assertEquals("10130", instance.getCodigoPlan());
    }

    @Test
    public void testGetIdCarrera() {
        System.out.println("getIdCarrera");
        Plan instance = new Plan();
        Carrera a = new Carrera(1);
        instance.setIdCarrera(a);
        Carrera expResult = a;
        Carrera result = instance.getIdCarrera();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdCarrera() {
        System.out.println("setIdCarrera");
        Carrera idCarrera = new Carrera(1);
        Plan instance = new Plan();
        instance.setIdCarrera(idCarrera);
        Carrera expResult = idCarrera;
        Carrera result = instance.getIdCarrera();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetVisiblePlan() {
        System.out.println("getVisiblePlan");
        Plan instance = new Plan();
        Boolean expResult = true;
        instance.setVisiblePlan(true);
        Boolean result = instance.getVisiblePlan();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetVisiblePlanString() {
        System.out.println("getVisiblePlanString");
        Plan instance = new Plan();
        String expResult = "Si";
        String expResultNo = "No";
        instance.setVisiblePlan(true);
        String result = instance.getVisiblePlanString();
        assertEquals(expResult, result);

        instance.setVisiblePlan(false);
        assertEquals(expResultNo, instance.getVisiblePlanString());
    }

    @Test
    public void testSetVisiblePlan() {
        System.out.println("setVisiblePlan");
        Boolean visiblePlan = true;
        Plan instance = new Plan();
        instance.setVisiblePlan(visiblePlan);
        assertEquals(true, instance.getVisiblePlan());
    }

    @Test
    public void testGetVersionPlan() {
        System.out.println("getVersionPlan");
        Plan instance = new Plan();
        String expResult = "1";
        instance.setVersionPlan("1");
        String result = instance.getVersionPlan();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetVersionPlan() {
        System.out.println("setVersionPlan");
        String versionPlan = "1";
        Plan instance = new Plan();
        instance.setVersionPlan("1");
        assertEquals(versionPlan, instance.getVersionPlan());
    }

    @Test
    public void testGetAsignaturaCollection() {
        System.out.println("getAsignaturaCollection");
        Plan instance = new Plan();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setAsignaturaCollection(expResult);
        List<Asignatura> result = instance.getAsignaturaCollection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAsignaturasRequisito method, of class Plan.
     */
    @Test
    public void testSetAsignaturaCollection() {
        System.out.println("getAsignaturaCollection");
        Plan instance = new Plan();
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        instance.setAsignaturaCollection(expResult);
        List<Asignatura> result = instance.getAsignaturaCollection();
        assertEquals(expResult, result);
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Plan instance = new Plan(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new Plan(5);
        Plan instance = new Plan(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new Plan(6)));
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Plan instance = new Plan(5);
        String expResult = "entities.Plan[ idPlan=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
