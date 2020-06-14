package dao.mysql_implementation;

import dao.sql_interface.Dao;
import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * This class represent common behavior of all DAO implementation classes
 *
 * @author Cerba Mihail
 */
public abstract class JpaDaoImpl<E> {

    protected Class<E> entityClass;

    @Resource
    protected UserTransaction userTransaction;
    protected EntityManager entityManager;

    public JpaDaoImpl() {
    }
    
    public JpaDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @PersistenceContext(unitName = "CSteam_d")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E persist(E entity) {

        executeInsideTransaction(em -> {
            em.persist(entity);
        });

        //logger.debug("persist new entity");
        return entity;
    }

    public void remove(E entity) {
        //this.update(entity);
        executeInsideTransaction((em) -> {
            em.remove(entity);
                });
    }

    public E update(E entity) {
        executeInsideTransaction(em -> em.merge(entity));
        return entity;
    }

    public E findById(long id) {
        return entityManager.find(entityClass, id);
    }
    
    
    

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        if (userTransaction == null) {
            EntityTransaction tx = this.entityManager.getTransaction();
            try {
                tx.begin();
                action.accept(entityManager);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        } else {
            try {
                userTransaction.begin();
                action.accept(entityManager);
                try {
                    userTransaction.commit();
                } catch (RollbackException ex) {
                    Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicMixedException ex) {
                    Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HeuristicRollbackException ex) {
                    Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NotSupportedException ex) {
                Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(JpaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void close() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }

        if (entityManager.isOpen()) {
            entityManager.close();
        }

    }

}
