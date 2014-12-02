package managedbeans;

import entities.Asignatura;
import entities.Plan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

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
    
    List <Plan> posiblesConvalidantes = null;
    
    List <Asignatura> asignaturasConvalidado = null;
    Asignatura asignaturaConvalidanteSelected = null;
    Asignatura asigConvalidadaSelected = null;
    
    private DualListModel<Asignatura> dlAsignaturas;

    public Plan getPlanConvalidante() {
        return planConvalidante;
    }

    public void setPlanConvalidante(Plan planConvalidante) {
        //System.out.println("setpc");
        this.planConvalidante = planConvalidante;
        updateTable();
    }

    public Plan getPlanConvalidado() {
        return planConvalidado;
    }

    public void setPlanConvalidado(Plan planConvalidado) {
        //System.out.println("setpcd");
        this.planConvalidado = planConvalidado;
        this.posiblesConvalidantes = planCtrl.getItemsAvailableSelectOne();
        this.posiblesConvalidantes.remove(planConvalidado);
        updateTable();
    }

    public List<Plan> getPosiblesConvalidados() {
        return posiblesConvalidantes;
    }

    public void setPosiblesConvalidados(List<Plan> posiblesConvalidados) {
        this.posiblesConvalidantes = posiblesConvalidados;
    }
    
    public List<Asignatura> getAsignaturasConvalidado() {
        if ( planConvalidado != null ){
            asignaturasConvalidado = asigCtrl.getAsignaturasPlan( planConvalidado );
        } else {
            asignaturasConvalidado = null;
        }
        return asignaturasConvalidado;
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
        if (asigConvalidadaSelected != null){
            List<Asignatura> asignaturasActuales = (List<Asignatura>) asigConvalidadaSelected.getConvalidadaPor();
            List<Asignatura> toRemove = new ArrayList();
            for (Asignatura a : asignaturasActuales) {
                if (!a.getIdPlan().equals(planConvalidante)){
                    toRemove.add(a);
                }
            }
            asignaturasActuales.removeAll(toRemove);
            List<Asignatura> asignaturasPosibles;
            if (planConvalidante != null)
                asignaturasPosibles = planConvalidante.getAsignaturaCollection();
            else
                asignaturasPosibles = new ArrayList();
            asignaturasPosibles.removeAll(asignaturasActuales);
            dlAsignaturas = new DualListModel<>(asignaturasPosibles, asignaturasActuales); 
        }
    }
    
    public DualListModel<Asignatura> getDLAsignaturas() {
        return dlAsignaturas;
    }

    public void setDLAsignaturas(DualListModel<Asignatura> dlAsignaturas) {
        this.dlAsignaturas = dlAsignaturas;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Asignatura) item).getNombreAsignatura()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
    
    public void update2() {
        List <Asignatura> convalidadaPor = (List <Asignatura>) asigConvalidadaSelected.getConvalidadaPor();
        List <Asignatura> toRemove = new ArrayList();
        for (Asignatura ca : convalidadaPor) {
            if ( ca.getIdPlan().equals(planConvalidante) )
                toRemove.add(ca);
        }
        convalidadaPor.removeAll(toRemove);
        
        convalidadaPor.addAll(dlAsignaturas.getTarget());
        asigConvalidadaSelected.setConvalidadaPor(convalidadaPor);
        asigCtrl.setSelected(asigConvalidadaSelected);
        asigCtrl.update();
        
        for (Asignatura convalidante : asigConvalidadaSelected.getConvalidadaPor()) {
            if ( !convalidante.getConvalidaciones().contains(asigConvalidadaSelected) ){
                List <Asignatura> c = (List <Asignatura>) convalidante.getConvalidaciones();
                c.add(asigConvalidadaSelected);
                convalidante.setConvalidaciones(c);
                asigCtrl.setSelected(convalidante);
                asigCtrl.update();
            }
        }
    }
    
    public void update(){
        List <Asignatura> convalidadaPor = (List <Asignatura>) asigConvalidadaSelected.getConvalidadaPor();
        List <Asignatura> toRemove = new ArrayList();
        for (Asignatura ca : convalidadaPor) {
            if ( ca.getIdPlan().equals(planConvalidante) )
                toRemove.add(ca);
        }
        convalidadaPor.removeAll(toRemove);
        
        convalidadaPor.addAll(dlAsignaturas.getTarget());
        asigConvalidadaSelected.setConvalidadaPor(convalidadaPor);
        asigCtrl.setSelected(asigConvalidadaSelected);
        asigCtrl.update();
        
        List<Asignatura> libres = dlAsignaturas.getSource();
        for (Asignatura libre : libres) {
            if (libre.getConvalidaciones().contains(asigConvalidadaSelected)){
               List <Asignatura> conv = (List <Asignatura>) libre.getConvalidaciones();
               conv.remove(asigConvalidadaSelected);
               libre.setConvalidaciones(conv);
               asigCtrl.setSelected(libre);
               asigCtrl.update();
            }
        }
        for (Asignatura nueva : dlAsignaturas.getTarget()) {
            if (!nueva.getConvalidaciones().contains(asigConvalidadaSelected)){
                List <Asignatura> conv = (List <Asignatura>) nueva.getConvalidaciones();
                conv.add(asigConvalidadaSelected);
                nueva.setConvalidaciones(conv);
                asigCtrl.setSelected(nueva);
                asigCtrl.update();
            }
        }
        //updateTable();
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
        getAsignaturasConvalidado();
        RequestContext.getCurrentInstance().update("datalist");
    }
    
    public String getConvalidadaPor2(Asignatura asig){
        //System.out.println("Striing");
        String conv = "";
        for (Asignatura c : asig.getConvalidadaPor()) {
            if (c.getIdPlan().equals(planConvalidante))
                conv += c.getNombreAsignatura() + "\n";
        }
        return conv;
    }
    
    public String getConvalidadaPor(Asignatura asig){
        
        String s = "";
        if (asig != null && planConvalidante != null){
            for (Asignatura a : planConvalidante.getAsignaturaCollection()) {
                if (a.getConvalidaciones().contains(asig)){
                    s = s + a.getCodigoAsignatura()+" "+a.getNombreAsignatura()+"\n";
                }
            }
        }
        return s;
    }
}
