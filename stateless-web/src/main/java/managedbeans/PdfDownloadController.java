package managedbeans;

/**
 *
 * @author Celso
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entities.Asignatura;
import entities.Plan;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.log4j.Logger;

public class PdfDownloadController extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LOGO_USACH_URL = "/resources/images/UDS_HCOLOR.png";
    private static final Logger LOGGER = Logger.getLogger(PdfDownloadController.class);
    
    @Inject
    private PlanController planController;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ByteArrayOutputStream baosPDF = null;
        
        try {
            Plan plan;
            plan = planController.getPlan(Integer.parseInt(request.getParameter("planId")));
            
            baosPDF = generatePDFDocumentBytes(plan);
            StringBuffer sbFilename = new StringBuffer();
            sbFilename.append("Plan_");
            sbFilename.append(plan.getCodigoPlan());
            sbFilename.append(".pdf");

            // Read the HTTP 1.1 specification for full details
            // about the Cache-Control header
            response.setHeader("Cache-Control", "max-age=30");
            response.setContentType("application/pdf");
            // The Content-disposition header is explained
            // in RFC 2183
            //    http://www.ietf.org/rfc/rfc2183.txt
            // The Content-disposition value will be in one of 
            // two forms:
            //   1)  inline; filename=foobar.pdf
            //   2)  attachment; filename=foobar.pdf
            // In this servlet, we use "inline"
            
            StringBuilder sbContentDispValue = new StringBuilder();
            sbContentDispValue.append("inline");
            sbContentDispValue.append("; filename=");
            sbContentDispValue.append(sbFilename);

            response.setHeader( "Content-disposition", sbContentDispValue.toString());
            response.setContentLength(baosPDF.size());
            ServletOutputStream sos;
            sos = response.getOutputStream();
            baosPDF.writeTo(sos);
            sos.flush();
        } catch (DocumentException dex) {
            LOGGER.error(dex);
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println( this.getClass().getName()+" caught an exception: "+dex.getClass().getName()+"<br>");
            writer.println("<pre>");
            writer.println("</pre>");
        } finally {
            if (baosPDF != null) {
                baosPDF.reset();
            }
        }
    }

    protected ByteArrayOutputStream generatePDFDocumentBytes(Plan plan)
    throws DocumentException, BadElementException, IOException {
        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, baosPDF);
           
            doc.setPageSize(PageSize.LETTER);

            doc.open();
            
            Image logo = Image.getInstance(getServletContext().getResource(LOGO_USACH_URL));
            logo.scalePercent(10f);
            logo.setAlignment(Element.ALIGN_RIGHT);
            doc.add(logo);
            
            doc.add(new Phrase("\n"+plan.getIdCarrera().getNombreCarrera()+"\n", new Font(FontFamily.HELVETICA, 22)));
            doc.add(new Phrase("Plan de Estudios "+plan.getCodigoPlan()+" ("+plan.getAnioPlan()+")"));

            doc.add( new Phrase("\n"));
            doc.add( new Phrase("\n"));
            Integer totalNiveles = getTotalNiveles(plan);
            for (int i = 0; i < totalNiveles; i++) {
                Paragraph para = new Paragraph("Nivel " + (i+1) + "\n\n");
                para.setAlignment(Element.ALIGN_RIGHT);
                doc.add(para);
                doc.add(new LineSeparator());
                doc.add(createInfoNivel(getAsignaturasNivel(plan, i+1)));
                doc.add( new Phrase("\n"));
            }

        } catch (DocumentException /*| IOException*/ ex) {
            baosPDF.reset();
            throw ex;
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
        if (baosPDF.size() < 1) {
            throw new DocumentException("document has " + baosPDF.size() + " bytes");
        }
        return baosPDF;
    }


    private Integer getTotalNiveles(Plan plan) {
        Integer max = 0;
        for (Asignatura a : plan.getAsignaturaCollection()) {
            Integer actual = a.getNivelAsignatura();
            if ( actual > max ){
                max = actual;
            }
        }
        return max;
    }
    
    private List<Asignatura> getAsignaturasNivel(Plan plan, Integer nivel){
        List<Asignatura> asignaturas;
        asignaturas = new ArrayList<>();
        for (Asignatura asig : plan.getAsignaturaCollection()) {
            if (Objects.equals(asig.getNivelAsignatura(), nivel)) {
                asignaturas.add(asig);
            }
        }
        return asignaturas;
    }

    private Element createInfoNivel(List<Asignatura> asignaturasNivel) {
        StringBuilder contentSB = new StringBuilder();
        for (Asignatura asig : asignaturasNivel) {
            contentSB.append("\nAsignatura: ").append(blankIfNull(asig.getCodigoAsignatura()));
            contentSB.append(" - ").append(blankIfNull(asig.getNombreAsignatura())).append("\n");
            contentSB.append(blankIfNull(asig.getResumenAsignatura()));
            contentSB.append("\nRequisitos: ");
            List<Asignatura> requisitos =  (List<Asignatura>) asig.getAsignaturasRequisito();
            if (requisitos.isEmpty()){
                contentSB.append("Ingreso\n\n");
            }else{
                for (int i = 0; i < requisitos.size(); i++) {
                    contentSB.append(blankIfNull(requisitos.get(i).getCodigoAsignatura()));
                    contentSB.append(" - ");
                }
                contentSB.replace(contentSB.length()-3, contentSB.length(), "\n\n");
            }
        }
        return new Paragraph(contentSB.toString());
    }
    
    /**
     * 
     * @param str
     * @return 
     */
    private String blankIfNull(String str){
        String ret;
        if (str == null) {
            ret = "";
        }else{
            ret = str;
        }
        return ret;
    }
}
