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
import entities.Usuario;
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
public class UsuarioFacadeTest {
    @Mock
    private UsuarioFacade usuarioFacade;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    public UsuarioFacadeTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        query = mock(Query.class);
        entityManager = Mockito.mock(EntityManager.class);
        usuarioFacade = Mockito.mock(UsuarioFacade.class);
        usuarioFacade.em = entityManager;
        
    }

    @Test
    public void testUsuarioFacadeFindAll() throws ParseException {
        Usuario a1 = new Usuario();
        Usuario a2 = new Usuario();
        Usuario a3 = new Usuario();
        
        // a1
        a1.setApellido("apellido");
        a1.setId(1);
        a1.setNombre("nombre");
        a1.setRol("rol");
        a1.setUid("uid");
        
        // a2
        a2.setApellido("apellido");
        a2.setId(2);
        a2.setNombre("nombre");
        a2.setRol("rol");
        a2.setUid("uid");
        
        // a3
        a2.setApellido("apellido");
        a2.setId(3);
        a2.setNombre("nombre");
        a2.setRol("rol");
        a2.setUid("uid");
        
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(a3);

        List<Usuario> usuariosTest = new ArrayList<>();

        usuariosTest.add(a1);
        usuariosTest.add(a2);
        usuariosTest.add(a3);

        when(entityManager.createNamedQuery("SELECT a FROM Usuario a")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT a FROM Usuario a");

        when(querys.getResultList()).thenReturn(usuarios);

        when(usuarioFacade.findAll()).thenReturn(usuariosTest);

        assertEquals(usuariosTest, usuarios);

        }
    
    @Test
    public void testUsuarioFacadeFindUsuarioByUid() throws ParseException {
        Usuario a1 = new Usuario();
        Usuario a2 = new Usuario();
        Usuario a3 = new Usuario();
        
        // a1
        a1.setApellido("apellido");
        a1.setId(1);
        a1.setNombre("nombre");
        a1.setRol("rol");
        a1.setUid("uid");
        
        // a2
        a2.setApellido("apellido");
        a2.setId(2);
        a2.setNombre("nombre");
        a2.setRol("rol");
        a2.setUid("uid");
        
        // a3
        a2.setApellido("apellido");
        a2.setId(3);
        a2.setNombre("nombre");
        a2.setRol("rol");
        a2.setUid("uid");
        
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(a3);

        List<Usuario> usuariosTest = new ArrayList<>();

        usuariosTest.add(a1);
        usuariosTest.add(a2);
        usuariosTest.add(a3);

        when(entityManager.createNamedQuery("SELECT p FROM Usuario p WHERE p.uid = :uid")).thenReturn(query);

        Query querys = entityManager.createNamedQuery("SELECT p FROM Usuario p WHERE p.uid = :uid");

        when(querys.getResultList()).thenReturn(usuarios);

        when(usuarioFacade.findUsuarioByUid("uid")).thenReturn(usuariosTest);

        assertEquals(usuariosTest, usuarios);

        }
    
}
