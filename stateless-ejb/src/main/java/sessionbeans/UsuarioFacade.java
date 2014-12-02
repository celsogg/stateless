/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;


import entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author miguel
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;
    
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
    /**
     *
     * @param uid
     * @return
     */
    @Override
    public List<Usuario> findUsuarioByUid(String uid){
        Query query;
        query = em.createNamedQuery("Usuario.findByUid")
                .setParameter("uid",uid);
        return query.getResultList();
        
    }
    

    
}
