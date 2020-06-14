package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.UserType;

/**
 * The UserTypeDAO interface defines the methods that must be contained within 
 * an implementation of a UserType data access object (DAO).
 * 
 * @author P. Bellefleur
 */
public interface UserTypeDAO extends Dao<UserType> {
    List<UserType> findAll();
}
