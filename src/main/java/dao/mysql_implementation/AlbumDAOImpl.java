package dao.mysql_implementation;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityTransaction;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import jpa.Album;

/**
 * This class execute mange operations over Album class in the DataBase
 *
 * @author Cerba Mihail, Peter Bellefleur
 */
@SessionScoped

public class AlbumDAOImpl extends JpaDaoImpl<Album>
        implements dao.sql_interface.AlbumDAO, Serializable {

    public AlbumDAOImpl() {
        super(Album.class);
    }

    /**
     * This method retrieve all Albums from database
     *
     * @return List<Album> - all Albums entities from database
     */
    @Override
    public List<Album> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<Album>) q.getResultList();
    }

    /**
     * Given an Album, finds the total number of times it has been sold. Count
     * is calculated based on the number of times the given album appears in the
     * Order Items table.
     *
     * @param toSearch The Album to search for.
     * @return an int, representing the total number of times an album has been
     * sold.
     */
    @Override
    public int findPurchaseCount(Album toSearch) {
        Query q = entityManager.createQuery("SELECT a FROM Album a "
                + "INNER JOIN a.orderItemCollection oi "
                + "WHERE oi.albumId = ?1");
        q.setParameter(1, toSearch.getId());
        List<Album> results = q.getResultList();
        return results.size();
    }

    /**
     * Given a String, finds all Albums whose title match that String.
     * 
     * @param toSearch  The title to search for.
     * @return  a List of Albums whose titles match the search criteria.
     */
    @Override
    public List<Album> findByName(String toSearch) {
        Query q = entityManager.createQuery("SELECT a FROM Album a "
                + "WHERE a.title = ?1");
        q.setParameter(1, toSearch);
        List<Album> results = q.getResultList();
        return results;
    }

    /**
     * Given a String, finds all Albums whose titles are similar to that String.
     * 
     * @param keyword   The keyword to search for.
     * @return  a List of Albums whose titles are similar to the search criteria.
     */
    @Override
    public List<Album> searchAllMatching(String keyword) {
        Query q = entityManager.createQuery("SELECT a FROM Album a "
                + "WHERE a.title like :keyword");
        q.setParameter("keyword", "%" + keyword + "%");
        List<Album> results = q.getResultList();
        return results;
    }
    
    @Override
    public void deleteAll() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        EntityTransaction tx = null; 
        if (this.userTransaction != null){
            
            this.userTransaction.begin();
        }else{
        tx = this.entityManager.getTransaction();
        tx.begin();
        }
        
        Query delete = entityManager.createQuery("DELETE FROM Album a");
        delete.executeUpdate();
        if (this.userTransaction != null){
            this.userTransaction.commit();
        }else{
        tx.commit();
        }
    }

    /**
     * Retrieves a list of the three Albums most recently added to the database.
     * 
     * @return  a List containing the 3 latest Albums. 
     */
    @Override
    public List<Album> searchByNewest() {
        Query q = entityManager.createQuery("SELECT a FROM Album a "
                + "ORDER BY a.releaseDate ASC");
        q.setMaxResults(3);
        List<Album> results = q.getResultList();
        return results;
    }
}
