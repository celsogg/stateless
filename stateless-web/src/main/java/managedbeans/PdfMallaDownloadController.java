package managedbeans;

/**
 *
 * @author Celso
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
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
import org.apache.log4j.Logger;

public class PdfMallaDownloadController extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LOGO_USACH_URL = "/resources/images/UDS_HCOLOR.png";
    public static final int  SIETE= 7;
    public static final float DIEZF = 10f;
    public static final int CIEN = 100;
    public static final int VEINTIDOS = 22;
    public static final int DOS = 2;
    public static final int CIENTOSETENTAYCINCO = 175;
    public static final int OCHO = 8;
    private static final Logger LOGGER = Logger.getLogger(PdfMallaDownloadController.class);

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

            response.setHeader("Content-disposition", sbContentDispValue.toString());
            response.setContentLength(baosPDF.size());
            ServletOutputStream sos;
            sos = response.getOutputStream();
            baosPDF.writeTo(sos);
            sos.flush();
        } catch (DocumentException dex) {
            LOGGER.error(dex);
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(this.getClass().getName() + " caught an exception: " + dex.getClass().getName() + "<br>");
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
            Font fontAsignaturas = FontFactory.getFont(FontFactory.HELVETICA, SIETE);
            docWriter = PdfWriter.getInstance(doc, baosPDF);
            doc.setPageSize(PageSize.LETTER.rotate());
            doc.open();

            Image logo = Image.getInstance(getServletContext().getResource("/resources/images/UDS_HCOLOR.png"));
            logo.scalePercent(DIEZF);
            logo.setAlignment(Element.ALIGN_RIGHT);
            logo.setAbsolutePosition(PageSize.LETTER.rotate().getWidth() - CIENTOSETENTAYCINCO, PageSize.LETTER.rotate().getHeight() - CIEN);
            doc.add(logo);
            
            Phrase nombreCarrera = new Phrase("\n"+plan.getIdCarrera().getNombreCarrera()+"\n", new Font(Font.FontFamily.HELVETICA, VEINTIDOS));
            Phrase nombrePlan = new Phrase("Plan de Estudios "+plan.getCodigoPlan()+" ("+plan.getAnioPlan()+")");
            doc.add(nombreCarrera);
            doc.add(nombrePlan);



            List<Asignatura> asignaturas;
            asignaturas = new ArrayList<>(plan.getAsignaturaCollection());

            int maximoNivel = 1;
            for (int i = 0; i < asignaturas.size(); i++) {
                if (asignaturas.get(i).getNivelAsignatura() > maximoNivel) {
                    maximoNivel = asignaturas.get(i).getNivelAsignatura();
                }
            }

            PdfPTable table = new PdfPTable(maximoNivel);
            table.setSplitLate(false); // default value
            table.setWidthPercentage(CIEN); //Width 100%


            float columnWidths[] = new float[maximoNivel];
            for (int i = 0; i < maximoNivel; i++) {
                columnWidths[i] = 1f;
            }
            table.setWidths(columnWidths);

            for (int i = 0; i < maximoNivel / DOS; i++) {
                PdfPCell cell = new PdfPCell(new Paragraph((i + 1) + "° Año", fontAsignaturas));
                cell.setNoWrap(true);
                cell.setRightIndent(1);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                if (i % 2 == 0) {
                    cell.setBackgroundColor(new BaseColor(255, 185, 140));
                } else {
                    cell.setBackgroundColor(new BaseColor(255, 143, 71));
                }
                cell.setColspan(2);
                table.addCell(cell);
            }

            for (int i = 0; i < maximoNivel; i++) {
                PdfPCell cell = new PdfPCell(new Paragraph("Semestre " + (i + 1), fontAsignaturas));
                cell.setRightIndent(1);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setPaddingLeft(10);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                if (i % 2 == 0) {
                    cell.setBackgroundColor(new BaseColor(128, 169, 230));
                } else {
                    cell.setBackgroundColor(new BaseColor(204, 220, 245));
                }
                table.addCell(cell);
            }

            int asignaturasImpresas = 0;
            int totalAsignaturas = asignaturas.size();
            Boolean imprimio;
            Boolean[] asignaturasImpresasArray = new Boolean[totalAsignaturas];
            int nivelActual = 1;
            int asignaturasImpresasPorEsteCiclo = 0;

            for (int i = 0; i < totalAsignaturas; i++) {
                asignaturasImpresasArray[i] = false;
            }
            int celdasEscritas = 0;
            while (asignaturasImpresas < totalAsignaturas) {
                imprimio = false;
                for (int i = 0; i < totalAsignaturas; i++) {
                    if (!asignaturasImpresasArray[i] && asignaturas.get(i).getNivelAsignatura() == nivelActual) {
                        imprimio = true;
                        asignaturasImpresasArray[i] = true;

                        PdfPCell cell = new PdfPCell(new Paragraph(asignaturas.get(i).getNombreAsignatura(), fontAsignaturas));
                        cell.setBorderColor(BaseColor.BLACK);
                        cell.setPaddingLeft(4);
                        cell.setPaddingRight(4);
                        if (asignaturas.get(i).getEsAnual()) {
                            cell.setColspan(2);
                            celdasEscritas++;
                        }
                        celdasEscritas++;

                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        if (asignaturas.get(i).getEsAnual()) {
                            nivelActual = (nivelActual + 1) % maximoNivel;
                        }
                        asignaturasImpresas++;
                        asignaturasImpresasPorEsteCiclo++;
                        break;

                    }
                }
                if (!imprimio) {
                    PdfPCell cell = new PdfPCell(new Paragraph("", fontAsignaturas));
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setPaddingLeft(OCHO);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                    celdasEscritas++;
                }
                nivelActual = (nivelActual) % maximoNivel + 1;
            }
            while (celdasEscritas % maximoNivel != 0) {
                PdfPCell cell = new PdfPCell(new Paragraph("", fontAsignaturas));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setPaddingLeft(OCHO);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                celdasEscritas++;
            }

            doc.add(table);

            doc.close();

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
}

    