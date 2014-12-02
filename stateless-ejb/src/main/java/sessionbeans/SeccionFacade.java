/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Seccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matias
 */
@Stateless
public class SeccionFacade extends AbstractFacade<Seccion> implements SeccionFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;
    
    
    public SeccionFacade() {
        super(Seccion.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}
