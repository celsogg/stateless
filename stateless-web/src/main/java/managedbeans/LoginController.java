/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Usuario;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import sessionbeans.UsuarioFacadeLocal;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
@Named("loginController")
public class LoginController {

    private String username;
    private String password;
    private String rol;
    private boolean isLoggedIn;
    private String originalURL;
    final static Logger logger = Logger.getLogger(LoginController.class);
    
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/home.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    @EJB
    private UsuarioFacadeLocal userService;

    public String login() throws IOException, ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String navto = "";
        String message = "";
        try {
            request.login(this.username, this.password);
            setUsername(this.username);
            //System.out.println(this.username);
            List<Usuario> results;
            results = userService.findUsuarioByUid(request.getUserPrincipal().getName());
            Usuario user;
            //MDC.getContext().clear();
            //MDC.put("user", request.getUserPrincipal().getName());
            
            
            if (!results.isEmpty()) {
                user = results.get(0);
                this.rol = user.getRol();
                if("".equals(this.rol)){
                    this.rol = "estudiante";
                }
                if (this.rol.equalsIgnoreCase("administrador")) {
                    message = "Username: " + request.getUserPrincipal().getName() + " You are administrator";
                    navto = "/index.xhtml";
                }
            } else {
                message = "Username: " + request.getUserPrincipal().getName() + " You are student";
                navto = "/index.xhtml";
            }

//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            logger.info("El usuario "+ request.getUserPrincipal().getName()+" ha iniciado sesión");
            return navto;
        } catch (ServletException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error ocurred: login failed", null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, compruebe sus credenciales o contacte al administrador.", null));
        }
        return "";
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        logger.info("El usuario ha cerrado sesión");
        //MDC.remove("user");
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }

    // Getters/setters for username and password.
    private Principal getLoggedInUser() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    public void isUserNotLogin() {
        Principal loginUser = getLoggedInUser();
        if (loginUser == null) {
            setIsLoggedIn(true);

        } else {

            setIsLoggedIn(false);
        }

    }

    public void getLoginUserName() {
        Principal loginUser = getLoggedInUser();
        if (loginUser != null) {
            setUsername(loginUser.getName());
        }

    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean esAdmin() {
        return "administrador".equals(this.rol);
    }

    public Boolean noEsAdmin() {
        return !"administrador".equals(this.rol);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    public void setIsLoggedIn(boolean isloggedIn) {
        this.isLoggedIn = isloggedIn;
    }

}
