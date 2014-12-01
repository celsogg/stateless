/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Asignatura;
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
public class AsignaturaFacade extends AbstractFacade<Asignatura> implements AsignaturaFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public AsignaturaFacade() {
        super(Asignatura.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
    @Override
    public List<Asignatura> findAsignaturas(int id) {
        Query query;
        query = em.createNamedQuery("Asignatura.findByIdAsignatura")
                .setParameter("idAsignatura",id);
        
        return query.getResultList();
    }

    public List<Asignatura> findAsignaturasByPlan(Plan p) {
        Query query;
        query = em.createNamedQuery("Asignatura.findByPlanId")
                .setParameter("planId", p);
        
        return query.getResultList();
    }
    
    
    
}
