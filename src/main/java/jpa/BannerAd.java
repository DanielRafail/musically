/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The User class is a JPA class that represents the "BannerAds" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 * @author Johnny Lin
 */
@Entity
@Table(name = "banner_ads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BannerAd.findAll", query = "SELECT b FROM BannerAd b")
    , @NamedQuery(name = "BannerAd.findById", query = "SELECT b FROM BannerAd b WHERE b.id = :id")
    , @NamedQuery(name = "BannerAd.findByName", query = "SELECT b FROM BannerAd b WHERE b.name = :name")
    , @NamedQuery(name = "BannerAd.findByImgPath", query = "SELECT b FROM BannerAd b WHERE b.imgPath = :imgPath")
    , @NamedQuery(name = "BannerAd.findByLink", query = "SELECT b FROM BannerAd b WHERE b.link = :link")})
public class BannerAd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "img_path")
    private String imgPath;
    @Size(max = 255)
    @Column(name = "link")
    private String link;

    public BannerAd() {
    }

    public BannerAd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        if (!(object instanceof BannerAd)) {
            return false;
        }
        BannerAd other = (BannerAd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.BannerAd[ id=" + id + " ]";
    }
    
}
