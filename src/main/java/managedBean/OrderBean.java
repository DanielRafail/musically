package managedBean;

import dao.sql_interface.AlbumDAO;
import dao.sql_interface.OrderDAO;
import dao.sql_interface.OrderItemDAO;
import dao.sql_interface.TrackDAO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.OrderCart;
import jpa.OrderItem;
import jpa.Track;
import jpa.User;
import utility.TaxesManager;

/**
 * Managed bean with all necessary functionality for Orders Pages:
 * Cart/Checkout/Download song.
 *
 * @author Johnny Lin
 */
@Named
@SessionScoped
public class OrderBean implements Serializable {

    private User user;
    private OrderCart orderCart;

    @Inject
    private OrderDAO orderDao;
    @Inject
    private OrderItemDAO orderItemDao;
    @Inject
    private TrackDAO trackDao;
    @Inject
    private AlbumDAO albumDao;

    private double subTotal;

    private String cc_name;
    private String cc_number;
    private String cc_cvc;
    private String cc_expiry;

    /**
     * Default constructor for the OrderBean.
     */
    public OrderBean() {
    }

    /**
     * Add album to cart. Only works if the user is logged on. Any error when
     * adding to cart will result in sending the user to the 404 page.
     *
     * @return redirect to cart page
     */
    public String addAlbumToCart() {
        this.getExistingCart();
        //check if user is logged
        if (user == null) {
            //System.out.println("addAlbumToCart: User is not logged in!");
            return "cart";
        }

        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("album_id");
        //System.out.println("addAlbumToCart: Param-> " + param);

        long id = 0;
        try {
            id = Long.parseLong(param);
        } catch (NumberFormatException nfe) {
            //System.out.println("addAlbumToCart: FAILED TO ADD ALBUM TO CART " + nfe.getMessage());

            return "404";
        }

        //System.out.println("addAlbumToCart: id="+id);
        //Checks:
        if (id <= 0) {
            return "404";
        }

        //System.out.println(albumDao.toString());
        Album album = albumDao.findById(id);
        if (album == null) {
            //System.out.println("Album does not exist! BIG YIKES!");
            return "404";
        }

        //System.out.println("Got the album, now going to add it in cart");
        OrderItem item = new OrderItem();
        item.setAlbumId(album);
        item.setTrackId(null);
        if (album.getSalePrice() != null && album.getSalePrice().doubleValue() != 0) {
            //System.out.println("setting sale price...");
            item.setPrice(album.getSalePrice());
        } else {
            //System.out.println("setting list price...");
            item.setPrice(album.getListPrice());
        }
        item.setOrderId(orderCart);
        this.orderItemDao.persist(item);
        //System.out.println("Persisted!");

        //update subtotal
        //System.out.println("Subtotal Updating...");
        //System.out.println("SUBTOTAL = " + this.orderCart.getSubTotal().doubleValue() + " + "+ item.getPrice().doubleValue());
        this.subTotal = this.subTotal + item.getPrice().doubleValue();
        this.orderCart.setSubTotal(BigDecimal.valueOf(subTotal));
        this.orderDao.update(orderCart);
        //System.out.println("updated subtotal!");
        return "cart";
    }

    /**
     * Add Track to cart. Only works if the user is logged on. Any error when
     * adding to cart will result in sending the user to the 404 page.
     *
     * @return cart page
     */
    public String addTrackToCart() {
        this.getExistingCart();
        //check if user is logged
        if (user == null) {
            //System.out.println("addAlbumToCart: User is not logged in!");
            return "cart";
        }

        String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("track_id");
        //System.out.println("addAlbumToCart: Param-> " + param);

        long id = 0;
        try {
            id = Long.parseLong(param);
        } catch (NumberFormatException nfe) {
            //System.out.println("addAlbumToCart: FAILED TO ADD ALBUM TO CART " + nfe.getMessage());

            return "404";
        }

        //System.out.println("addTrackToCart: id=" + id);
        //Checks:
        if (id <= 0) {
            return "404";
        }

        Track track = trackDao.findById(id);
        if (track == null) {
            return "404";
        }

        OrderItem item = new OrderItem();
        item.setAlbumId(null);
        item.setTrackId(track);
        if (track.getSalePrice() != null && track.getSalePrice().doubleValue() != 0) {
            item.setPrice(track.getSalePrice());
        } else {
            item.setPrice(track.getListPrice());
        }
        item.setOrderId(orderCart);
        this.orderItemDao.persist(item);

        //update subtotal.
        //System.out.println("Subtotal Updating...");
        this.subTotal += item.getPrice().doubleValue();
        this.orderCart.setSubTotal(BigDecimal.valueOf(subTotal));
        this.orderDao.update(orderCart);
        //System.out.println("updated subtotal!");

        return "cart";
    }

