package dao.mysql_implementation;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.Track;

/**
 * This class executes managing operations over Track class in the DataBase
 * @author Cerba Mihail, Peter Bellefleur
 */
@SessionScoped
public class TrackDAOImpl extends JpaDaoImpl<Track> 
        implements dao.sql_interface.TrackDAO , Serializable {

    public TrackDAOImpl() {
        super(Track.class);
    }

        
    /**
     * This method retrieve all tracks from database 
     * @return List<Track> - all tracks entities from database
     */
    @Override
    public List<Track> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<Track>) q.getResultList();
    }
    
    /**
     * Given a Track ID, finds the total number of times it has been sold. Count 
     * is calculated based on the number of times the given track appears in 
     * the Order Items table. 
     * 
     * @param toSearch  The Track to search for.
     * @return  an int, representing the total number of times a track has been 
     *          sold.
     */
    @Override
    public int findPurchaseCount(int idToSearch) {
        Query q = entityManager.createQuery("SELECT t FROM Track t "
                + "INNER JOIN t.orderItemCollection oi "
                + "WHERE oi.trackId = ?1");
        q.setParameter(1, idToSearch);
        List<Track> results = q.getResultList();
        return results.size();
    }
    
    /**
     * Finds all Tracks in the database with a given name.
     * 
     * @param toSearch  a String, representing the Track name to search for.
     * @return  a List of Tracks with the given name. 
     */
    @Override
    public List<Track> findByName(String toSearch) {
        Query q = entityManager.createQuery("SELECT e FROM " 
                + entityClass.getName() 
                + " e WHERE e.title = ?1");
        q.setParameter(1, toSearch);
        return q.getResultList();
        
    }

    /**
     * Given a String, finds all Tracks whose titles are similar to the String.
     * 
     * @param keyword   The search criteria.
     * @return  a List of Tracks matching the search criteria.
     */
    @Override
    public List<Track> searchAllMatching(String keyword) {
        Query q = entityManager.createQuery("SELECT t FROM Track t "
                + "WHERE t.title like :keyword");
        q.setParameter("keyword", "%"+keyword+"%");
        List<Track> results = q.getResultList();
        return results;
    }
  
}
