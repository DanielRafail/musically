package dao.mysql_implementation;

import dao.sql_interface.BannerAdDAO;
import java.io.Serializable;
import jpa.BannerAd;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;

/**
 * The BannerAdDAOImpl class defines a Data Access Object (DAO) that accesses 
 * the Banner Ads table in the Musically database.
 * 
 * @author Johnny Lin
 */
@SessionScoped
public class BannerAdDAOImpl extends JpaDaoImpl<BannerAd> 
        implements BannerAdDAO, Serializable {

    public BannerAdDAOImpl() {
        super(BannerAd.class);
    }
    
    /**
     * Finds all entries in the table, and stores them in a List of BannerAd 
     * objects.
     * 
     * @return  a List, containing every BannerAd in the database.
     */
    @Override
    public List<BannerAd> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<BannerAd>) q.getResultList();
    }
}
