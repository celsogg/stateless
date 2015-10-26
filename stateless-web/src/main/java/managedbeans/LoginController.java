/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Usuario;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author miguel
 */
@ManagedBean
@SessionScoped
@Named("loginController")
public class LoginController {

    private String username;
    private String nombreMostrado;
    private String nombre;
    private String password;
    private String rol;
    private boolean isLoggedIn;
    private String originalURL;
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    @EJB
    private UsuarioFacadeLocal userService;

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

    public String login() throws IOException, ServletException, JSONException, ParseException{
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String navto = "";
        String message = "";
        setUsername(this.username);
        List<Usuario> results;
//        results = userService.findUsuarioByUid(request.getUserPrincipal().getName());
        results = userService.findUsuarioByUid(this.username);
        Usuario user;
        if (!results.isEmpty()) {
            user = results.get(0);
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://inicio.diinf.usach.cl/webservice.php");

                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("user", username));
                nameValuePairs.add(new BasicNameValuePair("pass", password));
                nameValuePairs.add(new BasicNameValuePair("keyapi", MD5("c55ecd5c60a5a5b2bea1c92bbc45f8ab")));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

                String responseString = new BasicResponseHandler().handleResponse(response);

            //JSONParser parser = new JSONParser();
                //Object obj = parser.parse(responseString);
                JSONObject jsonObject = new JSONObject(responseString);

                Boolean valido_response = (Boolean) jsonObject.get("pass_ok");
                if (valido_response == null) {
                    valido_response = false;
                }

                if (valido_response) {
                    this.rol = user.getRol();
                    this.nombre = user.getNombre();

                    if ("".equals(this.rol)) {
                        this.rol = "estudiante";
                        setNombreMostrado(this.nombre);
                    }
                    if (this.rol.equalsIgnoreCase("administrador")) {
                        setNombreMostrado(this.nombre);
                        message = "Username: " + this.nombre + " You are administrator";
                        navto = "/index.xhtml";
                    }
                }
            }catch(IOException e){
                context.addMessage(null, new FacesMessage("Ingreso Erróneo", "Los datos ingresados no son correctos."));
            }
        } else {
            setNombreMostrado(getUsername());
            message = "Username: " + this.nombre + " You are student";
            navto = "/index.xhtml";
        }
        LOGGER.info("El usuario " + this.nombre + " ha iniciado sesión");
        return navto;
//        return "";
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        LOGGER.info("El usuario ha cerrado sesión");
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }

    // Getters/setters for username and password.
    private Principal getLoggedInUser() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
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
        System.out.println("Rol: " + this.rol);
        return "administrador".equals(this.rol);
    }

    public String getNombreMostrado() {
        return nombreMostrado;
    }

    public void setNombreMostrado(String nombreMostrado) {
        this.nombreMostrado = nombreMostrado;
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
