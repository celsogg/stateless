package managedbeans;

import entities.Asignatura;
import entities.Plan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.PlanFacadeLocal;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import static managedbeans.AsignaturaController.logger;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.context.RequestContext;
import sessionbeans.PlanFacade;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("planController")
@SessionScoped
public class PlanController implements Serializable {

    @EJB
    private PlanFacadeLocal ejbFacade;
    private PlanFacade ejbPlanFacade;
    private List<Plan> items = null;
    private Plan selected;
    private List<SelectItem> listaPlanes;
    private String selection;
    private Asignatura selectedAsignatura;
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AsignaturaController.class);
    
    private UploadedFile csvFile = null;
    private boolean csvWithHeader = false;
    private boolean csvFileSelected = false;

    @Inject
    private AsignaturaController asigController;

    public Asignatura getSelectedAsignatura() {
        return selectedAsignatura;
    }
    
    private Principal getLoggedInUser()
    {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().
                    getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    public void setSelectedAsignatura(Asignatura selectedAsignatura) {
        this.selectedAsignatura = selectedAsignatura;
    }

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

    public List<SelectItem> getListaPlanes() {
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
        System.out.println("create!");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        logger.info("El usuario "+getLoggedInUser().getName() +" ha creado un plan");
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlanUpdated"));
        logger.info("El usuario "+getLoggedInUser().getName()+"ha actualizado el plan "+ getSelected().getNombrePlan()+", codigo:"+ getSelected().getCodigoPlan() + ", version: "+getSelected().getVersionPlan());
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlanDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            logger.info("El usuario "+getLoggedInUser().getName()+"ha eliminado el plan "+ getSelected().getNombrePlan()+", codigo:"+ getSelected().getCodigoPlan() + ", version: "+getSelected().getVersionPlan());
        }
    }

    public List<Plan> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Plan> getItemsByVisible() {
        //if (items == null) {
        List<Plan> items = getFacade().findByVisiblePlan();
        //}
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

    public String toJSON(Plan plan) {
        StringBuilder jsonB = new StringBuilder();
        jsonB.append("var context = [");
        ArrayList<Asignatura> as;
        as = new ArrayList<>(plan.getAsignaturaCollection());
        for (Asignatura a : as) {
            ArrayList<Asignatura> pre, post;
            jsonB.append("{ \"nombre\": \"").append(a.getNombreAsignatura());
            jsonB.append("\", \"id\": ").append(a.getCodigoAsignatura());
            jsonB.append(", \"nivel\": ").append(a.getNivelAsignatura());
            jsonB.append(", \"anual\": ").append(a.getEsAnual() == 1 ? "true" : "false");
            jsonB.append(", \"sct\": ").append(a.getSctAsignatura());
            jsonB.append(", \"t\": ").append(a.getHorasTeoria());
            jsonB.append(", \"e\": ").append(a.getHorasEjercicio());
            jsonB.append(", \"l\": ").append(a.getHorasLaboratorio());
            jsonB.append(", \"resumen\": \"");
            jsonB.append(StringEscapeUtils.escapeJavaScript(a.getResumenAsignatura()));
            //requisitos
            pre = new ArrayList<>(a.getAsignaturaCollection());
            jsonB.append("\", \"prerequisitos\": [");
            for (Asignatura p : pre) {
                jsonB.append(p.getCodigoAsignatura());
                if (pre.lastIndexOf(p) != pre.size() - 1) {
                    jsonB.append(",");
                }
            }
            jsonB.append("], \"aperturas\": [");
            //apertura
            post = new ArrayList<>(a.getAsignaturaCollection1());
            for (Asignatura p : post) {
                jsonB.append(p.getCodigoAsignatura());
                if (post.lastIndexOf(p) != post.size() - 1) {
                    jsonB.append(",");
                }
            }
            jsonB.append("] }");
            if (as.lastIndexOf(a) != as.size() - 1) {
                jsonB.append(",");
            }
        }
        jsonB.append("];");
        return jsonB.toString();
    }

    public String getAccion(String parametro) {
        if (parametro.isEmpty()) {
            return "false";
        }
        return "true";
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
        setSelectedAsignatura(asigController.getAsignatura(Integer.parseInt(selection)));
    }

    public void updateResumen() {
        asigController.setSelected(selectedAsignatura);
        asigController.update();
    }
    
    //

    public void setCsvWithHeader(boolean csvWithHeader) {
        this.csvWithHeader = csvWithHeader;
    }
    
    public boolean isCsvWithHeader(){
        return csvWithHeader;
    }
    
    public UploadedFile getFile() {
        return csvFile;
    }
 
    public void setFile(UploadedFile file) {
        this.csvFile = file;
    }
     
    public void upload() throws IOException {
        if(csvFile != null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            //context.redirect(context.getRequestContextPath() + "/plan/CompleteCsv.xhtml");
            FacesMessage message = new FacesMessage("Succesful", csvFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        
        UploadedFile uf = event.getFile();
        
        File save;
        save = File.createTempFile("temp", ".csv");

        uf.getContents();
        Files.copy(uf.getInputstream(), save.toPath(),  StandardCopyOption.REPLACE_EXISTING);
        
        ArrayList <Asignatura> asignaturas = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(save));
        String line = "";
        while ((line = br.readLine()) != null) {
            Asignatura asignatura = new Asignatura();
            String[] strs = getCsvLineCols(line);
            
            asignatura.setIdPlan(selected);
            
            asignatura.setCodigoAsignatura(strs[0]);
            asignatura.setNombreAsignatura(strs[1]);
            asignatura.setHorasTeoria(Integer.parseInt(strs[2]));
            asignatura.setHorasEjercicio(Integer.parseInt(strs[3]));
            asignatura.setHorasLaboratorio(Integer.parseInt(strs[4]));
            asignatura.setNivelAsignatura(Integer.parseInt(strs[5]));
            Short s = 0;
            asignatura.setEsAnual(s);

            if (strs[6].compareToIgnoreCase("ingreso") != 0) {
                String[] requisitosStrs = strs[6].replace("\"", "").split(",");
                ArrayList<Asignatura> requisitos = new ArrayList<>();
                for (String requisito : requisitosStrs) {

                    for (Asignatura a : asignaturas) {
                        if (a.getCodigoAsignatura().compareToIgnoreCase(requisito.trim()) == 0) {
                            requisitos.add(a);
                        }
                    }                  
                }
                asignatura.setAsignaturaCollection(requisitos);
            }
            asignaturas.add(asignatura);
        }
        
        FacesMessage message = new FacesMessage("Succesful", "El archivo \"" + uf.getFileName() + "\" se ha subido con éxito, guarde los cambios");
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext context = RequestContext.getCurrentInstance();
        csvFileSelected = true;
        selected.setAsignaturaCollection(asignaturas);
    }
    
    private String[] getCsvLineCols(String line){
        String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                ",                         "+ // match a comma
                "(?=                       "+ // start positive look ahead
                "  (                       "+ //   start group 1
                "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                "    %s                    "+ //     match 'quotedString'
                "  )*                      "+ //   end group 1 and repeat it zero or more times
                "  %s*                     "+ //   match 'otherThanQuote'
                "  $                       "+ // match the end of the string
                ")                         ", // stop positive look ahead
                otherThanQuote, quotedString, otherThanQuote);

        String[] tokens = line.split(regex, -1);
        return tokens;
    }

    public boolean isCsvFileSelected() {
        return csvFileSelected;
    }

    public void setCsvFileSelected(boolean csvFileSelected) {
        this.csvFileSelected = csvFileSelected;
    }
    
    
}
