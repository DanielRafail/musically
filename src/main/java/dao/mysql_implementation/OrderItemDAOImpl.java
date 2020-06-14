package dao.mysql_implementation;

import dao.sql_interface.OrderItemDAO;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import jpa.OrderCart;
import jpa.OrderItem;
import jpa.User;

/**
 * The UserDAOImpl class defines a data access object (DAO) that accesses the
 * Order Items table in the Musically database.
 *
 * @author P. Bellefleur
 * @author Johnny Lin
 */
@SessionScoped

public class OrderItemDAOImpl extends JpaDaoImpl<OrderItem>
        implements OrderItemDAO, Serializable {

    public OrderItemDAOImpl() {
        super(OrderItem.class);
    }

    /**
     * Finds all entries in the table, and stores them as OrderItem objects in a
     * List.
     *
     * @return a List of OrderItems, containing all order items retrieved from
     * the database.
     */
    @Override
    public List<OrderItem> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<OrderItem>) q.getResultList();
    }

    /**
     * Finds all order items in an order.
     *
     * @param order order id
     * @return order items
     */
    @Override
    public List<OrderItem> findByOrderId(OrderCart order) {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e where e.orderId = :orderId");
        q.setParameter("orderId", order);
        List<OrderItem> list = q.getResultList();
        return list;
    }

    /**
     * delete all order items in a specific order.
     * 
     * @param order order
     * @return deleted item count
     */
    @Override
    public int deleteAllByOrderId(OrderCart order) {
        try {
            this.userTransaction.begin();
            Query q = entityManager.createQuery("DELETE FROM " + entityClass.getName() + " e where e.orderId = :orderId");
            q.setParameter("orderId", order);
            int rowsAffected = q.executeUpdate();
            this.userTransaction.commit();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("deleteAllByOrderId: delete unsuccessful.");
            return 0;
        }

        
    }

    /**
     * Delete specified order item.
     * 
     * @param orderItem order item
     * @return deleted order item count
     */
    @Override
    public int deleteByOrderItemId(OrderItem orderItem) {
        try {
            this.userTransaction.begin();
            Query q = entityManager.createQuery("DELETE FROM " + entityClass.getName() + " e where e.id = :orderItemId");
            q.setParameter("orderItemId", orderItem.getId());
            int rowsAffected = q.executeUpdate();
            this.userTransaction.commit();
            return rowsAffected;
        } catch (Exception e) {
            System.out.println("deleteByOrderItemId: delete unsuccessful");
            return 0;
        }
    }
    
    /**
     * Finds all purchased items from the specified user.
     * 
     * @param user user
     * @return purchased items
     */
    @Override
    public List<OrderItem> findAllFrom(User user) {
        Query q = entityManager.createQuery("SELECT e FROM OrderItem e where e.orderId.userId = :userId and e.orderId.isFinalized = true and e.disabled = false order by e.id desc");
        q.setParameter("userId", user);
        List<OrderItem> list = q.getResultList();
        return list;
    }
}
