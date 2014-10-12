/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Habilidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author matias
 */
@Local
public interface HabilidadFacadeLocal {

    void create(Habilidad habilidad);

    void edit(Habilidad habilidad);

    void remove(Habilidad habilidad);

    Habilidad find(Object id);

    List<Habilidad> findAll();

    List<Habilidad> findRange(int[] range);

    int count();
    
}
