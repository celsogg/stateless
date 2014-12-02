/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SystemLogTest {
    
    public SystemLogTest() {
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
     * Test of getLogsId method, of class SystemLog.
     */
    @Test
    public void testGetLogsId() {
        System.out.println("getLogsId");
        SystemLog instance = new SystemLog(1);
        Integer expResult = 1;
        instance.setLogsId(expResult);
        Integer result = instance.getLogsId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLogsId method, of class SystemLog.
     */
    @Test
    public void testSetLogsId() {
        System.out.println("setLogsId");
        Integer logsId = 1;
        SystemLog instance = new SystemLog(1);
        instance.setLogsId(logsId);
        Integer result = instance.getLogsId();
        assertEquals(logsId,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getUserId method, of class SystemLog.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        SystemLog instance = new SystemLog();
        String expResult = "1";
        instance.setUserId(expResult);
        String result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setUserId method, of class SystemLog.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        String userId = "1";
        SystemLog instance = new SystemLog();
        instance.setUserId(userId);
        String result = instance.getUserId();
        assertEquals(userId,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDated method, of class SystemLog.
     */
    @Test
    public void testGetDated() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date date;
        date = simpleDateFormat.parse("24-12-12");
        System.out.println("getDated");
        SystemLog instance = new SystemLog();
        Date expResult = date;
        instance.setDated(date);
        Date result = instance.getDated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setDated method, of class SystemLog.
     */
    @Test
    public void testSetDated() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date date;
        date = simpleDateFormat.parse("24-12-12");
        System.out.println("setDated");
        Date dated = date;
        SystemLog instance = new SystemLog();
        instance.setDated(dated);
        Date result = instance.getDated();
        assertEquals(dated,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getLogger method, of class SystemLog.
     */
    @Test
    public void testGetLogger() {
        System.out.println("getLogger");
        SystemLog instance = new SystemLog();
        String expResult = "logger";
        instance.setLogger(expResult);
        String result = instance.getLogger();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLogger method, of class SystemLog.
     */
    @Test
    public void testSetLogger() {
        System.out.println("setLogger");
        String logger = "logger";
        SystemLog instance = new SystemLog();
        instance.setLogger(logger);
        String result = instance.getLogger();
        assertEquals(logger,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getLevel method, of class SystemLog.
     */
    @Test
    public void testGetLevel() {
        System.out.println("getLevel");
        SystemLog instance = new SystemLog();
        String expResult = "level";
        instance.setLevel(expResult);
        String result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setLevel method, of class SystemLog.
     */
    @Test
    public void testSetLevel() {
        System.out.println("setLevel");
        String level = "level";
        SystemLog instance = new SystemLog();
        instance.setLevel(level);
        String result = instance.getLevel();
        assertEquals(level,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getMessage method, of class SystemLog.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        SystemLog instance = new SystemLog();
        String expResult = "message";
        instance.setMessage("message");
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setMessage method, of class SystemLog.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "message";
        SystemLog instance = new SystemLog();
        instance.setMessage(message);
        String result = instance.getMessage();
        assertEquals(message,result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of hashCode method, of class SystemLog.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        SystemLog instance = new SystemLog(5);
        int expResult = Integer.hashCode(5);
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of equals method, of class SystemLog.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = new SystemLog(5);
        SystemLog instance = new SystemLog(5);
        boolean expResult = true;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        
        expResult = false;
        assertEquals(expResult, instance.equals(new SystemLog(6)));
    }

    /**
     * Test of toString method, of class SystemLog.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SystemLog instance = new SystemLog(5);
        String expResult = "entities.SystemLog[ logsId=5 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
