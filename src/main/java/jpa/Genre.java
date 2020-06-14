/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The User class is a JPA class that represents the "Genres" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 */
@Entity
@Table(name = "genres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g")
    , @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id")
    , @NamedQuery(name = "Genre.findByGenre", query = "SELECT g FROM Genre g WHERE g.genre = :genre")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cover_filepath")
    private String coverFilepath;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "genre")
    private String genre;
    @ManyToMany(mappedBy = "genreCollection")
    private Collection<Album> albumCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lastSearchGenre")
    private Collection<User> userCollection;

    public Genre() {
    }

    public Genre(Long id) {
        this.id = id;
    }

    public Genre(Long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoverFilepath() {
        return coverFilepath;
    }

    public void setCoverFilepath(String coverFilepath) {
        this.coverFilepath = coverFilepath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlTransient
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Genre[ id=" + id + " ]";
    }
    
}
