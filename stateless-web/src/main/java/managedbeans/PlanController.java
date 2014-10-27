package managedbeans;

import entities.Asignatura;
import entities.Plan;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.PlanFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import org.apache.commons.lang.StringEscapeUtils;
import sessionbeans.AsignaturaFacadeLocal;

@Named("planController")
@SessionScoped
public class PlanController implements Serializable {
 
    @EJB
    private PlanFacadeLocal ejbFacade;
    private List<Plan> items = null;
    private Plan selected;
    private List<SelectItem> listaPlanes;
    private String selection;
    private Asignatura selectedAsignatura;

    public Asignatura getSelectedAsignatura() {
        return selectedAsignatura;
    }

    public void setSelectedAsignatura(Asignatura selectedAsignatura) {
        this.selectedAsignatura = selectedAsignatura;
    }

    @Inject
    private AsignaturaController asigController;
    
    public PlanController() {
    }

     public Plan getSelected() {
        return selected;
    }

    public void setSelected(Plan selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PlanFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Plan prepareCreate() {
        selected = new Plan();
        initializeEmbeddableKey();
        return selected;
    }
    
    public List<SelectItem> getListaPlanes(){
        this.listaPlanes = new ArrayList<>();
        for (Plan plan : items) {
            SelectItemGroup group = new SelectItemGroup(plan.getNombrePlan());
            List<Asignatura> asignaturas = plan.getAsignaturaCollection();
            SelectItem[] seleccion = new SelectItem[asignaturas.size()];
            for (int j = 0; j < asignaturas.size(); j++) {
                Asignatura asignatura = asignaturas.get(j);
                SelectItem option = new SelectItem(asignatura.getIdAsignatura(), asignatura.getNombreAsignatura());
                //option.setLabel("asd");
                seleccion[j] = option;
            }
            group.setSelectItems(seleccion);
            listaPlanes.add(group);
        }
        return listaPlanes;
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlanCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlanUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlanDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Plan> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Plan getPlan(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Plan> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Plan> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Plan.class)
    public static class PlanControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlanController controller = (PlanController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "planController");
            return controller.getPlan(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Plan) {
                Plan o = (Plan) object;
                return getStringKey(o.getIdPlan());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Plan.class.getName()});
                return null;
            }
        }

    }
    
    public String toJSON(Integer planId){
        StringBuilder jsonB = new StringBuilder();
        jsonB.append("var context = [");
        Plan plan = getPlan(planId);
        
        ArrayList<Asignatura> as;
        as = new ArrayList<>(plan.getAsignaturaCollection());
        for (Asignatura a : as) {
            ArrayList <Asignatura> pre, post;
            jsonB.append("{ \"nombre\": \"");
            jsonB.append(a.getNombreAsignatura());
            jsonB.append("\", \"id\": ");
            jsonB.append(a.getCodigoAsignatura());
            jsonB.append(", \"nivel\": ");
            jsonB.append(a.getNivelAsignatura());
            jsonB.append(", \"anual\": ");
            jsonB.append( a.getEsAnual() == 1 ? "true" : "false" );
            jsonB.append(", \"sct\": ");
            jsonB.append(a.getSctAsignatura());
            jsonB.append(", \"t\": ");
            jsonB.append(a.getHorasTeoria());
            jsonB.append(", \"e\": ");
            jsonB.append(a.getHorasEjercicio());
            jsonB.append(", \"l\": ");
            jsonB.append(a.getHorasLaboratorio());
            jsonB.append(", \"resumen\": \"");
             jsonB.append(StringEscapeUtils.escapeJavaScript(a.getResumenAsignatura()));
            //requisitos
            pre = new ArrayList<> (a.getAsignaturaCollection());
            jsonB.append("\", \"prerequisitos\": [");
            for (Asignatura p : pre) {
                jsonB.append(p.getCodigoAsignatura());
                if ( pre.lastIndexOf(p) != pre.size()-1 ) jsonB.append(",");
            }
            jsonB.append("], \"aperturas\": [");
            //apertura
            post = new ArrayList<> (a.getAsignaturaCollection1());
            for (Asignatura p : post) {
                jsonB.append(p.getCodigoAsignatura());
                if ( post.lastIndexOf(p) != post.size()-1 ) jsonB.append(",");
            }
            jsonB.append("] }");
            if (as.lastIndexOf(a) != as.size()-1) jsonB.append(",");
        }
        jsonB.append("];");
        return jsonB.toString() ;
    }
    
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
        setSelectedAsignatura(asigController.getAsignatura(Integer.parseInt(selection)));
    }


}