    /**
     * Gets the user object if the user is logged on. Checks if there is an
     * existing cart in the database and gets its data, else it will create a
     * new empty cart for the user. Will fetch all existing cart items if there
     * are any and update the subtotal according to sale or list price.
     */
    public void getExistingCart() {
        System.out.println("start of getExistingCart()");
        this.orderCart = null;
        //get logged in user 
        FacesContext fc = FacesContext.getCurrentInstance();
        this.user = (User) fc.getExternalContext().getSessionMap().getOrDefault("User", null);
        if (this.user == null) {
            //System.out.println("getExistingCart: User is null when getting cart.");
            //since user is null, do not execute below
            return;
        } else {
            //System.out.println("HELLO " + this.user.getEmail());
        }

        //get last order
        orderCart = orderDao.findLastOrder(user);

        //if last order doesn't exist or is completed
        if (orderCart == null || orderCart.getIsFinalized()) {
            //System.out.println("orderCart is null and will make a new");
            orderCart = new OrderCart();
            this.orderCart.setGrossValue(BigDecimal.ZERO);
            this.orderCart.setGst(BigDecimal.ZERO);
            this.orderCart.setHst(BigDecimal.ZERO);
            this.orderCart.setIsFinalized(false);
            this.orderCart.setPst(BigDecimal.ZERO);
            this.orderCart.setSaleDate(new Date());
            this.orderCart.setSubTotal(BigDecimal.ZERO);
            this.orderCart.setOrderItemCollection(new ArrayList<>());
            this.orderCart.setUserId(user);

            orderCart = orderDao.persist(orderCart);

            //System.out.println("PERSISTED!!!");
        }

        List<OrderItem> items = this.orderItemDao.findByOrderId(orderCart);
        //System.out.println("items in cart: " + items);
        orderCart.setOrderItemCollection(items);

        //set the prices to sale or no longer on sale
        if (items != null || !items.isEmpty()) {
            //System.out.println("Setting prices to sale/not on sale items");
            for (OrderItem item : items) {
                //System.out.println("FOR ITEM: " + item.toString());
                if (item.isAlbum()) {
                    Album a = this.albumDao.findById(item.getAlbumId().getId());
                    if (a.isOnSale()) {
                        item.setPrice(a.getSalePrice());
                    } else {
                        item.setPrice(a.getListPrice());
                    }
                    a = null;
                } else {
                    Track t = this.trackDao.findById(item.getTrackId().getId());
                    if (t.isOnSale()) {
                        item.setPrice(t.getSalePrice());
                    } else {
                        item.setPrice(t.getListPrice());
                    }
                    t = null;
                }
            }
        }
        //set subtotal for the order
        getPrice();

        //verbose to current cart
        /*if (orderCart != null) {
            System.out.println("orderCart obj: " + orderCart.toString());
        } else {
            System.out.println("orderCart obj: NULL");
        }*/
        //set the sale and  prices
    }

    /**
     * Get all OrderItems from the cart. If the user is not logged on, it will
     * return an empty list.
     *
     * @return OrderItems
     */
    public Collection<OrderItem> getOrderItems() {

        //if user is not logged in, return nothing
        if (this.user == null) {
            return new ArrayList<>();
        }
        return this.orderCart.getOrderItemCollection();

        /*
        Artist artist = new Artist();
        artist.setArtistName("ITZY");
        Collection<Artist> artistCollection = new ArrayList<>();
        artistCollection.add(artist);
        
        Collection<OrderItem> items = new ArrayList<>();
        Album album = new Album();
        album.setListPrice(BigDecimal.valueOf(12.99));
        //album.setTitle("Love Yourself: Answer");
        album.setTitle("IT'z Different");
        album.setArtistCollection(artistCollection);
        
        
        Track track = new Track();
        track.setListPrice(BigDecimal.valueOf(1.39));
        //track.setTitle("IDOL");
        track.setTitle("DALLA DALLA");
        track.setAlbumId(album);
        Collection<Track> tracks = new ArrayList<>();
        tracks.add(track);
        
        OrderItem oi1 = new OrderItem();
        OrderItem oi2 = new OrderItem();
        
        oi1.setAlbumId(album);
        oi1.setIsAlbum(true);
        oi1.setPrice(BigDecimal.valueOf(12.99));
        
        oi2.setTrackId(track);
        oi2.setIsAlbum(false);
        oi2.setPrice(BigDecimal.valueOf(1.39));
        
        
        items.add(oi1);
        items.add(oi2);
        return items;*/
    }

