/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
import entities.Carrera;
import entities.Perfil;
import entities.Plan;
import entities.Seccion;
import entities.SystemLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import javax.persistence.Query;

/**
 *
 * @author matias
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemLogFacadeTest {
    @Mock
    private SystemLogFacade systemlogFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public SystemLogFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        systemlogFacade = Mockito.mock(SystemLogFacade.class);
        systemlogFacade.em = entityManager;
        
    }

    @Test
    public void testSystemLogFacadeFindAll() throws ParseException {
        SystemLog a1 = new SystemLog();
        SystemLog a2 = new SystemLog();
        SystemLog a3 = new SystemLog();
        
        // a1
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date date;
        date = simpleDateFormat.parse("24-12-12");
        a1.setDated(date);
        a1.setLevel("1");
        a1.setLogger("1");
        a1.setLogsId(1);
        a1.setMessage("mensaje");
        a1.setUserId("userid");

        // a2
        java.util.Date date2;
        date2 = simpleDateFormat.parse("24-12-12");
        a1.setDated(date2);
        a1.setLevel("1");
        a1.setLogger("1");
        a1.setLogsId(2);
        a1.setMessage("mensaje");
        a1.setUserId("userid");
        
        // a3
        java.util.Date date3;
        date3 = simpleDateFormat.parse("24-12-12");
        a1.setDated(date3);
        a1.setLevel("1");
        a1.setLogger("1");
        a1.setLogsId(3);
        a1.setMessage("mensaje");
        a1.setUserId("userid");
        
        List<SystemLog> systemlogs = new ArrayList<>();

        systemlogs.add(a1);
        systemlogs.add(a2);
        systemlogs.add(a3);

        List<SystemLog> systemlogsTest = new ArrayList<>();

        systemlogsTest.add(a1);
        systemlogsTest.add(a2);
        systemlogsTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM SystemLog a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM SystemLog a");

        when(querys.getResultList()).thenReturn(systemlogs);

        when(systemlogFacade.findAll()).thenReturn(systemlogsTest);

        assertEquals(systemlogsTest, systemlogs);

        }
    
}