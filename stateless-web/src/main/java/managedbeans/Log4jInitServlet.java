/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author miguel
 */

public class Log4jInitServlet extends HttpServlet {
    
    /**
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init (ServletConfig config) throws ServletException { 
        String root = config.getServletContext().getRealPath("/");

        //  String log4jLocation = config.getInitParameter("log4jLocation");

        System.setProperty("webRoot", root); 
    }
}
