package managedBean;

import javax.faces.context.FacesContext;
import dao.mysql_implementation.UserDAOImpl;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jpa.User;

/**
 * This class checks if user with code and password exists. If yes, it create a
 * session with this user.
 *
 * @author Cerba Mihail
 */
@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    UserDAOImpl userDAOImpl;

    private User user;
    private String email;
    private String password;

    /**
     * This method use request.login method to check if user with email and
     * password exists. It login successful it creates user entity and add it to
     * ExternalContext SessionMap. After it redirects back to return URL.
     *
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return;
        }
        String returnUrl = request.getRequestURI();


        user = userDAOImpl.findUserByEmail(email);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);

        try {
            externalContext.redirect(returnUrl);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method invalidate(close) HTML session
     *
     * @return String - redirection URL
     */
    public String logout() {
        FacesContext context
                = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request
                = (HttpServletRequest) ec.getRequest();
        // false means not to create a session if
        // it doesn't exist already
        request.getSession(false).invalidate();
        return "index?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
