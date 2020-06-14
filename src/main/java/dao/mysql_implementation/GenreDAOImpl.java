package dao.mysql_implementation;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.Genre;

/**
 * This class executes managing operations over Genre class in the DataBase
 * @author Cerba Mihail
 */
@SessionScoped
public class GenreDAOImpl extends JpaDaoImpl<Genre> 
        implements dao.sql_interface.GenreDAO, Serializable {

    public GenreDAOImpl() {
        super(Genre.class);
    }

    /**
     * This method retrieve all genres from database 
     * @return List<Genre> - all genres entities from database
     */
    @Override
    public List<Genre> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<Genre>) q.getResultList();
    }
    
    /**
     * Given a String, retrieves all Genres whose genre name match the String.
     * 
     * @param toSearch  The genre name to search for.
     * @return  a List of Genres that match the search criteria.
     */
    public List<Genre> findByName(String toSearch) {
        Query q = entityManager.createQuery("SELECT g FROM Genre g "
                + "WHERE g.genre = ?1");
        q.setParameter(1, toSearch);
        List<Genre> results = q.getResultList();
        return results;
    }

    /**
     * Given a String, retrieves all Genres whose genre name is similar to the 
     * String.
     * 
     * @param keyword   The search criteria.
     * @return  a List of Genres that are similar to the search criteria.
     */
    @Override
    public List<Genre> searchAllMatching(String keyword) {
        Query q = entityManager.createQuery("SELECT genre FROM Genre genre "
                + "WHERE genre.genre like :keyword");
        q.setParameter("keyword", "%"+keyword+"%");
        List<Genre> results = q.getResultList();
        return results;
    }
    
    /**
     * Given a String, retrieves all Genres whose genre name is an exact match 
     * to the String.
     * 
     * @param keyword   The genre name to search for. 
     * @return  a List of Genres that match the search criteria.
     */
    @Override
    public List<Genre> searchAllMatchingExact(String keyword) {
        Query q = entityManager.createQuery("SELECT genre FROM Genre genre "
                + "WHERE genre.genre = :keyword");
        q.setParameter("keyword", keyword);
        List<Genre> results = q.getResultList();
        return results;
    }

    
}
