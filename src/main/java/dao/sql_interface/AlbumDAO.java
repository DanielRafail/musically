/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import jpa.Album;

/**
 *
 * @author Cerba Mihail 
 */
public interface AlbumDAO extends Dao<Album> {
    List<Album> findAll();
    int findPurchaseCount(Album toSearch);
    List<Album> findByName(String toSearch);
    List<Album> searchAllMatching(String keyword);
    void deleteAll() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException;
    List<Album> searchByNewest();
}
