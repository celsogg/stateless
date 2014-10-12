/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.ProgramaAsignatura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author matias
 */
@Local
public interface ProgramaAsignaturaFacadeLocal {

    void create(ProgramaAsignatura programaAsignatura);

    void edit(ProgramaAsignatura programaAsignatura);

    void remove(ProgramaAsignatura programaAsignatura);

    ProgramaAsignatura find(Object id);

    List<ProgramaAsignatura> findAll();

    List<ProgramaAsignatura> findRange(int[] range);

    int count();
    
}
