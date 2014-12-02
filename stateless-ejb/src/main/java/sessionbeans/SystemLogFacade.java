/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.SystemLog;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miguel
 */
@Stateless
public class SystemLogFacade extends AbstractFacade<SystemLog> implements SystemLogFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    EntityManager em;
    
    
    public SystemLogFacade() {
        super(SystemLog.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}