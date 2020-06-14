package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.User;

/**
 * The UserDAO interface defines the methods that must be contained within an 
 * implementation of a User data access object (DAO).
 * 
 * @author P. Bellefleur
 */
public interface UserDAO extends Dao<User>{
        /**
     * Finds all entries in the table, and stores them as User objects in a 
     * List.
     * 
     * @return  a List of Users, containing all users retrieved from the 
     * database.
     */
    public List<User> findAll();
        
    /**
     * This method creates user with user type "Client"
     * 
     * @param user User Entity to add to Database 
     * @return user with updated Id from database 
     */
    public User createClient(User user);
    
    /**
     * This method creates user with user type "Client"
     * 
     * @param user User Entity to add to Database 
     * @return user with updated Id from database 
     */
    public User createManager(User user);
    
    /**
     * This method find user by it id 
     * @param id long ID of User to find 
     * @return User Entity 
     */
    
    public User findUserById(long id);
    
        /**
     * This method check if user already exist with the same email 
     * @param email String to find the user 
     * @return boolean 
     * true if a user with this email already exists 
     * false if a user with this email does not exist 
     */
    public boolean isEmailExists(String email);
    
    
     /**
     * This method check if user already exist with the email 
     * If exists , return user
     * If no exists, return null
     * @param email String to find the user 
     * @return boolean 
     * true if a user with this email already exists 
     * false if a user with this email does not exist 
     */
    public User findUserByEmail(String email);
    
}
