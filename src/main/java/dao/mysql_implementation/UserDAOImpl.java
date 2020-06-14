package dao.mysql_implementation;

import dao.sql_interface.UserDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpa.User;
import jpa.UserType;

/**
 * The UserDAOImpl class defines a data access object (DAO) that accesses the 
 * Users table in the Musically database.
 * 
 * @author P. Bellefleur
 * @author Cerba Mihail
 */
@SessionScoped
public class UserDAOImpl extends JpaDaoImpl<User> 
        implements UserDAO, Serializable {
    
    public UserDAOImpl() {
        super(User.class);
    }
    
    /**
     * Finds all entries in the table, and stores them as User objects in a 
     * List.
     * 
     * @return  a List of Users, containing all users retrieved from the 
     * database.
     */
    @Override 
    public List<User> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " 
                + entityClass.getName() + " e");
        return (List<User>) q.getResultList();
    }
    
    /**
     * This method creates user with user type "Client"
     * 
     * @param user User Entity to add to Database 
     * @return user with updated Id from database 
     */
        @Override 
    public User createClient(User user) {

		UserType userType = new UserType();
		userType.setType("client");
                userType.setUserEmail(user.getEmail());
                user.setUserType(userType);
		user = persist(user);
		return user;
	}
  
    /**
     * This method creates user with user type "Client"
     * 
     * @param user User Entity to add to Database 
     * @return user with updated Id from database 
     */
        @Override 
    public User createManager(User user) {
		UserType userType = new UserType();
		userType.setType("manager");
                userType.setUserEmail(user.getEmail());
                user.setUserType(userType);
		user = persist(user);
		return user;
	}
    
    
    /**
     * This method find user by it id 
     * @param id long ID of User to find 
     * @return User Entity 
     */
        @Override 
    public User findUserById(long id) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findById", User.class);
		query.setParameter("id", id);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			// getSingleResult throws NoResultException in case there is no user in DB
			// ignore exception and return NULL for user instead
		}
		return user;
	}
    
    /**
     * This method check if user already exist with the same email 
     * @param email String to find the user 
     * @return boolean 
     * true if a user with this email already exists 
     * false if a user with this email does not exist 
     */
        @Override 
    public boolean isEmailExists(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
                    User userFromDB = null;
                    try {
                            userFromDB = query.getSingleResult();
                    } catch (Exception e) {
                            // getSingleResult throws NoResultException in case there is no user in DB
                            // ignore exception and return NULL for user instead
                    }
                if (userFromDB == null) return false ;
		return true;
	}
    
    
        /**
     * This method check if user already exist with the email 
     * If exists , return user
     * If no exists, return null
     * @param email String to find the user 
     * @return boolean 
     * true if a user with this email already exists 
     * false if a user with this email does not exist 
     */
        @Override 
    public User findUserByEmail(String email) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("email", email);
                    User userFromDB = null;
                    try {
                            userFromDB = query.getSingleResult();
                    } catch (Exception e) {
                            // getSingleResult throws NoResultException in case there is no user in DB
                            // ignore exception and return NULL for user instead
                    }
		return userFromDB;
	}
    
}
