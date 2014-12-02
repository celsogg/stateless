/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
import entities.Plan;
import java.util.ArrayList;
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
public class AsignaturaFacadeTest {
    @Mock
    private AsignaturaFacade asignaturaFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public AsignaturaFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        asignaturaFacade = Mockito.mock(AsignaturaFacade.class);
        asignaturaFacade.em = entityManager;
        
    }

    @Test
    public void testAsignaturaFacadeFindAll() {
        Asignatura a1 = new Asignatura();
        Asignatura a2 = new Asignatura();
        Asignatura a3 = new Asignatura();
        
        // a1
        a1.setSctAsignatura(1);
        a1.setResumenAsignatura("resumen");
        a1.setNombreAsignatura("nombre");
        a1.setNivelAsignatura(1);
        Plan d1 = new Plan(1);
        a1.setIdPlan(d1);
        a1.setIdAsignatura(1);
        a1.setHorasTeoria(1);
        a1.setHorasLaboratorio(1);
        a1.setHorasEjercicio(1);
        a1.setEsAnual(true);
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        a1.setConvalidadaPor(expResult);
        List<Asignatura> expResult1 = new ArrayList<>();
        Asignatura d = new Asignatura(1);
        Asignatura e = new Asignatura(2);
        Asignatura f = new Asignatura(3);
        expResult1.add(d);
        expResult1.add(e);
        expResult1.add(f);        
        a1.setConvalidaciones(expResult1);
        a1.setCodigoAsignatura("10000");
        List<Asignatura> expResult2 = new ArrayList<>();
        Asignatura g = new Asignatura(1);
        Asignatura h = new Asignatura(2);
        Asignatura i = new Asignatura(3);
        expResult2.add(g);
        expResult2.add(h);
        expResult2.add(i); 
        a1.setAsignaturaCollection1(expResult2);
        List<Asignatura> expResult3 = new ArrayList<>();
        Asignatura j = new Asignatura(1);
        Asignatura k = new Asignatura(2);
        Asignatura l = new Asignatura(3);
        expResult3.add(j);
        expResult3.add(k);
        expResult3.add(l); 
        a1.setAsignaturaCollection(expResult3);
        
        // a2
        a2.setSctAsignatura(1);
        a2.setResumenAsignatura("resumen");
        a2.setNombreAsignatura("nombre");
        a2.setNivelAsignatura(1);
        Plan d2 = new Plan(2);
        a2.setIdPlan(d2);
        a2.setIdAsignatura(1);
        a2.setHorasTeoria(1);
        a2.setHorasLaboratorio(1);
        a2.setHorasEjercicio(1);
        a2.setEsAnual(true);
        List<Asignatura> expResultt1 = new ArrayList<>();
        Asignatura at = new Asignatura(1);
        Asignatura bt = new Asignatura(2);
        Asignatura ct = new Asignatura(3);
        expResultt1.add(at);
        expResultt1.add(bt);
        expResultt1.add(ct);
        a2.setConvalidadaPor(expResultt1);
        List<Asignatura> expResult1t1 = new ArrayList<>();
        Asignatura dt = new Asignatura(1);
        Asignatura et = new Asignatura(2);
        Asignatura ft = new Asignatura(3);
        expResult1t1.add(dt);
        expResult1t1.add(et);
        expResult1t1.add(ft);        
        a2.setConvalidaciones(expResult1t1);
        a2.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t1 = new ArrayList<>();
        Asignatura gt = new Asignatura(1);
        Asignatura ht = new Asignatura(2);
        Asignatura it = new Asignatura(3);
        expResult2t1.add(gt);
        expResult2t1.add(ht);
        expResult2t1.add(it); 
        a2.setAsignaturaCollection1(expResult2t1);
        List<Asignatura> expResult3t1 = new ArrayList<>();
        Asignatura jt = new Asignatura(1);
        Asignatura kt = new Asignatura(2);
        Asignatura lt = new Asignatura(3);
        expResult3t1.add(jt);
        expResult3t1.add(kt);
        expResult3t1.add(lt); 
        a2.setAsignaturaCollection(expResult3t1);
        
        // a3
        a3.setSctAsignatura(1);
        a3.setResumenAsignatura("resumen");
        a3.setNombreAsignatura("nombre");
        a3.setNivelAsignatura(1);
        Plan d3 = new Plan(3);
        a3.setIdPlan(d3);
        a3.setIdAsignatura(1);
        a3.setHorasTeoria(1);
        a3.setHorasLaboratorio(1);
        a3.setHorasEjercicio(1);
        a3.setEsAnual(true);
        List<Asignatura> expResultt2 = new ArrayList<>();
        Asignatura att = new Asignatura(1);
        Asignatura btt = new Asignatura(2);
        Asignatura ctt = new Asignatura(3);
        expResultt2.add(att);
        expResultt2.add(btt);
        expResultt2.add(ctt);
        a3.setConvalidadaPor(expResultt2);
        List<Asignatura> expResult1t2 = new ArrayList<>();
        Asignatura dtt = new Asignatura(1);
        Asignatura ett = new Asignatura(2);
        Asignatura ftt = new Asignatura(3);
        expResult1t2.add(dtt);
        expResult1t2.add(ett);
        expResult1t2.add(ftt);        
        a3.setConvalidaciones(expResult1t2);
        a3.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t2 = new ArrayList<>();
        Asignatura gtt = new Asignatura(1);
        Asignatura htt = new Asignatura(2);
        Asignatura itt = new Asignatura(3);
        expResult2t2.add(gtt);
        expResult2t2.add(htt);
        expResult2t2.add(itt); 
        a3.setAsignaturaCollection1(expResult2t2);
        List<Asignatura> expResult3t2 = new ArrayList<>();
        Asignatura jtt = new Asignatura(1);
        Asignatura ktt = new Asignatura(2);
        Asignatura ltt = new Asignatura(3);
        expResult3t2.add(jtt);
        expResult3t2.add(ktt);
        expResult3t2.add(ltt); 
        a3.setAsignaturaCollection(expResult3t2);

        List<Asignatura> asignaturas = new ArrayList<>();

        asignaturas.add(a1);
        asignaturas.add(a2);
        asignaturas.add(a3);

        List<Asignatura> asignaturasTest = new ArrayList<>();

        asignaturasTest.add(a1);
        asignaturasTest.add(a2);
        asignaturasTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Asignatura a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Asignatura a");

        when(querys.getResultList()).thenReturn(asignaturas);

        when(asignaturaFacade.findAll()).thenReturn(asignaturasTest);

        assertEquals(asignaturasTest, asignaturas);

        }
    
    @Test
    public void testAsignaturaFacadeFindById() {
        Asignatura a1 = new Asignatura();
        Asignatura a2 = new Asignatura();
        Asignatura a3 = new Asignatura();
        
        // a1
        a1.setSctAsignatura(1);
        a1.setResumenAsignatura("resumen");
        a1.setNombreAsignatura("nombre");
        a1.setNivelAsignatura(1);
        Plan d1 = new Plan(1);
        a1.setIdPlan(d1);
        a1.setIdAsignatura(1);
        a1.setHorasTeoria(1);
        a1.setHorasLaboratorio(1);
        a1.setHorasEjercicio(1);
        a1.setEsAnual(true);
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        a1.setConvalidadaPor(expResult);
        List<Asignatura> expResult1 = new ArrayList<>();
        Asignatura d = new Asignatura(1);
        Asignatura e = new Asignatura(2);
        Asignatura f = new Asignatura(3);
        expResult1.add(d);
        expResult1.add(e);
        expResult1.add(f);        
        a1.setConvalidaciones(expResult1);
        a1.setCodigoAsignatura("10000");
        List<Asignatura> expResult2 = new ArrayList<>();
        Asignatura g = new Asignatura(1);
        Asignatura h = new Asignatura(2);
        Asignatura i = new Asignatura(3);
        expResult2.add(g);
        expResult2.add(h);
        expResult2.add(i); 
        a1.setAsignaturaCollection1(expResult2);
        List<Asignatura> expResult3 = new ArrayList<>();
        Asignatura j = new Asignatura(1);
        Asignatura k = new Asignatura(2);
        Asignatura l = new Asignatura(3);
        expResult3.add(j);
        expResult3.add(k);
        expResult3.add(l); 
        a1.setAsignaturaCollection(expResult3);
        
        // a2
        a2.setSctAsignatura(1);
        a2.setResumenAsignatura("resumen");
        a2.setNombreAsignatura("nombre");
        a2.setNivelAsignatura(1);
        Plan d2 = new Plan(2);
        a2.setIdPlan(d2);
        a2.setIdAsignatura(1);
        a2.setHorasTeoria(1);
        a2.setHorasLaboratorio(1);
        a2.setHorasEjercicio(1);
        a2.setEsAnual(true);
        List<Asignatura> expResultt1 = new ArrayList<>();
        Asignatura at = new Asignatura(1);
        Asignatura bt = new Asignatura(2);
        Asignatura ct = new Asignatura(3);
        expResultt1.add(at);
        expResultt1.add(bt);
        expResultt1.add(ct);
        a2.setConvalidadaPor(expResultt1);
        List<Asignatura> expResult1t1 = new ArrayList<>();
        Asignatura dt = new Asignatura(1);
        Asignatura et = new Asignatura(2);
        Asignatura ft = new Asignatura(3);
        expResult1t1.add(dt);
        expResult1t1.add(et);
        expResult1t1.add(ft);        
        a2.setConvalidaciones(expResult1t1);
        a2.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t1 = new ArrayList<>();
        Asignatura gt = new Asignatura(1);
        Asignatura ht = new Asignatura(2);
        Asignatura it = new Asignatura(3);
        expResult2t1.add(gt);
        expResult2t1.add(ht);
        expResult2t1.add(it); 
        a2.setAsignaturaCollection1(expResult2t1);
        List<Asignatura> expResult3t1 = new ArrayList<>();
        Asignatura jt = new Asignatura(1);
        Asignatura kt = new Asignatura(2);
        Asignatura lt = new Asignatura(3);
        expResult3t1.add(jt);
        expResult3t1.add(kt);
        expResult3t1.add(lt); 
        a2.setAsignaturaCollection(expResult3t1);
        
        // a3
        a3.setSctAsignatura(1);
        a3.setResumenAsignatura("resumen");
        a3.setNombreAsignatura("nombre");
        a3.setNivelAsignatura(1);
        Plan d3 = new Plan(3);
        a3.setIdPlan(d2);
        a3.setIdAsignatura(1);
        a3.setHorasTeoria(1);
        a3.setHorasLaboratorio(1);
        a3.setHorasEjercicio(1);
        a3.setEsAnual(true);
        List<Asignatura> expResultt2 = new ArrayList<>();
        Asignatura att = new Asignatura(1);
        Asignatura btt = new Asignatura(2);
        Asignatura ctt = new Asignatura(3);
        expResultt2.add(att);
        expResultt2.add(btt);
        expResultt2.add(ctt);
        a3.setConvalidadaPor(expResultt2);
        List<Asignatura> expResult1t2 = new ArrayList<>();
        Asignatura dtt = new Asignatura(1);
        Asignatura ett = new Asignatura(2);
        Asignatura ftt = new Asignatura(3);
        expResult1t2.add(dtt);
        expResult1t2.add(ett);
        expResult1t2.add(ftt);        
        a3.setConvalidaciones(expResult1t2);
        a3.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t2 = new ArrayList<>();
        Asignatura gtt = new Asignatura(1);
        Asignatura htt = new Asignatura(2);
        Asignatura itt = new Asignatura(3);
        expResult2t2.add(gtt);
        expResult2t2.add(htt);
        expResult2t2.add(itt); 
        a3.setAsignaturaCollection1(expResult2t2);
        List<Asignatura> expResult3t2 = new ArrayList<>();
        Asignatura jtt = new Asignatura(1);
        Asignatura ktt = new Asignatura(2);
        Asignatura ltt = new Asignatura(3);
        expResult3t2.add(jtt);
        expResult3t2.add(ktt);
        expResult3t2.add(ltt); 
        a3.setAsignaturaCollection(expResult3t2);

        List<Asignatura> asignaturas = new ArrayList<>();

        asignaturas.add(a1);
        asignaturas.add(a2);
        asignaturas.add(a3);

        List<Asignatura> asignaturasTest = new ArrayList<>();

        asignaturasTest.add(a1);
        asignaturasTest.add(a2);
        asignaturasTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura");

        when(querys.getResultList()).thenReturn(asignaturas);

        when(asignaturaFacade.findAsignaturas(asignaturas.get(0).getIdAsignatura())).thenReturn(asignaturasTest);

        assertEquals(asignaturasTest, asignaturas);

        }
    
    @Test
    public void testAsignaturaFacadeFindAsignaturaByPlan() {
        Asignatura a1 = new Asignatura();
        Asignatura a2 = new Asignatura();
        Asignatura a3 = new Asignatura();
        
        // a1
        a1.setSctAsignatura(1);
        a1.setResumenAsignatura("resumen");
        a1.setNombreAsignatura("nombre");
        a1.setNivelAsignatura(1);
        Plan d1 = new Plan(1);
        a1.setIdPlan(d1);
        a1.setIdAsignatura(1);
        a1.setHorasTeoria(1);
        a1.setHorasLaboratorio(1);
        a1.setHorasEjercicio(1);
        a1.setEsAnual(true);
        List<Asignatura> expResult = new ArrayList<>();
        Asignatura a = new Asignatura(1);
        Asignatura b = new Asignatura(2);
        Asignatura c = new Asignatura(3);
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        a1.setConvalidadaPor(expResult);
        List<Asignatura> expResult1 = new ArrayList<>();
        Asignatura d = new Asignatura(1);
        Asignatura e = new Asignatura(2);
        Asignatura f = new Asignatura(3);
        expResult1.add(d);
        expResult1.add(e);
        expResult1.add(f);        
        a1.setConvalidaciones(expResult1);
        a1.setCodigoAsignatura("10000");
        List<Asignatura> expResult2 = new ArrayList<>();
        Asignatura g = new Asignatura(1);
        Asignatura h = new Asignatura(2);
        Asignatura i = new Asignatura(3);
        expResult2.add(g);
        expResult2.add(h);
        expResult2.add(i); 
        a1.setAsignaturaCollection1(expResult2);
        List<Asignatura> expResult3 = new ArrayList<>();
        Asignatura j = new Asignatura(1);
        Asignatura k = new Asignatura(2);
        Asignatura l = new Asignatura(3);
        expResult3.add(j);
        expResult3.add(k);
        expResult3.add(l); 
        a1.setAsignaturaCollection(expResult3);
        
        // a2
        a2.setSctAsignatura(1);
        a2.setResumenAsignatura("resumen");
        a2.setNombreAsignatura("nombre");
        a2.setNivelAsignatura(1);
        Plan d2 = new Plan(2);
        a2.setIdPlan(d2);
        a2.setIdAsignatura(1);
        a2.setHorasTeoria(1);
        a2.setHorasLaboratorio(1);
        a2.setHorasEjercicio(1);
        a2.setEsAnual(true);
        List<Asignatura> expResultt1 = new ArrayList<>();
        Asignatura at = new Asignatura(1);
        Asignatura bt = new Asignatura(2);
        Asignatura ct = new Asignatura(3);
        expResultt1.add(at);
        expResultt1.add(bt);
        expResultt1.add(ct);
        a2.setConvalidadaPor(expResultt1);
        List<Asignatura> expResult1t1 = new ArrayList<>();
        Asignatura dt = new Asignatura(1);
        Asignatura et = new Asignatura(2);
        Asignatura ft = new Asignatura(3);
        expResult1t1.add(dt);
        expResult1t1.add(et);
        expResult1t1.add(ft);        
        a2.setConvalidaciones(expResult1t1);
        a2.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t1 = new ArrayList<>();
        Asignatura gt = new Asignatura(1);
        Asignatura ht = new Asignatura(2);
        Asignatura it = new Asignatura(3);
        expResult2t1.add(gt);
        expResult2t1.add(ht);
        expResult2t1.add(it); 
        a2.setAsignaturaCollection1(expResult2t1);
        List<Asignatura> expResult3t1 = new ArrayList<>();
        Asignatura jt = new Asignatura(1);
        Asignatura kt = new Asignatura(2);
        Asignatura lt = new Asignatura(3);
        expResult3t1.add(jt);
        expResult3t1.add(kt);
        expResult3t1.add(lt); 
        a2.setAsignaturaCollection(expResult3t1);
        
        // a3
        a3.setSctAsignatura(1);
        a3.setResumenAsignatura("resumen");
        a3.setNombreAsignatura("nombre");
        a3.setNivelAsignatura(1);
        Plan d3 = new Plan(3);
        a3.setIdPlan(d2);
        a3.setIdAsignatura(1);
        a3.setHorasTeoria(1);
        a3.setHorasLaboratorio(1);
        a3.setHorasEjercicio(1);
        a3.setEsAnual(true);
        List<Asignatura> expResultt2 = new ArrayList<>();
        Asignatura att = new Asignatura(1);
        Asignatura btt = new Asignatura(2);
        Asignatura ctt = new Asignatura(3);
        expResultt2.add(att);
        expResultt2.add(btt);
        expResultt2.add(ctt);
        a3.setConvalidadaPor(expResultt2);
        List<Asignatura> expResult1t2 = new ArrayList<>();
        Asignatura dtt = new Asignatura(1);
        Asignatura ett = new Asignatura(2);
        Asignatura ftt = new Asignatura(3);
        expResult1t2.add(dtt);
        expResult1t2.add(ett);
        expResult1t2.add(ftt);        
        a3.setConvalidaciones(expResult1t2);
        a3.setCodigoAsignatura("10000");
        List<Asignatura> expResult2t2 = new ArrayList<>();
        Asignatura gtt = new Asignatura(1);
        Asignatura htt = new Asignatura(2);
        Asignatura itt = new Asignatura(3);
        expResult2t2.add(gtt);
        expResult2t2.add(htt);
        expResult2t2.add(itt); 
        a3.setAsignaturaCollection1(expResult2t2);
        List<Asignatura> expResult3t2 = new ArrayList<>();
        Asignatura jtt = new Asignatura(1);
        Asignatura ktt = new Asignatura(2);
        Asignatura ltt = new Asignatura(3);
        expResult3t2.add(jtt);
        expResult3t2.add(ktt);
        expResult3t2.add(ltt); 
        a3.setAsignaturaCollection(expResult3t2);

        List<Asignatura> asignaturas = new ArrayList<>();

        asignaturas.add(a1);
        asignaturas.add(a2);
        asignaturas.add(a3);

        List<Asignatura> asignaturasTest = new ArrayList<>();

        asignaturasTest.add(a1);
        asignaturasTest.add(a2);
        asignaturasTest.add(a3);
        
        Plan plan = new Plan();
        plan.setAsignaturaCollection(asignaturas);

        Plan planTest = new Plan();
        planTest.setAsignaturaCollection(asignaturasTest);

        when(entityManager.createNamedQuery("SELECT a FROM Asignatura a WHERE a.idPlan = :planId")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Asignatura a WHERE a.idPlan = :planId");

        when(querys.getResultList()).thenReturn(asignaturas);
        when(asignaturaFacade.findAsignaturasByPlan(plan)).thenReturn(asignaturasTest);

        assertEquals(asignaturasTest, asignaturas);

        }
}