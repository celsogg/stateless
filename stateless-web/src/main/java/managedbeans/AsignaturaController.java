package managedbeans;

import entities.Asignatura;
import entities.Plan;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.AsignaturaFacadeLocal;

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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
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
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AsignaturaController.class);
    private String deletedAsignatura;

    @Inject
    private PlanController planController;

    private DualListModel<Asignatura> dlAsignaturas;

    public AsignaturaController() {
    }

    public Asignatura getSelected() {
        return selected;
    }

    public void setSelected(Asignatura selected) {
        this.selected = selected;
        if (selected != null) {
            List<Asignatura> asignaturasActuales = (List<Asignatura>) selected.getAsignaturasRequisito();
            List<Asignatura> asignaturasPosibles;
            if (itemsPlan != null) {
                asignaturasPosibles = new ArrayList<>(itemsPlan);
            } else {
                asignaturasPosibles = new ArrayList<>();
            }
            asignaturasPosibles.remove(selected);
            asignaturasPosibles.removeAll(asignaturasActuales);
            dlAsignaturas = new DualListModel<>(asignaturasPosibles, asignaturasActuales);
        }
    }

    public void refreshSelected() {
        if (selected != null) {
            Integer id = selected.getIdAsignatura();
            if (id != null) {
                selected = getAsignatura(id);
            }
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
        for (Object item : event.getItems()) {
            builder.append(((Asignatura) item).getNombreAsignatura()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    protected void setEmbeddableKeys() {
        //no embeddable keys
    }

    protected void initializeEmbeddableKey() {
        //no embeddable keys so no initialized needed
    }

    private AsignaturaFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Asignatura prepareCreate() {
        if (selected == null) {
            selected = new Asignatura();
            initializeEmbeddableKey();
            selected.setIdPlan(planController.getSelected());
            selected.setAsignaturasRequisito(new ArrayList<Asignatura>());
            selected.setAsignaturasApertura(new ArrayList<Asignatura>());
        }
        return selected;
    }

    public String getNombresAsignaturaString(Asignatura asignatura) {

        String salida = "";

        for (Asignatura a1 : asignatura.getAsignaturasRequisito()) {
            salida = salida + a1.getNombreAsignatura() + "\n";
        }

        return salida;
    }

    public String getNombresAsignatura2String(Asignatura a) {
        String salida = "";

        for (Asignatura a1 : a.getAsignaturasApertura()) {
            salida = salida + a1.getNombreAsignatura() + "\n";
        }
        return salida;
    }

    public String getStringRequisitos(Asignatura asignatura) {
        StringBuilder sb = null;
        for (Asignatura req : asignatura.getAsignaturasRequisito()) {
            sb.append("");
            sb.append(req.getCodigoAsignatura()).append(" ").append(req.getNombreAsignatura()).append("\n");
        }
        return sb.toString();
    }

    public void create() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (this.selected.getCodigoAsignatura() == null || this.selected.getCodigoAsignatura().trim().isEmpty()) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar un código", "Debe ingresar un código"));
            return;
        }
        
        if (this.selected.getNombreAsignatura() == null || this.selected.getNombreAsignatura().trim().isEmpty()) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar un nombre", "Debe ingresar un nombre"));
            return;
        }

        if (this.selected.getNivelAsignatura() == null) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar un nivel", "Debe ingresar un nivel"));
            return;
        }
        
        if (this.selected.getSctAsignatura() == null) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar SCT", "Debe ingresar un valor válido de SCT"));
            return;
        }

        if (this.selected.getHorasTeoria() == null) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar el número de horas teoría", "Debe ingresar el número de horas teoría"));
            return;
        }

        if (this.selected.getHorasEjercicio() == null) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar el número de horas ejecicio", "Debe ingresar el número de horas ejercicio"));
            return;
        }

        if (this.selected.getHorasLaboratorio() == null) {
            context.addMessage(null, new FacesMessage("Error: Debe ingresar el número de horas laboratorio", "Debe ingresar el número de horas laboratorio"));
            return;
        }

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaCreated"));
//        context.addMessage(null, new FacesMessage("Asignatura agregada correctamente", "Asignatura agregada correctamente"));
//        if (!JsfUtil.isValidationFailed()) {
//            context.addMessage(null, new FacesMessage("Asignatura agregada correctamente", "Asignatura agregada correctamente"));
//            LOGGER.info("Ha creado la asignatura: " + getSelected().getNombreAsignatura());
//            items = null;
//            itemsPlan = null;
//        }
//        RequestContext.getCurrentInstance().execute("AsignaturaCreateDlg.hide()");
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaUpdated"));
        LOGGER.info("Ha actualizado la asignatura: " + getSelected().getNombreAsignatura());
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsignaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null;
            // Remove selection
            items = null;
            // Invalidate list of items to trigger re-query.
        }
        LOGGER.info("Ha eliminado la asignatura " + deletedAsignatura);
    }

    public List<Asignatura> getItems() {

        if (items == null) {

            items = getFacade().findAll();
        }
        return items;
    }

    public List<Asignatura> getItemsPlan() {

        planController.refreshSelected();
        itemsPlan = planController.getSelected().getAsignaturaCollection();
        refreshSelected();
        return itemsPlan;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    deletedAsignatura = selected.getNombreAsignatura();
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

        selected.setAsignaturasRequisito(dlAsignaturas.getTarget());
        update();
    }

    public List<Asignatura> getAsignaturasPlan(Plan plan) {
        return getFacade().findAsignaturasByPlan(plan);
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
