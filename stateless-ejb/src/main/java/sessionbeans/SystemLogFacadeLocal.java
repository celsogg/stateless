/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.SystemLog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miguel
 */
@Local
public interface SystemLogFacadeLocal {

    void create(SystemLog systemLog);

    void edit(SystemLog systemLog);

    void remove(SystemLog systemLog);

    SystemLog find(Object id);

    List<SystemLog> findAll();

    List<SystemLog> findRange(int[] range);

    int count();
    
}