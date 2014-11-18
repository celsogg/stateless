/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author miguel
 */
@Named("fileController")
@SessionScoped
public class FileController extends HttpServlet implements Serializable   {

    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;

    @Override
    public void init() {
//        ServletContext sc = config.getServletContext();
        ServletContext sc = this.getServletContext();
        String path = sc.getRealPath("/") + "WEB-INF/logs/logTransacciones.logs";
        this.archivo = new File(path);
        try {
            this.fr = new FileReader(archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.br = new BufferedReader(fr);
    }

    public void lectura() throws IOException {
        try {
            String linea;
            while ((linea = this.br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
