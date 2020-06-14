/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.Genre;

/**
 * This interface executes managing operations over Genre class in the DataBase
 * @author Cerba Mihail 
 */
public interface GenreDAO extends Dao<Genre> {
    List<Genre> findAll();
    List<Genre> searchAllMatching(String keyword);
    List<Genre> searchAllMatchingExact(String keyword);
}
