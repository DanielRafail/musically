package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.Track;

/**
 * This interface executes managing operations over Track class in the DataBase
 * @author Cerba Mihail, Peter Bellefleur
 */
public interface TrackDAO extends Dao<Track> {
    List<Track> findAll();
    int findPurchaseCount(int idToSearch);
    List<Track> findByName(String toSearch);
    List<Track> searchAllMatching(String keyword);
    
}
