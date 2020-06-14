package dao.mysql_implementation;

import dao.sql_interface.OrderDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.OrderCart;
import jpa.User;

/**
 * The UserDAOImpl class defines a data access object (DAO) that accesses the 
 * Orders table in the Musically database.
 * 
 * @author P. Bellefleur
 * @author Johnny Lin
 */
@SessionScoped
public class OrderDAOImpl extends JpaDaoImpl<OrderCart> 
        implements OrderDAO, Serializable {

    public OrderDAOImpl() {
        super(OrderCart.class);
    }
    
    /**
     * Finds all entries in the table, and stores them as Order objects in a 
     * List.
     * 
     * @return a List of Orders, containing all user types retrieved from 
     * the database.
     */
    @Override
    public List<OrderCart> findAll() { 
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<OrderCart>) q.getResultList();
    }

    /**
     * Finds last order from the specified user.
     * 
     * @return the last order from the specified user
     */
    @Override
    public OrderCart findLastOrder(User user) {
        //check user
        if (user == null) {
            //System.out.println("findLastOrder: USER IS NULL");
            return null;
        }
        
        //query db
        Query q = entityManager.createQuery("SELECT e FROM OrderCart e where e.userId = :userId order by e.id desc");
        q.setParameter("userId", user);
        List<OrderCart> list = (List<OrderCart>) q.getResultList();
        if (list.isEmpty()) {
            //System.out.println("findLastOrder: LIST IS NULL");
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * Finds all finalized Orders by descending order.
     * 
     * @return list of Orders
     */
    @Override
    public List<OrderCart> findAllFinalized() {
        Query q = entityManager.createQuery("SELECT e FROM OrderCart e where e.isFinalized = true order by e.id desc");
        return (List<OrderCart>) q.getResultList();
    }
}
