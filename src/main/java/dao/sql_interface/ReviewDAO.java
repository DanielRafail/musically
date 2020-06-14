/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.Review;

/**
 *
 * @author Johnny Lin
 */
public interface ReviewDAO extends Dao<Review>{
    List<Review> findAll();
}
