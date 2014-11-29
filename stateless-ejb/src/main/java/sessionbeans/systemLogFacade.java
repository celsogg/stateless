/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.systemLog;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miguel
 */
@Stateless
public class systemLogFacade extends AbstractFacade<systemLog> implements systemLogFacadeLocal {
    @PersistenceContext(unitName = "com.stateless_stateless-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public systemLogFacade() {
        super(systemLog.class);
    }
    
}
