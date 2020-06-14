package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * The User class is a JPA class that represents the "albums" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 */
@Entity
@Table(name = "albums")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
    , @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.id = :id")
    , @NamedQuery(name = "Album.findByTitle", query = "SELECT a FROM Album a WHERE a.title = :title")
    , @NamedQuery(name = "Album.findByReleaseDate", query = "SELECT a FROM Album a WHERE a.releaseDate = :releaseDate")
    , @NamedQuery(name = "Album.findByRecordingLabel", query = "SELECT a FROM Album a WHERE a.recordingLabel = :recordingLabel")
    , @NamedQuery(name = "Album.findByNumberOfTracks", query = "SELECT a FROM Album a WHERE a.numberOfTracks = :numberOfTracks")
    , @NamedQuery(name = "Album.findByAddedDate", query = "SELECT a FROM Album a WHERE a.addedDate = :addedDate")
    , @NamedQuery(name = "Album.findByCostPrice", query = "SELECT a FROM Album a WHERE a.costPrice = :costPrice")
    , @NamedQuery(name = "Album.findByListPrice", query = "SELECT a FROM Album a WHERE a.listPrice = :listPrice")
    , @NamedQuery(name = "Album.findBySalePrice", query = "SELECT a FROM Album a WHERE a.salePrice = :salePrice")
    , @NamedQuery(name = "Album.findByRemoved", query = "SELECT a FROM Album a WHERE a.removed = :removed")
    , @NamedQuery(name = "Album.findByRemoveDate", query = "SELECT a FROM Album a WHERE a.removeDate = :removeDate")
    , @NamedQuery(name = "Album.findByCoverFilepath", query = "SELECT a FROM Album a WHERE a.coverFilepath = :coverFilepath")})
public class Album implements Serializable {

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
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "recording_label")
    private String recordingLabel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_tracks")
    private int numberOfTracks;
    @Basic(optional = false)
    @NotNull
    @Column(name = "added_date")
    @Temporal(TemporalType.DATE)
    private Date addedDate;
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
    @Column(name = "removed")
    private Boolean removed;
    @Column(name = "remove_date")
    @Temporal(TemporalType.DATE)
    private Date removeDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cover_filepath")
    private String coverFilepath;
    @ManyToMany(mappedBy = "albumCollection")
    private Collection<Artist> artistCollection;
    @JoinTable(name = "albums_genre", joinColumns = {
        @JoinColumn(name = "album_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "genre_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Genre> genreCollection;
    @OneToMany(mappedBy = "albumId")
    private Collection<Review> reviewCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private Collection<Track> trackCollection;
    @OneToMany(mappedBy = "albumId")
    private Collection<OrderItem> orderItemCollection;

    @NotNull
    @Column(name = "download_count")
    private int downloadCount = 0;
    
    public Album() {
    }

    public Album(Long id) {
        this.id = id;
    }

    public Album(Long id, String title, Date releaseDate, String recordingLabel, int numberOfTracks, Date addedDate, BigDecimal costPrice, BigDecimal listPrice, BigDecimal salePrice, String coverFilepath) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.recordingLabel = recordingLabel;
        this.numberOfTracks = numberOfTracks;
        this.addedDate = addedDate;
        this.costPrice = costPrice;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.coverFilepath = coverFilepath;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordingLabel() {
        return recordingLabel;
    }

    public void setRecordingLabel(String recordingLabel) {
        this.recordingLabel = recordingLabel;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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

    public String getCoverFilepath() {
        return coverFilepath;
    }

    public void setCoverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
    }

    @XmlTransient
    public Collection<Artist> getArtistCollection() {
        return artistCollection;
    }

    public void setArtistCollection(Collection<Artist> artistCollection) {
        this.artistCollection = artistCollection;
    }

    @XmlTransient
    public Collection<Genre> getGenreCollection() {
        return genreCollection;
    }

    public void setGenreCollection(Collection<Genre> genreCollection) {
        this.genreCollection = genreCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public List<Track> getTrackCollection() {
        Comparator<Track> comparator = new Comparator<Track>() {
        @Override
        public int compare(Track left, Track right) {
            return left.getAlbumSelection() - right.getAlbumSelection(); 
            }  
        };
        Collections.sort((List<Track>)trackCollection, comparator);
        return (List<Track>)trackCollection;
    }

    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public Boolean isOnSale(){
        return salePrice.compareTo(BigDecimal.ZERO) != 0;
    }
    
    /**
     * This method creates coma separated Artist name list 
     * @return String  - all coma separated Artist name list 
     * @author Cerba Mihail
     */
    public String getArtistCollectionCSV() {
        String artistCollectionCSV = "";
        for(Artist artist : this.artistCollection ){
            artistCollectionCSV += artist.getArtistName() + ",";
        }
        if (artistCollectionCSV.length() > 0) {
        artistCollectionCSV = artistCollectionCSV.substring(0,artistCollectionCSV.length()-1);
        }
        return artistCollectionCSV;
    }
    
    @Override
    public String toString() {
        return "jpa.Album[ id=" + id + " ]";
    }
    
}
