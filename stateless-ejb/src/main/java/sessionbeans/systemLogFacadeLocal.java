/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.systemLog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miguel
 */
@Local
public interface systemLogFacadeLocal {

    void create(systemLog systemLog);

    void edit(systemLog systemLog);

    void remove(systemLog systemLog);

    systemLog find(Object id);

    List<systemLog> findAll();

    List<systemLog> findRange(int[] range);

    int count();
    
}
