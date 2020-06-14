package dao.mysql_implementation;

import dao.sql_interface.UserTypeDAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.UserType;

/**
 * The UserDAOImpl class defines a data access object (DAO) that accesses the 
 * User Types table in the Musically database.
 * 
 * @author P. Bellefleur
 */
@SessionScoped
public class UserTypeDAOImpl extends JpaDaoImpl<UserType> 
        implements UserTypeDAO, Serializable{  

    public UserTypeDAOImpl() {
        super(UserType.class);
    }

    /**
     * Finds all entries in the table, and stores them as UserType objects in a 
     * List.
     * 
     * @return a List of UserTypes, containing all user types retrieved from 
     * the database.
     */
    @Override
    public List<UserType> findAll() {
        Query q = this.entityManager.createQuery("SELECT e FROM " 
                + entityClass.getName() + " e");
        return (List<UserType>) q.getResultList();
    }
    
}
