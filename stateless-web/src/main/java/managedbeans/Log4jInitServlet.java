/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author miguel
 */

public class Log4jInitServlet extends HttpServlet {
    
    /**
     *
     * @param event
     */
    public void contextInitialized (ServletContextEvent event){ 
        ServletContext context = event.getServletContext();
        System.setProperty("rootPath", context.getRealPath("/")); 
    }
}
