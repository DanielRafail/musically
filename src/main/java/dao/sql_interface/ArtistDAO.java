/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.Artist;

/**
 * This interface executes managing operations over Artist class in the DataBase
 * @author Cerba Mihail 
 */
public interface ArtistDAO extends Dao<Artist> {
    List<Artist> findAll();
    List<Artist> searchAllMatching(String keyword);
    
}
