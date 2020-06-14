/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.sql_interface.OrderDAO;
import dao.sql_interface.OrderItemDAO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.OrderCart;
import jpa.OrderItem;
import utility.TaxesManager;

/**
 * AllOrdersBean is the bean used to manage the Order Management page for
 * managers
 * 
 * @author Johnny Lin
 */
@Named
@SessionScoped
public class AllOrdersBean implements Serializable {

    @Inject
    private OrderDAO orderDao;
    @Inject
    private OrderItemDAO orderItemDao;

    private List<OrderCart> orders;
    private java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
    private String afterDate = "31-12-0000";
    private String beforeDate = dateFormatter.format(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1))); //today's date + 1 day
    
    /**
     * Gets all orders that are finalized and filters between 2 date values.
     * (Default date values are 31-Dec-0000 and tomorrow's date)
     */
    public void getAllOrders() {
        this.orders = this.orderDao.findAllFinalized();
        applyDateFilters();
    }

    /**
     * Filters the orders that are completed between the 2 date values
     */
    private void applyDateFilters() {
        if ((!this.afterDate.equals("31-12-0000")
                || !this.beforeDate.equals(dateFormatter.format(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)))))
                && this.afterDate.length() == 10 && this.beforeDate.length() == 10
                && this.afterDate.charAt(2) == '-' && this.afterDate.charAt(5) == '-'
                && this.beforeDate.charAt(2) == '-' && this.beforeDate.charAt(5) == '-') {
            try {
                Date before = this.dateFormatter.parse(this.beforeDate); //today
                Date after = this.dateFormatter.parse(this.afterDate); //year 0
                
                List<OrderCart> allOrders = this.orders;
                this.orders = new ArrayList<>();
                
                for (OrderCart order : allOrders) {
                    if (order.getSaleDate().after(after) && order.getSaleDate().before(before)) {
                        this.orders.add(order);
                    }
                }
                
                
            } catch (ParseException pe) {
                //do not filter and reset dates
                this.afterDate = "31-12-0000";
                this.beforeDate = this.dateFormatter.format(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
            }
        } else {
            this.afterDate = "31-12-0000";
            this.beforeDate = this.dateFormatter.format(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1)));
        }
    }
    
    /**
     * Disables the entire order's items.
     * 
     * @param order order
     * @return orders management page
     */
    public String disableOrder(OrderCart order) {
        for (OrderItem orderItem : order.getOrderItemCollection()) {
            disableOrderItem(orderItem);
        }
        return "orders";
    }
    
    /**
     * Disable an order item and update's the subtotal, tax, and total.
     * 
     * @param orderItem order item
     * @return orders management page
     */
    public String disableOrderItem(OrderItem orderItem) {
        //get tax rate for the order
        double taxRate = TaxesManager.getInstance().getTax(orderItem.getOrderId().getUserId().getProvince());
        
        //will do the mathematical way for now. (no longer needed)
        //double taxRate = currencyRound((orderItem.getOrderId().getGrossValue().doubleValue() / orderItem.getOrderId().getSubTotal().doubleValue()) - 1.0);
        //System.out.println("TAX RATE FOR ITEM: " + orderItem.getId() + " IS " + taxRate);
        //this should give back about 0.14975
        
        //update 3 price values
        OrderCart order = orderItem.getOrderId();
        order.setGrossValue(BigDecimal.valueOf(currencyRound(order.getGrossValue().doubleValue() - (orderItem.getPrice().doubleValue()*(1.0+taxRate)))));
        order.setSubTotal(BigDecimal.valueOf(currencyRound(order.getSubTotal().doubleValue() - (orderItem.getPrice().doubleValue()))));
        order.setHst(BigDecimal.valueOf(currencyRound(order.getHst().doubleValue() - (orderItem.getPrice().doubleValue()*taxRate))));
        
        //disable order item
        this.orderDao.update(order);
        orderItem.setDisabled(true);
        this.orderItemDao.update(orderItem);
        
        return "orders";
    }
    
    /**
     * Rounds doubles to currency (2 decimal point)
     * 
     * @param d double value
     * @return currency rounded value
     */
    private double currencyRound(double d) {
        return Math.round(d*100.0)/100.0;
    }
    
    //standard getters and setters
    public List<OrderCart> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderCart> orders) {
        this.orders = orders;
    }

    public String getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(String beforeDate) {
        this.beforeDate = beforeDate;
    }

    public String getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(String afterDate) {
        this.afterDate = afterDate;
    }

}
