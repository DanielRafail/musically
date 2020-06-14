package managedBean;

import dao.mysql_implementation.UserDAOImpl;
import dao.mysql_implementation.UserTypeDAOImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import jpa.User;
import jpa.UserType;
import utility.Authentication;

/**
 * This class do data validation and creation new user from registration form. 
 * @author Cerba Mihail, Peter Bellefleur
 */

@Named
@SessionScoped
public class RegistrationBean implements Serializable{
    
    @Inject
    UserDAOImpl userDAOImpl;
    
    @Inject
    UserTypeDAOImpl typeDAOImpl;
    
    //TODO replase constructor to @Inject anotation 
    private User user = new User();
    
    // The value for the confirmPassword field that does not exist in the entity
    private String passwordConfirm;
    
    //User ID for the DAO to retrieve when editing user information
    private long userID = (long)0;
    
    //Boolean value for editing user information to represent manager status
    private boolean isManager = false;
    
    
    /**
     * Return the User model that has been injected into this backing bean
     *
     * @return The User object
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Getter for the password confirm field
     *
     * @return
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Setter for the password confirm field
     *
     * @param passwordConfirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public long getUserID() {
        return this.userID;
    }
    
    public void setUserID(long userID) {
        this.userID = userID;
    }
    
    public boolean getIsManager() {
        return this.isManager;
    }
    
    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }
    
        /**
     * The method that compares the two password fields correctly
     *
     * @param context
     * @param component
     * @param value
     */
    public void validatePasswordConfirmation(FacesContext context, UIComponent component,
            Object value) {

        try {
            this.passwordConfirm = Authentication.encodeSHA256((String) value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        if (this.user.getHashedPassword() == null || this.passwordConfirm == null || !this.user.getHashedPassword().equals(this.passwordConfirm)) {
            String message = context.getApplication().evaluateExpressionGet(context, "Password and Confirm Password not match", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            throw new ValidatorException(msg);
        }
    }
    
    /**
     * The method that validate password and store it in the user object
     *
     * @param context
     * @param component
     * @param value
     */
    public void validatePassword(FacesContext context, UIComponent component,
            Object value) {
        String password = (String) value;

        
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";

        if (password == null || !password.matches(pattern)) {
            String message = context.getApplication().evaluateExpressionGet(context, "Password should include: "
                    + "A digit,"
                    + "a lower case letter, "
                    + "an upper case letter and be "
                    + "at least 8 characters!", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            throw new ValidatorException(msg);
        }
        try {
            this.user.setHashedPassword(Authentication.encodeSHA256(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * The method that validate email
     *
     * @param context
     * @param component
     * @param value
     */
    public void validateEmail(FacesContext context, UIComponent component,
            Object value) {
        String email = (String)value;
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";    

        if (email == null || !email.matches(pattern)) {
            String notValid = context.getApplication().evaluateExpressionGet(context, "Email is not valid", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, notValid, notValid);
            throw new ValidatorException(msg);
        }

        if ((userDAOImpl.isEmailExists(email)) && (!email.equals(user.getEmail()))){
            String AlredyExists = context.getApplication().evaluateExpressionGet(context, "User with this e-mail already exists", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, AlredyExists, AlredyExists);
            throw new ValidatorException(msg);
        }

    }

    /**
     * This method add client to the database and set JustRegistered session map value as true
     * After registration it redirects back to return URL 
     */
    public void register() {
        userDAOImpl.createClient(user);
        user = new User();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String returnUrl = request.getRequestURI();
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(returnUrl);
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("JustRegistered", true);
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prepares a user object to be displayed on the "edit user" page. 
     */
    public void setupForEditInfo() {
        this.user = userDAOImpl.findUserById(userID);
        System.out.println("user type: " + user.getUserType().getId());
        if (user.getUserType().getType().equals("manager")) {
            isManager = true;
            System.out.println("isManager set to true");
        }
    }
    
    /**
     * Updates a user's information based on user input, persists it to the 
     * database, then redirects to the client search page.
     */
    public void editClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        UserType toSet = user.getUserType();
        System.out.println("isManager is: " + isManager);
        if (isManager) {
            toSet.setType("manager");
        } else {
            toSet.setType("client");
        }
        user.setUserType(toSet);
        
        System.out.println("user type is now: " + user.getUserType().getType());
        
        typeDAOImpl.update(toSet);
        userDAOImpl.update(user);
        
        try {
            externalContext.redirect("clients.xhtml");
        } catch (IOException ioe) {
            
        }
        
    }
}
