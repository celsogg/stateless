package managedbeans;

import entities.Asignatura;
import entities.Plan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Celso
 */
@Named("convalidacionesCtrl")
@SessionScoped
public class ConvalidacionesController implements Serializable {
    @Inject
    PlanController planCtrl;
    
    @Inject
    AsignaturaController asigCtrl;
    
    Plan planConvalidante = null;
    Plan planConvalidado  = null;
    
    List <Plan> posiblesConvalidados = null;
    
    List <Asignatura> asignaturasConvalidante = null;
    Asignatura asignaturaConvalidanteSelected = null;
    Asignatura asigConvalidadaSelected = null;

    public Plan getPlanConvalidante() {
        return planConvalidante;
    }

    public void setPlanConvalidante(Plan planConvalidante) {
        //System.out.println("setpc");
        this.planConvalidante = planConvalidante;
        this.posiblesConvalidados = planCtrl.getItemsAvailableSelectOne();
        this.posiblesConvalidados.remove(planConvalidante);
        updateTable();
    }

    public Plan getPlanConvalidado() {
        return planConvalidado;
    }

    public void setPlanConvalidado(Plan planConvalidado) {
        //System.out.println("setpcd");
        this.planConvalidado = planConvalidado;
        updateTable();
    }

    public List<Plan> getPosiblesConvalidados() {
        return posiblesConvalidados;
    }

    public void setPosiblesConvalidados(List<Plan> posiblesConvalidados) {
        this.posiblesConvalidados = posiblesConvalidados;
    }
    
    public List<Asignatura> getAsignaturasConvalidante() {
        if ( planConvalidante != null ){
            asignaturasConvalidante = asigCtrl.getAsignaturasPlan( planConvalidante );
        } else {
            asignaturasConvalidante = null;
        }
        return asignaturasConvalidante;
    }
    
    public Asignatura getAsignaturaConvalidanteSelected() {
        return asignaturaConvalidanteSelected;
    }

    public void setAsignaturaConvalidanteSelected(Asignatura asignaturaConvalidanteSelected) {
        //System.out.println("setasig");
        this.asignaturaConvalidanteSelected = asignaturaConvalidanteSelected;
    }

    public Asignatura getAsigConvalidadaSelected() {
        return asigConvalidadaSelected;
    }

    public void setAsigConvalidadaSelected(Asignatura asigConvalidadaSelected) {
        this.asigConvalidadaSelected = asigConvalidadaSelected;
    }
    
    public void update() {
        if (asigConvalidadaSelected != null){
            List <Asignatura> convalidaciones = (List <Asignatura>) asignaturaConvalidanteSelected.getConvalidaciones();
            convalidaciones.add(asigConvalidadaSelected);
            asignaturaConvalidanteSelected.setConvalidaciones(convalidaciones);
            asigCtrl.setSelected(asignaturaConvalidanteSelected);
            asigCtrl.update();
            updateTable();
        }
    }
    
    public void noConvalida(){
        List <Asignatura> convalidaciones = (List <Asignatura>) asignaturaConvalidanteSelected.getConvalidaciones();
        List <Asignatura> toRemove = new ArrayList();
        for (Asignatura conv : convalidaciones) {
            if (conv.getIdPlan().equals(planConvalidado))
                toRemove.add(conv);
        }
        convalidaciones.removeAll(toRemove);
        asignaturaConvalidanteSelected.setConvalidaciones(convalidaciones);
        asigCtrl.setSelected(asignaturaConvalidanteSelected);
        asigCtrl.update();
        updateTable();
    }
    
    public void updateTable() {
        //System.out.println("alo");
        getAsignaturasConvalidante();
        RequestContext.getCurrentInstance().update("datalist");
    }
    
    public String getConvalidacionesString(Asignatura asig){
        //System.out.println("Striing");
        String conv = "";
        for (Asignatura c : asig.getConvalidaciones()) {
            if (c.getIdPlan().equals(planConvalidado))
                conv += c.getNombreAsignatura() + "\n";
        }
        return conv;
    }
}
