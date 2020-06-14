/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import javax.ejb.Stateless;

/**
 * This interface mange general behavior of all DAO classes 
 * @author Cerba Mihail 
 * @param <E> generic  Entity 
 */
public interface Dao<E> {
      E persist(E entity);
      void remove(E entity);
      E update(E entity);
      E findById(long id);
}
