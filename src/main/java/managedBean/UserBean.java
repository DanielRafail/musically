package managedBean;

import dao.mysql_implementation.UserDAOImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.OrderCart;
import jpa.User;

/**
 * The UserBean class is a JSF managed bean responsible for representing a 
 * specific user of the store, and their account information. 
 * 
 * @author P. Bellefleur
 */

@RequestScoped
@Named(value = "userBean")
public class UserBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    UserDAOImpl userDao;
    
    private User user = new User();
    private BigDecimal totalPurchases = BigDecimal.ZERO;
    private Long userID = (long)0;
    private boolean isValidSearch = false;
    
    public UserBean() {
        
    }
    
    public User getUser() {
        return this.user;
    }
    
    public BigDecimal getTotalPurchases() {
        return this.totalPurchases;
    }
    
    public void setTotalPurchases(BigDecimal totalPurchases) {
        this.totalPurchases = totalPurchases;
    }
    
    public long getUserID() {
        return this.userID;
    }
    
    public void setUserID(long userID) {
        this.userID = userID;
    }
    
    public boolean getIsValidSearch() {
        return isValidSearch;
    }

    public void setIsValidSearch(boolean isValidSearch) {
        this.isValidSearch = isValidSearch;
    }
    
    /**
     * Finds a user in the database based on the email entered.
     */
    public void getUserFromSearch() {
        user = userDao.findUserByEmail(user.getEmail());
        this.userID = user.getId();
        for (OrderCart allCarts : user.getOrderCartCollection()) {
            //add all purchases made to the totalPurchases value to find total
            totalPurchases = totalPurchases.add(allCarts.getGrossValue());
        }
    }
    
    /**
     * Sets up the appropriate user object by searching for its ID in the 
     * database.
     */
    public void setup() {
        this.user = userDao.findUserById(userID);
    }
    
    /**
     * Validates a user's input to ensure it is a valid email address.
     * 
     * @param context   The current FacesContext
     * @param component The UI component with the user's input
     * @param value The value entered by the user
     */
    public void validateEmail(FacesContext context, UIComponent component, 
            Object value) {
        String email = (String) value;
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        
        if (email == null || !email.matches(pattern)) {
            String notValid = context.getApplication().evaluateExpressionGet(context, "Email is not valid", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, notValid, notValid);
            isValidSearch = false;
            throw new ValidatorException(msg);
        }
        
        if (!userDao.isEmailExists(email)) {
            String doesNotExist = context.getApplication().evaluateExpressionGet(context, "No user found", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, doesNotExist, doesNotExist);
            isValidSearch = false;
            throw new ValidatorException(msg);
        }
        
        isValidSearch = true;
    }
    
}
