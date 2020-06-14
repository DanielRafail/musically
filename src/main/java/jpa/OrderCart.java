package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The OrderCart class is a JPA class that represents the "orders" table from 
 the Musically database as a Java entity.
 * 
 * @author P. Bellefleur 
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderCart.findAll", query = "SELECT o FROM OrderCart o")
    , @NamedQuery(name = "OrderCart.findById", query = "SELECT o FROM OrderCart o WHERE o.id = :id")
    , @NamedQuery(name = "OrderCart.findBySaleDate", query = "SELECT o FROM OrderCart o WHERE o.saleDate = :saleDate")
    , @NamedQuery(name = "OrderCart.findBySubTotal", query = "SELECT o FROM OrderCart o WHERE o.subTotal = :subTotal")
    , @NamedQuery(name = "OrderCart.findByPst", query = "SELECT o FROM OrderCart o WHERE o.pst = :pst")
    , @NamedQuery(name = "OrderCart.findByGst", query = "SELECT o FROM OrderCart o WHERE o.gst = :gst")
    , @NamedQuery(name = "OrderCart.findByHst", query = "SELECT o FROM OrderCart o WHERE o.hst = :hst")
    , @NamedQuery(name = "OrderCart.findByGrossValue", query = "SELECT o FROM OrderCart o WHERE o.grossValue = :grossValue")
    , @NamedQuery(name = "OrderCart.findByIsFinalized", query = "SELECT o FROM OrderCart o WHERE o.isFinalized = :isFinalized")})
public class OrderCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_date")
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pst")
    private BigDecimal pst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gst")
    private BigDecimal gst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hst")
    private BigDecimal hst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gross_value")
    private BigDecimal grossValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_finalized")
    private boolean isFinalized;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderItem> orderItemCollection;

    public OrderCart() {
    }

    public OrderCart(Long id) {
        this.id = id;
    }

    public OrderCart(Long id, Date saleDate, BigDecimal subTotal, BigDecimal pst, BigDecimal gst, BigDecimal hst, BigDecimal grossValue, boolean isFinalized) {
        this.id = id;
        this.saleDate = saleDate;
        this.subTotal = subTotal;
        this.pst = pst;
        this.gst = gst;
        this.hst = hst;
        this.grossValue = grossValue;
        this.isFinalized = isFinalized;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getPst() {
        return pst;
    }

    public void setPst(BigDecimal pst) {
        this.pst = pst;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public BigDecimal getHst() {
        return hst;
    }

    public void setHst(BigDecimal hst) {
        this.hst = hst;
    }

    public BigDecimal getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(BigDecimal grossValue) {
        this.grossValue = grossValue;
    }

    public boolean getIsFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(boolean isFinalized) {
        this.isFinalized = isFinalized;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderCart)) {
            return false;
        }
        OrderCart other = (OrderCart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.OrderCart[ id=" + id + " ]";
    }
    
}
