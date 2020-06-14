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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The UserType class is a JPA class that represents the "user types" table 
 * from the Musically database as a Java entity.
 * 
 * @author P. Bellefleur 
 * @author Cerba Mihail
 */
@Entity
@Table(name = "user_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
    , @NamedQuery(name = "UserType.findById", query = "SELECT u FROM UserType u WHERE u.id = :id")
    , @NamedQuery(name = "UserType.findByType", query = "SELECT u FROM UserType u WHERE u.type = :type")})
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_email", unique=true)
    private String userEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userType")
    private Collection<User> userCollection;

    public UserType() {
    }

    public UserType(Long id) {
        this.id = id;
    }

    public UserType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.UserType[ id=" + id + " ]";
    }
    
}
