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
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author miguel
 */
public class FileController {
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    
    public void init(ServletConfig config) throws ServletException, FileNotFoundException {
        ServletContext sc = config.getServletContext();
        String path = sc.getRealPath("/")+"WEB-INF/logs/logTransacciones.logs";
        this.archivo = new File (path);
        this.fr = new FileReader (archivo);
        this.br = new BufferedReader(fr);
    }
    
    public void lectura() throws IOException{
        try{
            String linea;
            while((linea=this.br.readLine())!=null){
                System.out.println(linea);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}
