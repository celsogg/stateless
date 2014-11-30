/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;


import java.io.File;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author miguel
 */

public class Log4jInitServlet extends HttpServlet {
    
    /**
     *
     * @param config
     * @throws javax.servlet.ServletException
     */
    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(Log4jInitServlet.class);


    
    @Override 
    public void init(ServletConfig config) throws ServletException {
        String log4jLocation = config.getInitParameter("log4j-properties-location");
        
        ServletContext sc = config.getServletContext();
        
        if (log4jLocation == null) {
            BasicConfigurator.configure();
        }else{
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File file = new File(log4jProp);
            if (file.exists()) {
		PropertyConfigurator.configure(log4jProp);
                System.setProperty("rootPath", webAppPath);
                
            }else{
                LOGGER.error("*** " + log4jProp + " file not found, so initializing log4j with BasicConfigurator");
		BasicConfigurator.configure();
            }
            
        }
        super.init(config);
        
    }
}