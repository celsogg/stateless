package managedbeans;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Perfil;
import entities.Seccion;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Celso
 */
public class PdfPerfilController extends HttpServlet {
    
    public static final String LOGO_USACH_URL = "/resources/images/UDS_HCOLOR.png";
    
    @Inject
    PerfilController perfilCtrl;
    
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
            Perfil perfil;
            perfil = perfilCtrl.getPerfil(Integer.parseInt(request.getParameter("id")));
            
            if (perfil == null) throw new NullPointerException("Perfil no encontrado");

            baosPDF = generatePDFDocumentBytes(perfil);
            StringBuffer sbFilename = new StringBuffer();
            sbFilename.append("Perfil de Egreso - ");
            sbFilename.append(StringUtils.stripAccents(perfil.getIdCarrera().getNombreCarrera()));
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
        } catch (NumberFormatException | NullPointerException | DocumentException | IOException dex) {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Documento no disponible<br>");
            writer.println("<pre>");
            writer.println("</pre>");
        } finally {
            if (baosPDF != null) {
                baosPDF.reset();
            }
        }
    }
    
    protected ByteArrayOutputStream generatePDFDocumentBytes(Perfil perfil)
    throws DocumentException, BadElementException, IOException {
        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, baosPDF);
            doc.addTitle("Perfil de Egreso "+WordUtils.capitalize(perfil.getIdCarrera().getNombreCarrera()));
            doc.setPageSize(PageSize.LETTER);
            //HeaderFooter footer = new HeaderFooter( new Phrase("This is a footer."), false);
            //doc.setFooter(footer);
            doc.open();
            
            Image logo = Image.getInstance(getServletContext().getResource(LOGO_USACH_URL));
            logo.scalePercent(10f);
            logo.setAlignment(Element.ALIGN_RIGHT);
            doc.add(logo);
            
            doc.add(new Phrase("Perfil de Egreso\n\n"));
            doc.add(new Phrase("Carrera: "+WordUtils.capitalize(perfil.getIdCarrera().getNombreCarrera())+"\n\n", new Font(Font.FontFamily.HELVETICA, 22)));

            for (int i = 0; i < perfil.getSeccionCollection().size(); i++) {
                Seccion seccion = perfil.getSeccionCollection().get(i);
                doc.add(new Phrase(getRoman(i+1) + ". "+ seccion.getNombreSeccion() + "\n\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                
                BufferedReader bufReader = new BufferedReader(new StringReader(seccion.getDescripcionSeccion()));

                String line = null;
                int c = 0;
                while( (line = bufReader.readLine()) != null ) {
                    String s = StringUtils.stripAccents(line.toLowerCase());
                    if (s.startsWith("habilidades g")){
                        doc.add(new Phrase("(" + getRoman(1).toLowerCase() + ") " 
                                + line + "\n\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    } else if (s.startsWith("habilidades especializadas g")) {
                        doc.add(new Phrase("(" + getRoman(2).toLowerCase() + ") " 
                                + line + "\n\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    } else if (s.startsWith("habilidades especializadas e")) {
                        doc.add(new Phrase("(" + getRoman(3).toLowerCase() + ") " 
                                + line + "\n\n", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                    } else {
                        
                        Pattern pattern = Pattern.compile("([0-9]*)");
                        
                        Paragraph p;
                        p = new Paragraph(line + "\n");
                        if (line.contains(".")){
                            if (pattern.matcher( line.substring(0, line.indexOf(".")) ).matches() ){
                                p.setIndentationLeft(30);
                            } else {
                                p.setFirstLineIndent(30);
                            }
                        } else {
                            p.setFirstLineIndent(30);
                        }
                        p.setAlignment(Element.ALIGN_JUSTIFIED);
                        doc.add(p);
                    }
                    
                }
                doc.add(new Phrase("\n\n"));
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
    
    public  String getRoman(int number) {
        String riman[] = {"M","XM","CM","D","XD","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int arab[] = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(riman[i]);
            }
            i++;
        }
        return result.toString();
    }
}