    /**
     * Empties the cart and deletes all items in cart from database.
     *
     * @return cart web page
     */
    public String emptyCart() {
        //System.out.println("emptyCart");
        if (this.user == null) {
            //System.out.println("user not logged in");
            return "cart";
        }

        //update subtotal
        this.orderCart.setSubTotal(BigDecimal.ZERO);
        this.subTotal = 0;
        orderDao.update(orderCart);

        int rowsAffected = this.orderItemDao.deleteAllByOrderId(orderCart);
        //System.out.println("emptyCart: DELETED ROWS=" + rowsAffected);

        return "cart";
    }

    /**
     * Get sub-total value of the order
     *
     * @return sub-total price
     */
    public BigDecimal getPrice() {
        if (user == null) {
            //System.out.println("getPrice: User is not logged on");
            return BigDecimal.ZERO;
        }

        //new subtotal
        this.subTotal = 0.0;
        for (OrderItem orderItem : this.orderCart.getOrderItemCollection()) {
            this.subTotal = Math.round(100 * (subTotal + orderItem.getPrice().doubleValue())) / 100.0;
        }

        //if subtotal different than db
        if (this.orderCart.getSubTotal().doubleValue() != this.subTotal) {
            this.orderCart.setSubTotal(BigDecimal.valueOf(this.subTotal));
            this.orderCart = this.orderDao.update(orderCart);
        }
        return this.orderCart.getSubTotal();
    }

    /**
     * Get tax value of the order
     *
     * @return tax value
     */
    public BigDecimal getTaxes() {
        double taxes = this.subTotal * TaxesManager.getInstance().getTax(this.user.getProvince());
        taxes = Math.round(taxes * 100) / 100.;
        return BigDecimal.valueOf(taxes);
    }

    /**
     * Get total value of the order
     *
     * @return total price
     */
    public BigDecimal getTotal() {

        double total = BigDecimal.valueOf(this.subTotal + getTaxes().doubleValue()).doubleValue();
        return BigDecimal.valueOf(Math.round(total * 100) / 100.0);
    }

    /**
     * Process credit card selection page if logged in or not.
     *
     * @return index or credit card page
     */
    public String creditCard() {
        if (this.user == null || this.orderCart == null || this.orderCart.getOrderItemCollection().isEmpty()) {
            return "cart";
        } else {
            return "creditcard";
        }
    }

    /**
     * Removes selected item from the user's cart.
     *
     * @param orderItem item (album or track)
     * @return cart web page
     */
    public String removeItem(OrderItem orderItem) {
        //System.out.println("removeItem: ATTEMPTING TO REMOVE-> " + orderItem.getId() + " " + orderItem.toString());

        //update subtotal
        this.subTotal -= orderItem.getPrice().doubleValue();
        this.orderCart.setSubTotal(BigDecimal.valueOf(this.subTotal));
        this.orderDao.update(orderCart);
        //System.out.println("Subtotal updated!");

        //update cart
        int rowsAffected = this.orderItemDao.deleteByOrderItemId(orderItem);
        //System.out.println("DELETED " + rowsAffected + " ROWS!");

        return "cart";
    }

    /**
     * Checks all credit card information, then if all information is entered,
     * will finalize the order. If there is a processing error, it will redirect
     * the user to the 404 page.
     *
     * @return goes to downloads page when the order is complete.
     */
    public String confirmOrder() {
        /*System.out.println("confirmOrder: clicked!");
        System.out.println("Credit card detail:");
        System.out.println(this.cc_name);
        System.out.println(this.cc_number);
        System.out.println(this.cc_cvc);
        System.out.println(this.cc_expiry);*/

        //finalize order
        //check if all credit card data entered, else return to 404
        if (this.cc_name == null || this.cc_name.equals("")
                || this.cc_number == null || this.cc_number.equals("")
                || this.cc_expiry == null || this.cc_expiry.equals("")
                || this.cc_cvc == null || this.cc_cvc.equals("")) {
            return "404";
        }
        this.orderCart.setIsFinalized(true);
        this.orderCart.setSaleDate(new Date());
        this.orderCart.setHst(this.getTaxes());
        this.orderCart.setGrossValue(this.getTotal());
        this.orderDao.update(orderCart);

        this.cc_cvc = "";
        this.cc_expiry = "";
        this.cc_name = "";
        this.cc_number = "";

        this.subTotal = 0.0;
        this.orderCart = null;
        return "downloads";
    }

