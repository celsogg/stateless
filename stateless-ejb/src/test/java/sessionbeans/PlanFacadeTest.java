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
public class PlanFacadeTest {
    @Mock
    private PlanFacade planFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public PlanFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        planFacade = Mockito.mock(PlanFacade.class);
        planFacade.em = entityManager;
        
    }

    @Test
    public void testPerfilFacadeFindAll() {
        Plan a1 = new Plan();
        Plan a2 = new Plan();
        Plan a3 = new Plan();
        
        // a1
        a1.setAnioPlan(1);
        List<Asignatura> exp = new ArrayList<>();
        Asignatura at = new Asignatura(1);
        Asignatura bt = new Asignatura(2);
        Asignatura ct = new Asignatura(3);
        exp.add(at);
        exp.add(bt);
        exp.add(ct);
        a1.setAsignaturaCollection(exp);
        a1.setCodigoPlan("101");
        Carrera a = new Carrera(1);
        a1.setIdCarrera(a);
        a1.setIdPlan(1);
        a1.setNombrePlan("nombre");
        a1.setVersionPlan("version");
        a1.setVisiblePlan(true);
            
        // a2
        a2.setAnioPlan(1);
        List<Asignatura> exp2 = new ArrayList<>();
        Asignatura at2 = new Asignatura(1);
        Asignatura bt2 = new Asignatura(2);
        Asignatura ct2 = new Asignatura(3);
        exp2.add(at2);
        exp2.add(bt2);
        exp2.add(ct2);
        a2.setAsignaturaCollection(exp2);
        a2.setCodigoPlan("101");
        Carrera ap = new Carrera(1);
        a2.setIdCarrera(ap);
        a2.setIdPlan(2);
        a2.setNombrePlan("nombre");
        a2.setVersionPlan("version");
        a2.setVisiblePlan(true);
        
        // a3
        
        a3.setAnioPlan(1);
        List<Asignatura> exp3 = new ArrayList<>();
        Asignatura at3 = new Asignatura(1);
        Asignatura bt3 = new Asignatura(2);
        Asignatura ct3 = new Asignatura(3);
        exp3.add(at3);
        exp3.add(bt3);
        exp3.add(ct3);
        a3.setAsignaturaCollection(exp3);
        a3.setCodigoPlan("101");
        Carrera ap2 = new Carrera(1);
        a3.setIdCarrera(ap2);
        a3.setIdPlan(3);
        a3.setNombrePlan("nombre");
        a3.setVersionPlan("version");
        a3.setVisiblePlan(true);
        
        List<Plan> planes = new ArrayList<>();

        planes.add(a1);
        planes.add(a2);
        planes.add(a3);

        List<Plan> planesTest = new ArrayList<>();

        planesTest.add(a1);
        planesTest.add(a2);
        planesTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Perfil a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Perfil a");

        when(querys.getResultList()).thenReturn(planes);

        when(planFacade.findAll()).thenReturn(planesTest);

        assertEquals(planesTest, planes);

        }
    
    @Test
    public void testPerfilFacadeFindByVisiblePlan() {
        Plan a1 = new Plan();
        Plan a2 = new Plan();
        Plan a3 = new Plan();
        
        // a1
        a1.setAnioPlan(1);
        List<Asignatura> exp = new ArrayList<>();
        Asignatura at = new Asignatura(1);
        Asignatura bt = new Asignatura(2);
        Asignatura ct = new Asignatura(3);
        exp.add(at);
        exp.add(bt);
        exp.add(ct);
        a1.setAsignaturaCollection(exp);
        a1.setCodigoPlan("101");
        Carrera a = new Carrera(1);
        a1.setIdCarrera(a);
        a1.setIdPlan(1);
        a1.setNombrePlan("nombre");
        a1.setVersionPlan("version");
        a1.setVisiblePlan(true);
            
        // a2
        a2.setAnioPlan(1);
        List<Asignatura> exp2 = new ArrayList<>();
        Asignatura at2 = new Asignatura(1);
        Asignatura bt2 = new Asignatura(2);
        Asignatura ct2 = new Asignatura(3);
        exp2.add(at2);
        exp2.add(bt2);
        exp2.add(ct2);
        a2.setAsignaturaCollection(exp2);
        a2.setCodigoPlan("101");
        Carrera ap = new Carrera(1);
        a2.setIdCarrera(ap);
        a2.setIdPlan(2);
        a2.setNombrePlan("nombre");
        a2.setVersionPlan("version");
        a2.setVisiblePlan(true);
        
        // a3
        
        a3.setAnioPlan(1);
        List<Asignatura> exp3 = new ArrayList<>();
        Asignatura at3 = new Asignatura(1);
        Asignatura bt3 = new Asignatura(2);
        Asignatura ct3 = new Asignatura(3);
        exp3.add(at3);
        exp3.add(bt3);
        exp3.add(ct3);
        a3.setAsignaturaCollection(exp3);
        a3.setCodigoPlan("101");
        Carrera ap2 = new Carrera(1);
        a3.setIdCarrera(ap2);
        a3.setIdPlan(3);
        a3.setNombrePlan("nombre");
        a3.setVersionPlan("version");
        a3.setVisiblePlan(true);
        
        List<Plan> planes = new ArrayList<>();

        planes.add(a1);
        planes.add(a2);
        planes.add(a3);

        List<Plan> planesTest = new ArrayList<>();

        planesTest.add(a1);
        planesTest.add(a2);
        planesTest.add(a3);

        when(entityManager.createNamedQuery("SELECT P FROM Plan p WHERE p.visiblePlan = 1")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT P FROM Plan p WHERE p.visiblePlan = 1");

        when(querys.getResultList()).thenReturn(planes);

        when(planFacade.findByVisiblePlan()).thenReturn(planesTest);

        assertEquals(planesTest, planes);

        }
    
}