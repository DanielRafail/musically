package dao.mysql_implementation;

import dao.sql_interface.ReviewDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.Album;
import jpa.Review;
import jpa.Track;

/**
 * The ReviewDAOImpl class defines a data access object (DAO) that accesses the 
 * Reviews table in the Musically database.
 * 
 * @author Johnny Lin, P. Bellefleur
 */
@SessionScoped
public class ReviewDAOImpl extends JpaDaoImpl<Review> 
        implements ReviewDAO , Serializable  {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    /**
     * Finds all entries in the table, and stores them as Review objects in a 
     * List.
     * 
     * @return  a List of all Reviews in the database.
     */
    @Override
    public List<Review> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<Review>) q.getResultList();
    }
    
    /**
     * Given an album ID, retrieves all approved reviews for that album.
     * 
     * @param albumID   An album ID in the database.
     * @return  a List of all approved Reviews for that album.
     */
    public List<Review> findAllByAlbum(long albumID) {
        Query q = entityManager.createQuery("SELECT r FROM Review r WHERE r.albumId.id = :album AND r.approved = true");
        q.setParameter("album", albumID);
        List<Review> result = q.getResultList();
        return result;
    }
    
    /**
     * Given a track ID, retrieves all approved reviews for that track. 
     * 
     * @param trackID   A track ID in the database.
     * @return  a List of all approved Reviews for that track.
     */
    public List<Review> findAllByTrack(long trackID) {
        Query q = entityManager.createQuery("SELECT r FROM Review r WHERE r.trackId.id = :track AND r.approved = true");
        q.setParameter("track", trackID);
        List<Review> result = q.getResultList();
        return result;
    }
    
}
