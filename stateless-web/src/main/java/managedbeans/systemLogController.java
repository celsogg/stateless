package managedbeans;

import entities.systemLog;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.systemLogFacadeLocal;

import java.io.Serializable;
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

@Named("systemLogController")
@SessionScoped
public class systemLogController implements Serializable {

    @EJB
    private systemLogFacadeLocal ejbFacade;
    private List<systemLog> items = null;
    private systemLog selected;

    public systemLogController() {
    }

    public systemLog getSelected() {
        return selected;
    }

    public void setSelected(systemLog selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        //no embeddable keys
    }

    protected void initializeEmbeddableKey() {
        //no embeddable keys so no initialize needed
    }

    private systemLogFacadeLocal getFacade() {
        return ejbFacade;
    }

    public systemLog prepareCreate() {
        selected = new systemLog();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("systemLogCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    
            // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("systemLogUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("systemLogDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            // Remove selection
            items = null;    
            // Invalidate list of items to trigger re-query.
        }
    }

    public List<systemLog> getItems() {
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

    public systemLog getsystemLog(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<systemLog> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<systemLog> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = systemLog.class)
    public static class systemLogControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            systemLogController controller = (systemLogController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "systemLogController");
            return controller.getsystemLog(getKey(value));
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
            if (object instanceof systemLog) {
                systemLog o = (systemLog) object;
                return getStringKey(o.getLogsId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), systemLog.class.getName()});
                return null;
            }
        }

    }

}