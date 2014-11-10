package managedbeans;

/**
 *
 * @author Celso
 */
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javax.servlet.ServletContext;

public class PdfDownloadController extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final int BYTES_DOWNLOAD = 1024;
    
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
        DocumentException ex = null;
        ByteArrayOutputStream baosPDF = null;
        
        try {
            //System.out.println(request.getParameter("planId"));
            Plan plan;
            plan = planController.getPlan(Integer.parseInt(request.getParameter("planId")));
            
            baosPDF = generatePDFDocumentBytes(plan);
            StringBuffer sbFilename = new StringBuffer();
            sbFilename.append("filename_");
            sbFilename.append(System.currentTimeMillis());
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
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println( this.getClass().getName()+" caught an exception: "+dex.getClass().getName()+"<br>");
            writer.println("<pre>");
            dex.printStackTrace(writer);
            writer.println("</pre>");
        } finally {
            if (baosPDF != null) {
                baosPDF.reset();
            }
        }
    }

    protected ByteArrayOutputStream generatePDFDocumentBytes(Plan plan)
            throws DocumentException {
        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, baosPDF);
            //doc.addAuthor(this.getClass().getName());
            //doc.addCreationDate();
            //doc.addProducer();
            //doc.addCreator(this.getClass().getName());
            //doc.addTitle("This is a title.");
            //doc.addKeywords("pdf, itext, Java, open source, http");
            doc.setPageSize(PageSize.LETTER);
            //HeaderFooter footer = new HeaderFooter( new Phrase("This is a footer."), false);
            //doc.setFooter(footer);
            doc.open();
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 4, 1,1,2});
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("CÓDIGO"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("ASIGNATURA"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("T-E-L"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("SCT"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("REQUISITOS"));
            table.addCell(cell);
            doc.add(table);
            doc.add( new Phrase("\n"));
            doc.add( new Phrase("\n"));
            //table = createTable();
            Integer totalNiveles = getTotalNiveles(plan);
            for (int i = 0; i < totalNiveles; i++) {
                doc.add( new Phrase("Nivel " + (i+1) ));
                doc.add(createTableNivel(getAsignaturasNivel(plan, i+1)));
                doc.add( new Phrase("\n"));
            }
            //doc.add(table);
            //doc.add(new Paragraph("This document was created by a class named: " + this.getClass().getName()));
            //doc.add(new Paragraph("This document was created on " + new java.util.Date()));
            //doc.add(new Paragraph("This is a multi-page document."));
        } catch (DocumentException dex) {
            baosPDF.reset();
            throw dex;
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

    private PdfPTable createTableNivel(ArrayList<Asignatura> asignaturas) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 4, 1,1,2});
        PdfPCell cell;
//        cell = new PdfPCell(new Phrase("Table 1"));
//        cell.setColspan(3);
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
//        cell.setRowspan(2);
//        table.addCell(cell);
//        table.addCell("row 1; cell 1");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 2; cell 1");
//        table.addCell("row 2; cell 2");
        //System.out.println(asignaturas.toString());
        for (Asignatura asignatura : asignaturas) {
//            System.out.println(asignatura.getCodigoAsignatura() + " "+asignatura.getNombreAsignatura()+" "+
//                    asignatura.getHorasTeoria() +" "+
//                    asignatura.getHorasEjercicio() +" "+
//                    asignatura.getHorasLaboratorio()+" "+
//                    asignatura.getSctAsignatura());
            cell = new PdfPCell(new Phrase(asignatura.getCodigoAsignatura()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(asignatura.getNombreAsignatura()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(asignatura.getHorasTeoria().toString()+"-"+
                        asignatura.getHorasEjercicio().toString()+"-"+
                        asignatura.getHorasLaboratorio().toString()
                    ));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase((String) ((asignatura.getSctAsignatura()!=null) ? asignatura.getSctAsignatura().toString():"")));
            table.addCell(cell);
            StringBuilder sb = new StringBuilder();
            List<Asignatura> req = (List<Asignatura>) asignatura.getAsignaturaCollection();
            if (req.isEmpty()) sb.append("Ingreso");
            else{
                for (int i = 0; i<req.size(); i++) {
                    sb.append(req.get(i).getCodigoAsignatura());
                    sb.append(" ");
                }
            }
            cell = new PdfPCell(new Phrase(sb.toString()));
            table.addCell(cell);
        }
        return table;
    }

    private Integer getTotalNiveles(Plan plan) {
        Integer max = 0;
        for (Asignatura a : plan.getAsignaturaCollection()) {
            Integer actual = a.getNivelAsignatura();
            if ( actual > max ) max = actual;
        }
        return max;
    }
    
    private ArrayList<Asignatura> getAsignaturasNivel(Plan plan, Integer nivel){
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        for (Asignatura asig : plan.getAsignaturaCollection()) {
            if (Objects.equals(asig.getNivelAsignatura(), nivel)) asignaturas.add(asig);
        }
        return asignaturas;
    }
}
