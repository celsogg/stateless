package managedbeans;

import entities.Asignatura;
import entities.Plan;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.PlanFacadeLocal;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.model.UploadedFile;

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
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PlanController.class);
    private Plan deletedPlan;

    private UploadedFile csvFile = null;
    private boolean csvWithHeader = false;
    private boolean csvFileSelected = false;

    @Inject
    private AsignaturaController asigController;

    public PlanController() {
    }

    public Asignatura getSelectedAsignatura() {
        return selectedAsignatura;
    }

    public void setSelectedAsignatura(Asignatura selectedAsignatura) {
        this.selectedAsignatura = selectedAsignatura;
    }

    public Plan getSelected() {
        return selected;
    }

    public void setSelected(Plan selected) {
        this.selected = selected;
    }

    public void refreshSelected() {
        if (selected != null) {
            Integer id = selected.getIdPlan();
            if (id != null) {
                selected = getPlan(id);
            }
        }
    }

    protected void setEmbeddableKeys() {
        // no embeddable keys
    }

    protected void initializeEmbeddableKey() {
        //no embeddable keys so no initialize needed
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
            items = null;
            // Invalidate list of items to trigger re-query.
        }
        LOGGER.info("Se ha creado un plan " + getSelected().getNombrePlan());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlanUpdated"));
        LOGGER.info("Se ha actualizado el plan " + getSelected().getNombrePlan() + ", codigo:" + getSelected().getCodigoPlan() + ", version: " + getSelected().getVersionPlan());
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlanDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            LOGGER.info("Se ha eliminado el plan " + deletedPlan.getNombrePlan() + ", codigo:" + deletedPlan.getCodigoPlan() + ", version: " + getSelected().getVersionPlan());
            selected = null;
            // Remove selection
            items = null;
            // Invalidate list of items to trigger re-query.

        }
    }

    public List<Plan> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Plan> getItemsByVisible() {

        List<Plan> items = getFacade().findByVisiblePlan();

        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    deletedPlan = selected;
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
        jsonB.append("var context_planes = [\n");

        for (Plan un_plan : getFacade().findByVisiblePlan()) {
            jsonB.append("\t{\n");
            jsonB.append("\t\tid: " + un_plan.getIdPlan() + ",\n");
            jsonB.append("\t\tnombre: \"" + un_plan.getNombrePlan() + "\",\n");
            jsonB.append("\t\tasignaturas: [\n");
            for (Asignatura asignatura_candidata : un_plan.getAsignaturaCollection()) {
                jsonB.append("\t\t\t{\n");
                jsonB.append("\t\t\t\tnombre: \"" + asignatura_candidata.getNombreAsignatura() + "\", \n");
                jsonB.append("\t\t\t\tcodigo: " + asignatura_candidata.getCodigoAsignatura() + ", \n");
                jsonB.append("\t\t\t\tnivel: " + asignatura_candidata.getNivelAsignatura() + ", \n");
                jsonB.append("\t\t\t\tid: " + asignatura_candidata.getCodigoAsignatura() + ", \n");
                jsonB.append("\t\t\t},\n");
            }
            jsonB.append("\t\t],\n");
            jsonB.append("\t},\n");
        }

        jsonB.append("];\n");
        jsonB.append("var id_plan = " + plan.getIdPlan() + ";");
        jsonB.append("var context = [");
        List<Asignatura> as;
        as = new ArrayList<>(plan.getAsignaturaCollection());
        for (Asignatura a : as) {
            List<Asignatura> pre, post;
            jsonB.append("{ \"nombre\": \"").append(a.getNombreAsignatura());
            jsonB.append("\", \n\"convalidaciones\": {");

            List<Plan> todos_los_planes = getFacade().findByVisiblePlan();

            for (int i = 0; i < todos_los_planes.size(); i++) {
                if (todos_los_planes.get(i).getIdPlan() == plan.getIdPlan()) {
                    todos_los_planes.remove(i);
                    break;
                }
            }

            for (Plan plan_convalidable : todos_los_planes) {
                jsonB.append("\n\t\"" + plan_convalidable.getIdPlan() + "\": [\n");

                for (Asignatura asignatura_convalidable : plan_convalidable.getAsignaturaCollection()) {
                        if(asignatura_convalidable.getConvalidaciones().contains(a)){
                            jsonB.append("\t\t" + asignatura_convalidable.getCodigoAsignatura() + ",\n");
                        }
                }
                jsonB.append("\n\t],");
            }

//            for (Asignatura asignatura : as) {
//                jsonB.append("\t\"id\": ").append(asignatura.getCodigoAsignatura() + ": {\n");
//                for (Asignatura asignatura_convalidable : asignatura.getConvalidaciones()) {
//                    jsonB.append("\t\tid_plan: " + asignatura_convalidable.getIdPlan().getCodigoPlan() + ", id_asignatura: " + asignatura_convalidable.getCodigoAsignatura()+ ", ");
//                }
//                jsonB.append("\n\t}");
//            }
            jsonB.append("}, \"id\": ").append(a.getCodigoAsignatura());
            jsonB.append(", \"nivel\": ").append(a.getNivelAsignatura());
            jsonB.append(", \"anual\": ").append(a.getEsAnual());
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

    public boolean isCsvWithHeader() {
        return csvWithHeader;
    }

    public UploadedFile getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(UploadedFile csvFile) {
        this.csvFile = csvFile;
    }

    public void upload() throws IOException {
        if (csvFile != null && csvFile.getFileName().compareToIgnoreCase("") != 0) {
            UploadedFile uf = csvFile;

            File save;
            save = File.createTempFile("temp", ".csv");

            uf.getContents();
            Files.copy(uf.getInputstream(), save.toPath(), StandardCopyOption.REPLACE_EXISTING);

            List<Asignatura> asignaturas = new ArrayList<>();

            BufferedReader br = Files.newBufferedReader(save.toPath(), Charset.forName("Windows-1252"));
            String line;

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
                asignatura.setEsAnual(false);

                if (strs[6].compareToIgnoreCase("ingreso") != 0) {
                    String[] requisitosStrs = strs[6].replace("\"", "").split(",");
                    List<Asignatura> requisitos = new ArrayList<>();
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

            selected.setAsignaturaCollection(asignaturas);
            csvFile = null;
            update();

        } else {
            FacesMessage message = new FacesMessage("No se ha seleccionado un archivo.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private String[] getCsvLineCols(String line) {
        String otherThanQuote = " [^\"] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "
                + // enable comments, ignore white spaces
                ",                         "
                + // match a comma
                "(?=                       "
                + // start positive look ahead
                "  (                       "
                + //   start group 1
                "    %s*                   "
                + //     match 'otherThanQuote' zero or more times
                "    %s                    "
                + //     match 'quotedString'
                "  )*                      "
                + //   end group 1 and repeat it zero or more times
                "  %s*                     "
                + //   match 'otherThanQuote'
                "  $                       "
                + // match the end of the string
                ")                         ",
                // stop positive look ahead
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
