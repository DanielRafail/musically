package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.OrderCart;
import jpa.User;

/**
 * The OrderDAO interface defines the methods that must be contained within an 
 * implementation of an Order data access object (DAO).
 * 
 * @author P. Bellefleur
 */
public interface OrderDAO extends Dao<OrderCart>{
    List<OrderCart> findAll();
    OrderCart findLastOrder(User user);
    List<OrderCart> findAllFinalized();
}
