package managedbeans;

import entities.Habilidad;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.HabilidadFacadeLocal;

import java.io.Serializable;
import java.security.Principal;
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
import javax.servlet.http.HttpServletRequest;

@Named("habilidadController")
@SessionScoped
public class HabilidadController implements Serializable {

    @EJB
    private HabilidadFacadeLocal ejbFacade;
    private List<Habilidad> items = null;
    private Habilidad selected;
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HabilidadController.class);

    public HabilidadController() {
    }
    
    private Principal getLoggedInUser()
    {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().
                    getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    public Habilidad getSelected() {
        return selected;
    }

    public void setSelected(Habilidad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HabilidadFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Habilidad prepareCreate() {
        selected = new Habilidad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HabilidadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        logger.info("El usuario "+getLoggedInUser().getName() +" ha creado una habilidad");
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HabilidadUpdated"));
        logger.info("El usuario "+getLoggedInUser().getName()+"ha actualizado la habilidad "+ getSelected().getDescripcionHabilidad());
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HabilidadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            logger.info("El usuario "+getLoggedInUser().getName()+"ha eliminado la habilidad "+ getSelected().getDescripcionHabilidad());
        }
    }

    public List<Habilidad> getItems() {
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

    public Habilidad getHabilidad(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Habilidad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Habilidad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Habilidad.class)
    public static class HabilidadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HabilidadController controller = (HabilidadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "habilidadController");
            return controller.getHabilidad(getKey(value));
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
            if (object instanceof Habilidad) {
                Habilidad o = (Habilidad) object;
                return getStringKey(o.getIdHabilidad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Habilidad.class.getName()});
                return null;
            }
        }

    }

}
