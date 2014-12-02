/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Plan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author matias
 */
@Stateless
public class PlanFacade extends AbstractFacade<Plan> implements PlanFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;
    
    
    public PlanFacade() {
        super(Plan.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    

    @Override
    public List<Plan> findByVisiblePlan() {
        Query query;
        query = em.createNamedQuery("Plan.findByVisiblePlan");
        
        return query.getResultList();
        
    }

   
    
}
