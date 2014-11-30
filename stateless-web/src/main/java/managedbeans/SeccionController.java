package managedbeans;

import entities.Perfil;
import entities.Seccion;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.SeccionFacadeLocal;

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
import javax.inject.Inject;

@Named("seccionController")
@SessionScoped
public class SeccionController implements Serializable {

    @EJB
    private SeccionFacadeLocal ejbFacade;
    private List<Seccion> items = null;
    private List<Seccion> itemsPerfil = null;
    @Inject
    private PerfilController perfilController;
    private Seccion selected;
    final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SeccionController.class);
    private Seccion deletedSeccion;

    public SeccionController() {
    }

    public Seccion getSelected() {
        return selected;
    }

    public void setSelected(Seccion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        //no embeddable keys
    }

    protected void initializeEmbeddableKey() {
        //no embeddable keys so no initialize needed
    }

    private SeccionFacadeLocal getFacade() {
        return ejbFacade;
    }

    public Seccion prepareCreate() {
        selected = new Seccion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SeccionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    
            // Invalidate list of items to trigger re-query.
        }
        LOGGER.info("Se ha creado una sección: "+getSelected().getNombreSeccion());
        
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SeccionUpdated"));
        LOGGER.info("Se ha actualizado una sección: "+getSelected().getNombreSeccion());
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SeccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; 
            // Remove selection
            items = null;    
            // Invalidate list of items to trigger re-query.
        }
        LOGGER.info("Se ha eliminado la sección: " + deletedSeccion.getNombreSeccion());
    }

    public List<Seccion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Seccion> getItemsPerfil(){
        Perfil perfil = perfilController.getSelected();
        itemsPerfil = perfil.getSeccionCollection();
        return itemsPerfil;
    }
        
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    deletedSeccion = selected;
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

    public Seccion getSeccion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Seccion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Seccion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Seccion.class)
    public static class SeccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SeccionController controller = (SeccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "seccionController");
            return controller.getSeccion(getKey(value));
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
            if (object instanceof Seccion) {
                Seccion o = (Seccion) object;
                return getStringKey(o.getIdSeccion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Seccion.class.getName()});
                return null;
            }
        }

    }

}
