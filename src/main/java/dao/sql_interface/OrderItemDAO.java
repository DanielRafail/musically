package dao.sql_interface;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import jpa.OrderCart;
import jpa.OrderItem;
import jpa.User;

/**
 * The OrderItemDAO interface defines the methods that must be contained within 
 * an implementation of an OrderItem data access object (DAO).
 * 
 * @author P. Bellefleur
 * @author Johnny Lin
 */
public interface OrderItemDAO extends Dao<OrderItem>{
    List<OrderItem> findAll();
    List<OrderItem> findByOrderId(OrderCart order);
    int deleteAllByOrderId(OrderCart order);
    int deleteByOrderItemId(OrderItem orderItem);
    List<OrderItem> findAllFrom(User user);
}
