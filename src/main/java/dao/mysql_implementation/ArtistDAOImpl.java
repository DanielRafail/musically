package dao.mysql_implementation;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.Artist;

/**
 * This class executes managing operations over Artist class in the DataBase
 * @author Cerba Mihail 
 */
@SessionScoped
public class ArtistDAOImpl extends JpaDaoImpl<Artist> 
        implements dao.sql_interface.ArtistDAO, Serializable{

    public ArtistDAOImpl() {
        super(Artist.class);
    }

    
    /**
     * This method retrieve all artist from database 
     * @return List<Artist> - all artists entities from database
     */
    @Override
    public List<Artist> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<Artist>) q.getResultList();
    }

    /**
     * Given a String, retrieves a list of all Artists whose names are similar 
     * to that String.
     * 
     * @param keyword   The String to search for.
     * @return  a List of Artists whose names match the search criteria.
     */
    @Override
    public List<Artist> searchAllMatching(String keyword) {
        Query q = entityManager.createQuery("SELECT ar FROM Artist ar "
                + "WHERE ar.artistName like :keyword");
        q.setParameter("keyword", "%"+keyword+"%");
        List<Artist> results = q.getResultList();
        return results;
    }

    
}
