/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The User class is a JPA class that represents the "Tracks" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 */
@Entity
@Table(name = "tracks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t")
    , @NamedQuery(name = "Track.findById", query = "SELECT t FROM Track t WHERE t.id = :id")
    , @NamedQuery(name = "Track.findByTitle", query = "SELECT t FROM Track t WHERE t.title = :title")
    , @NamedQuery(name = "Track.findBySongwriter", query = "SELECT t FROM Track t WHERE t.songwriter = :songwriter")
    , @NamedQuery(name = "Track.findByPlayLength", query = "SELECT t FROM Track t WHERE t.playLength = :playLength")
    , @NamedQuery(name = "Track.findByAlbumSelection", query = "SELECT t FROM Track t WHERE t.albumSelection = :albumSelection")
    , @NamedQuery(name = "Track.findByCostPrice", query = "SELECT t FROM Track t WHERE t.costPrice = :costPrice")
    , @NamedQuery(name = "Track.findByListPrice", query = "SELECT t FROM Track t WHERE t.listPrice = :listPrice")
    , @NamedQuery(name = "Track.findBySalePrice", query = "SELECT t FROM Track t WHERE t.salePrice = :salePrice")
    , @NamedQuery(name = "Track.findBySellableAsSingle", query = "SELECT t FROM Track t WHERE t.sellableAsSingle = :sellableAsSingle")
    , @NamedQuery(name = "Track.findByRemoved", query = "SELECT t FROM Track t WHERE t.removed = :removed")
    , @NamedQuery(name = "Track.findByRemoveDate", query = "SELECT t FROM Track t WHERE t.removeDate = :removeDate")})
public class Track implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "songwriter")
    private String songwriter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "play_length")
    private String playLength;
    @Basic(optional = false)
    @NotNull
    @Column(name = "album_selection")
    private int albumSelection;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "list_price")
    private BigDecimal listPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "sellable_as_single")
    private Boolean sellableAsSingle;
    @Column(name = "removed")
    private Boolean removed;
    @Column(name = "remove_date")
    @Temporal(TemporalType.DATE)
    private Date removeDate;
    @OneToMany(mappedBy = "trackId")
    private Collection<Review> reviewCollection;
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.PERSIST)
    private Album album;
    @OneToMany(mappedBy = "trackId")
    private Collection<OrderItem> orderItemCollection;

    @NotNull
    @Column(name = "download_count")
    private int downloadCount = 0;
    
    public Track() {
    }

    public Track(Long id) {
        this.id = id;
    }

    public Track(Long id, String title, String songwriter, String playLength, int albumSelection, BigDecimal costPrice, BigDecimal listPrice, BigDecimal salePrice) {
        this.id = id;
        this.title = title;
        this.songwriter = songwriter;
        this.playLength = playLength;
        this.albumSelection = albumSelection;
        this.costPrice = costPrice;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongwriter() {
        return songwriter;
    }

    public void setSongwriter(String songwriter) {
        this.songwriter = songwriter;
    }

    public String getPlayLength() {
        return playLength;
    }

    public void setPlayLength(String playLength) {
        this.playLength = playLength;
    }

    public int getAlbumSelection() {
        return albumSelection;
    }

    public void setAlbumSelection(int albumSelection) {
        this.albumSelection = albumSelection;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Boolean getSellableAsSingle() {
        return sellableAsSingle;
    }

    public void setSellableAsSingle(Boolean sellableAsSingle) {
        this.sellableAsSingle = sellableAsSingle;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album albumId) {
        this.album = albumId;
    }

    @XmlTransient
    public Collection<OrderItem> getOrderItemCollection() {
        return orderItemCollection;
    }

    public void setOrderItemCollection(Collection<OrderItem> orderItemCollection) {
        this.orderItemCollection = orderItemCollection;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
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
        if (!(object instanceof Track)) {
            return false;
        }
        Track other = (Track) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Track[ id=" + id + " ]";
    }
    
    public Boolean isOnSale(){
        return salePrice.compareTo(BigDecimal.ZERO) != 0;
    }
}
