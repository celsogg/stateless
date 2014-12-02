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
public class CarreraFacadeTest {
    @Mock
    private CarreraFacade carreraFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public CarreraFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        carreraFacade = Mockito.mock(CarreraFacade.class);
        carreraFacade.em = entityManager;
        
    }

    @Test
    public void testCarreraFacadeFindAll() {
        Carrera a1 = new Carrera();
        Carrera a2 = new Carrera();
        Carrera a3 = new Carrera();
        
        // a1
        a1.setIdCarrera(1);
        a1.setNombreCarrera("carrera");
        
        
        List<Perfil> expResult = new ArrayList<>();
        Perfil a = new Perfil(1);
        Perfil b = new Perfil(2);
        Perfil c = new Perfil(3);
        
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        a1.setPerfilCollection(expResult);
        
    
        Plan at = new Plan(1);
        Plan bt = new Plan(2);
        Plan ct = new Plan(3);

        List<Plan> exp = new ArrayList<>();
        exp.add(at);
        exp.add(bt);
        exp.add(ct);
        a1.setPlanCollection(exp);
        
        // a2
        a2.setIdCarrera(2);
        a2.setNombreCarrera("carrera");
        
        
        List<Perfil> expResult1 = new ArrayList<>();
        Perfil att = new Perfil(1);
        Perfil btt = new Perfil(2);
        Perfil ctt = new Perfil(3);
        
        expResult1.add(att);
        expResult1.add(btt);
        expResult1.add(ctt);
        a2.setPerfilCollection(expResult1);
        
    
        Plan att1 = new Plan(1);
        Plan btt1 = new Plan(2);
        Plan ctt1 = new Plan(3);

        List<Plan> exp1 = new ArrayList<>();
        exp1.add(att1);
        exp1.add(btt1);
        exp1.add(ctt1);
        a2.setPlanCollection(exp1);
        
        // a3
        a3.setIdCarrera(3);
        a3.setNombreCarrera("carrera");
        
        
        List<Perfil> expResult2 = new ArrayList<>();
        Perfil att2 = new Perfil(1);
        Perfil btt2 = new Perfil(2);
        Perfil ctt2 = new Perfil(3);
        
        expResult2.add(att2);
        expResult2.add(btt2);
        expResult2.add(ctt2);
        a3.setPerfilCollection(expResult2);
        
    
        Plan at3 = new Plan(1);
        Plan bt3 = new Plan(2);
        Plan ct3 = new Plan(3);

        List<Plan> exp3 = new ArrayList<>();
        exp3.add(at3);
        exp3.add(bt3);
        exp3.add(ct3);
        a3.setPlanCollection(exp3);
        

        List<Carrera> carreras = new ArrayList<>();

        carreras.add(a1);
        carreras.add(a2);
        carreras.add(a3);

        List<Carrera> carrerasTest = new ArrayList<>();

        carrerasTest.add(a1);
        carrerasTest.add(a2);
        carrerasTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Carrera a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Carrera a");

        when(querys.getResultList()).thenReturn(carreras);

        when(carreraFacade.findAll()).thenReturn(carrerasTest);

        assertEquals(carrerasTest, carreras);

        }
    
}