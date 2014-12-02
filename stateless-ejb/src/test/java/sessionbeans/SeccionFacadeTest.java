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
public class SeccionFacadeTest {
    @Mock
    private SeccionFacade seccionFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public SeccionFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        seccionFacade = Mockito.mock(SeccionFacade.class);
        seccionFacade.em = entityManager;
        
    }

    @Test
    public void testSeccionFacadeFindAll() {
        Seccion a1 = new Seccion();
        Seccion a2 = new Seccion();
        Seccion a3 = new Seccion();
        
        // a1
        a1.setDescripcionSeccion("descripcion");
        Perfil a = new Perfil(1);
        a1.setIdPerfil(a);
        a1.setIdSeccion(1);
        a1.setNombreSeccion("nombre");
        
        // a2
        a2.setDescripcionSeccion("descripcion");
        Perfil ap = new Perfil(1);
        a2.setIdPerfil(ap);
        a2.setIdSeccion(2);
        a2.setNombreSeccion("nombre");
        
        // a3
        a2.setDescripcionSeccion("descripcion");
        Perfil ap2 = new Perfil(1);
        a2.setIdPerfil(ap2);
        a2.setIdSeccion(2);
        a2.setNombreSeccion("nombre");
        
        List<Seccion> secciones = new ArrayList<>();

        secciones.add(a1);
        secciones.add(a2);
        secciones.add(a3);

        List<Seccion> seccionesTest = new ArrayList<>();

        seccionesTest.add(a1);
        seccionesTest.add(a2);
        seccionesTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Seccion a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Seccion a");

        when(querys.getResultList()).thenReturn(secciones);

        when(seccionFacade.findAll()).thenReturn(seccionesTest);

        assertEquals(seccionesTest, secciones);

        }
    
}