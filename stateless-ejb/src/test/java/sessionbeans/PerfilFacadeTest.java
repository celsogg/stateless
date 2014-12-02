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
public class PerfilFacadeTest {
    @Mock
    private PerfilFacade perfilFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public PerfilFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        perfilFacade = Mockito.mock(PerfilFacade.class);
        perfilFacade.em = entityManager;
        
    }

    @Test
    public void testPerfilFacadeFindAll() {
        Perfil a1 = new Perfil();
        Perfil a2 = new Perfil();
        Perfil a3 = new Perfil();
        
        // a1
        a1.setDescripcionPerfil("descripcion");
        Carrera a = new Carrera(1);
        a1.setIdCarrera(a);
        a1.setIdPerfil(1);
        a1.setNombrePerfil("nombre");
        List<Seccion> expResult = new ArrayList<>();
        Seccion at1 = new Seccion(1);
        Seccion bt1 = new Seccion(2);
        Seccion ct1 = new Seccion(3);
        expResult.add(at1);
        expResult.add(bt1);
        expResult.add(ct1);
        a1.setSeccionCollection(expResult);
        
        // a2
        a2.setDescripcionPerfil("descripcion");
        Carrera ap = new Carrera(1);
        a2.setIdCarrera(ap);
        a2.setIdPerfil(1);
        a2.setNombrePerfil("nombre");
        List<Seccion> expResult2 = new ArrayList<>();
        Seccion ata = new Seccion(1);
        Seccion bta = new Seccion(2);
        Seccion cta = new Seccion(3);
        expResult2.add(ata);
        expResult2.add(bta);
        expResult2.add(cta);
        a2.setSeccionCollection(expResult2);
        
        // a3
        a3.setDescripcionPerfil("descripcion");
        Carrera ap1 = new Carrera(1);
        a3.setIdCarrera(ap1);
        a3.setIdPerfil(1);
        a3.setNombrePerfil("nombre");
        List<Seccion> expResult3 = new ArrayList<>();
        Seccion at2 = new Seccion(1);
        Seccion bt2 = new Seccion(2);
        Seccion ct2 = new Seccion(3);
        expResult3.add(at2);
        expResult3.add(bt2);
        expResult3.add(ct2);
        a3.setSeccionCollection(expResult3);
        
        List<Perfil> perfiles = new ArrayList<>();

        perfiles.add(a1);
        perfiles.add(a2);
        perfiles.add(a3);

        List<Perfil> perfilesTest = new ArrayList<>();

        perfilesTest.add(a1);
        perfilesTest.add(a2);
        perfilesTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Perfil a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Perfil a");

        when(querys.getResultList()).thenReturn(perfiles);

        when(perfilFacade.findAll()).thenReturn(perfilesTest);

        assertEquals(perfilesTest, perfiles);

        }
    
}