package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The OrderItem class is a JPA class that represents the "order_items" table
 * from the Musically database as a Java entity.
 *
 * @author P. Bellefleur
 */
@Entity
@Table(name = "order_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o")
    , @NamedQuery(name = "OrderItem.findById", query = "SELECT o FROM OrderItem o WHERE o.id = :id")
    , @NamedQuery(name = "OrderItem.findByPrice", query = "SELECT o FROM OrderItem o WHERE o.price = :price")})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @JoinColumn(name = "album_id", referencedColumnName = "id")

    @ManyToOne
    private Album albumId;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderCart orderId;
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    @ManyToOne
    private Track trackId;
    
    @Column(name = "disabled")
    private boolean disabled;

    public OrderItem() {
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    public OrderItem(Long id) {
        this.id = id;
    }

    public OrderItem(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Album getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Album albumId) {
        this.albumId = albumId;
    }

    public OrderCart getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderCart orderId) {
        this.orderId = orderId;
    }

    public Track getTrackId() {
        return trackId;
    }

    public void setTrackId(Track trackId) {
        this.trackId = trackId;
    }

    public boolean isAlbum() {
        if (this.albumId != null) {
            return true;
        } else {
            return false;
        }
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
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    private String getArtistNames() {
        String artists = "";
        if (this.isAlbum()) {
            for (Artist artist : this.getAlbumId().getArtistCollection()) {
                artists += artist.getArtistName() + ", ";
            }
        } else {
            for (Artist artist : this.getTrackId().getAlbum().getArtistCollection()) {
                artists += artist.getArtistName() + ", ";
            }
        }
        artists = artists.substring(0, artists.length() - 2);

        return artists;
    }

    /**
     * Gets a String representation of the current item in the form of:
     * (Album/Track) artists - title
     * 
     * @return order item description
     */
    @Override
    public String toString() {
        return getArtistNames() + " - " + 
                (this.isAlbum() ? this.getAlbumId().getTitle() : this.getTrackId().getTitle()) 
                + (this.isDisabled() ? " disabled (Will not count for reports)" : "");
        
    }

    /**
     * Gets the object of the order item.
     * @return Object
     */
    public Object get() {
        if (this.isAlbum()) {
            return albumId;
        } else {
            return trackId;
        }
    }
    
    public String getType() {
        if (this.isAlbum()) {
            return "Album";
        } else {
            return "Track";
        }
    }
}
