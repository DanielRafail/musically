package dao.mysql_implementation;

import dao.sql_interface.UserTypeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.UserType;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The UserTypeDAOTest class contains methods used to test the functionality of 
 * the UserTypeDAO class.
 * 
 * @author P. Bellefleur , Cerba Mihail
 */
public class UserTypeDAOTest {
   

    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static UserTypeDAOImpl userTypeDAOImpl = null;

    public UserTypeDAOTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        userTypeDAOImpl = new UserTypeDAOImpl();
        userTypeDAOImpl.setEntityManager(entityManager);
    }


    @Before
    public void setUp() {
        List<UserType> usersTypes = userTypeDAOImpl.findAll();
        usersTypes.forEach((usersType) -> {
            userTypeDAOImpl.remove(usersType);
        });
    }

    @After
    public void tearDown() {
        List<UserType> usersTypes = userTypeDAOImpl.findAll();
        usersTypes.forEach((usersType) -> {
            userTypeDAOImpl.remove(usersType);
        });
        
    }
    
    
    @Test
    public void findByID() {
        UserType customer1 = new UserType();
        customer1.setType("customer");
        customer1.setUserEmail("customer");
        customer1 = userTypeDAOImpl.persist(customer1);
        
        UserType customerFromDB = userTypeDAOImpl.findById(customer1.getId());
        assertEquals (customerFromDB, customer1);
        
    }
    
    @Test
    public void findAll() {
        UserType customer1 = new UserType();
        customer1.setType("customer");
        customer1.setUserEmail("customer");
        customer1 = userTypeDAOImpl.persist(customer1);
        
        UserType customer2 = new UserType();
        customer2.setType("customer2");
        customer2.setUserEmail("customer2");
        customer2 = userTypeDAOImpl.persist(customer2);
        userTypeDAOImpl.persist(customer1);
        userTypeDAOImpl.persist(customer2);

        List<UserType> all = userTypeDAOImpl.findAll();
        assertEquals(all.size(), 2);
    }
    
}