    /**
     * Gets all purchased and enabled order items. Only works when logged in.
     *
     * @return purchased items.
     */
    public List<OrderItem> getBoughtOrderItems() {
        if (user == null) {
            return new ArrayList<>();
        }
        List<OrderItem> boughtItems = this.orderItemDao.findAllFrom(user);
        //System.out.println("boughtItems: " + boughtItems.size());
        return boughtItems;
    }

    //Standard getters and setters
    public String getCc_name() {
        return cc_name;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public String getCc_number() {
        return cc_number;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public String getCc_cvc() {
        return cc_cvc;
    }

    public void setCc_cvc(String cc_cvc) {
        this.cc_cvc = cc_cvc;
    }

    public String getCc_expiry() {
        return cc_expiry;
    }

    public void setCc_expiry(String cc_expiry) {
        this.cc_expiry = cc_expiry;
    }

    public User getUser() {
        return user;
    }

    /**
     * checks if the user is not logged in.
     *
     * @return not logged in
     */
    public boolean userIsNotLoggedIn() {
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates the credit card number if it matches Visa, Mastercard,
     * Discover, American Express (Not supported), Dinners or JCB.
     *
     * @param context context
     * @param component ui_component
     * @param value value
     */
    public void validateCreditCard(FacesContext context, UIComponent component, Object value) {
        String pattern = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|"
                + "(?<mastercard>5[1-5][0-9]{14})|"
                + "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|"
                + "(?<amex>3[47][0-9]{13})|"
                + "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|"
                + "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
        String card_number = (String) value;
        if (card_number == null || !card_number.matches(pattern)) {
            //System.out.println("not valid cc_number: " + card_number);
            String notValid = context.getApplication().evaluateExpressionGet(context, "Credit card is not valid (no spaces)", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, notValid, notValid);
            throw new ValidatorException(msg);
        }
    }

    /**
     * Validates expiry date for the credit card.
     *
     * @param context context
     * @param component ui_component
     * @param value value
     */
    public void validateExpiry(FacesContext context, UIComponent component, Object value) {
        boolean cc_badExpiry = true;

        String expiry = (String) value;
        try {
            //has a "/", length is 7, month is 12 or lower, is not a negative number
            if (expiry.contains("/") && expiry.length() == 7 && Integer.parseInt(expiry.substring(0, 2)) < 13 && !expiry.startsWith("-")) {
                try {
                    Date today = new java.util.Date();

                    Date expiryDate = new java.text.SimpleDateFormat("dd/MM/yyyy").parse("01/" + expiry);
                    //System.out.println(expiryDate.toString());
                    if (!expiryDate.before(today)) {
                        //not expired
                        cc_badExpiry = false;
                    } else {
                        //System.out.println("card expired:");
                    }
                } catch (ParseException pe) {
                    //System.out.println("Expiry comparison error: " + expiry);
                }
            }
        } catch (NumberFormatException nfe) {
            //System.out.println("MM is not a number");
        }

        if (cc_badExpiry) {
            //System.out.println("not valid expiry: " + expiry);
            String notValid = context.getApplication().evaluateExpressionGet(context, "Expiry date is not valid (MM/YYYY)", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, notValid, notValid);
            throw new ValidatorException(msg);
        }
    }

    /**
     * Validates CVC/CVV of the credit card.
     *
     * @param context context
     * @param component ui_component
     * @param value value
     */
    public void validateCVC(FacesContext context, UIComponent component, Object value) {
        boolean cc_badcvc = true;

        String cvc = (String) value;

        if (!cvc.startsWith("-") && cvc.length() == 3) {
            try {
                int cvcnum = Integer.parseInt(cvc);
                cc_badcvc = false;
            } catch (Exception e) {
                //System.out.println("CVC must be digits only.");
            }
        } else {
            //System.out.println("CVC invalid: " + cvc);
        }

        if (cc_badcvc) {
            //System.out.println("not valid cvc: " + cvc);
            String notValid = context.getApplication().evaluateExpressionGet(context, "CVC is not valid", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, notValid, notValid);
            throw new ValidatorException(msg);
        }
    }
}
