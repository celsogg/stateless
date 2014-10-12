/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Nombres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author matias
 */
@Local
public interface NombresFacadeLocal {

    void create(Nombres nombres);

    void edit(Nombres nombres);

    void remove(Nombres nombres);

    Nombres find(Object id);

    List<Nombres> findAll();

    List<Nombres> findRange(int[] range);

    int count();
    
}
