package managedbeans;

import entities.Asignatura;
import entities.Plan;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.AsignaturaFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named("asignaturaController")
@SessionScoped
public class AsignaturaController implements Serializable {
    
    @EJB
    private AsignaturaFacadeLocal ejbFacade;
    private List<Asignatura> items = null;
    private List<Asignatura> itemsPlan = null;
    private Asignatura selected;
    
    @Inject
    private PlanController planController;
    
    private DualListModel<Asignatura> DLAsignaturas;

    public AsignaturaController() {
    }

    public Asignatura getSelected() {
        return selected;
    }

    public void setSelected(Asignatura selected) {
        this.selected = selected;
        if (selected != null){
            List<Asignatura> asignaturasActuales = (List<Asignatura>) selected.getAsignaturaCollection();
            List<Asignatura> asignaturasPosibles = new ArrayList<>(itemsPlan);
            asignaturasPosibles.remove(selected);
            asignaturasPosibles.removeAll(asignaturasActuales);
            DLAsignaturas = new DualListModel<>(asignaturasPosibles, asignaturasActuales);
        }
    }

    public DualListModel<Asignatura> getDLAsignaturas() {
        return DLAsignaturas;
    }

    public void setDLAsignaturas(DualListModel<Asignatura> DLAsignaturas) {
        this.DLAsignaturas = DLAsignaturas;
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
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsignaturaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura prepareCreate() {
        selected = new Asignatura();
        initializeEmbeddableKey();
        return selected;
    }
    
     public void borrarProyeccion(Integer id_requisito){
        ArrayList<Asignatura> proyeccion = new ArrayList<>(selected.getAsignaturaCollection1());
        
        List<Asignatura> asig = getFacade().findAsignaturas(3);
        Asignatura asigna = asig.get(0);
        
        ArrayList<Asignatura> proye = new ArrayList<>(asigna.getAsignaturaCollection1());
        
        System.out.println("entre");
        for (Asignatura pro : proye) {
            System.out.println("asi "+ pro.getIdAsignatura() + "=" + selected.getIdAsignatura());
            System.out.println("asi name" + pro.getNombreAsignatura());
            if(pro.getIdAsignatura() == 9) {
                System.out.println("nombre proyecion" + asigna.getNombreAsignatura());
                System.out.println("id proyeccion" + asigna.getIdAsignatura());
                System.out.println("nombre asig requi: " + pro.getNombreAsignatura());
                System.out.println(" id asign : " + pro.getIdAsignatura());
                ArrayList<Asignatura> asdf = new ArrayList<>();
                
                List<Asignatura> asig2 = getFacade().findAsignaturas(pro.getIdAsignatura());
                Asignatura asigna2 = asig2.get(0);
                System.out.println("asdfasdasd:" +items.get(pro.getIdAsignatura()-3).getNombreAsignatura());
                items.get(pro.getIdAsignatura()-3).setAsignaturaCollection(null);
                
                update();
          
                 System.out.println("hago el update proyeccion");
                break;
            }
            
        }
         for (Asignatura proye1 : proyeccion) {
             System.out.println("algo" + proye1.getNombreAsignatura());
         }
        
        
        
     }
    
    public void borrarRequisito(Integer id_requisito){
        ArrayList<Asignatura> requisitos = new ArrayList<>(selected.getAsignaturaCollection());
        
        List<Asignatura> asig = getFacade().findAsignaturas(9);
        Asignatura asigna = asig.get(0);
        
        ArrayList<Asignatura> proye = new ArrayList<>(asigna.getAsignaturaCollection1());
        
        System.out.println("entre");
        for (Asignatura requi : requisitos) {
            if(requi.getIdAsignatura() == 9) {
                System.out.println("nombre proyecion" + asigna.getNombreAsignatura());
                System.out.println("id proyeccion" + asigna.getIdAsignatura());
                System.out.println("nombre asig requi: " + requi.getNombreAsignatura());
                System.out.println(" id asign : " + requi.getIdAsignatura());
                
                requisitos.remove(requi);
                break;
            }
            
        }
        
        selected.setAsignaturaCollection(requisitos);  
        update();
          
        System.out.println("hago el update requisitos");
        /*
        for (Asignatura asi : proye) {
            System.out.println("asi "+ asi.getIdAsignatura() + "=" + selected.getIdAsignatura());
            System.out.println("asi name" + asi.getNombreAsignatura());
            if(asi.getIdAsignatura() == selected.getIdAsignatura()){
                proye.remove(asi);
                break;
            
            }
        
        }
        for (Asignatura asias : proye) {
            System.out.println(asias.getNombreAsignatura());
            
        }
       
        asigna.setAsignaturaCollection1(proye);  
        update();  
        System.out.println("hago el update proyeccion");
        */
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asignatura> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Asignatura> getItemsPlan(){
        Plan plan = planController.getSelected();
        itemsPlan = plan.getAsignaturaCollection();
        return itemsPlan;
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

    public Asignatura getAsignatura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Asignatura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asignatura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void saveRequisitos() {
        System.out.println("Selected: "+selected.getCodigoAsignatura()+" "+selected.getNombreAsignatura());
        System.out.println("Requisitos");
        for (Asignatura item : DLAsignaturas.getTarget()) {
            System.out.println("-"+item.getCodigoAsignatura()+" "+item.getNombreAsignatura());
        }
    }

    @FacesConverter(forClass = Asignatura.class, value = "asig")
    public static class AsignaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignaturaController controller = (AsignaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignaturaController");
            return controller.getAsignatura(getKey(value));
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
            if (object instanceof Asignatura) {
                Asignatura o = (Asignatura) object;
                return getStringKey(o.getIdAsignatura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asignatura.class.getName()});
                return null;
            }
        }

    }
}
