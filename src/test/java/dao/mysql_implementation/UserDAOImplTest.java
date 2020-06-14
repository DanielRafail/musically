/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql_implementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.User;
import jpa.UserType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Cerba Mihail
 */
public class UserDAOImplTest {

    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static UserDAOImpl UserDAO = null;
    static UserTypeDAOImpl userTypeDAOImpl = null;

    public UserDAOImplTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        UserDAO = new UserDAOImpl();
        userTypeDAOImpl = new UserTypeDAOImpl();
        UserDAO.setEntityManager(entityManager);
        userTypeDAOImpl.setEntityManager(entityManager);
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        List<User> users = UserDAO.findAll();
        users.forEach((user) -> {
            UserDAO.remove(user);
        });
    }

    @After
    public void tearDown() {
        List<User> users = UserDAO.findAll();
        users.forEach((user) -> {
            UserDAO.remove(user);
        });
        List<UserType> usersTypes = userTypeDAOImpl.findAll();
        usersTypes.forEach((usersType) -> {
            userTypeDAOImpl.remove(usersType);
        });
        
    }

    /**
     * Test of createClient method, of class UserDAOImpl.
     */
    @Test
    public void testCreateClient0() {
        System.out.println("createClient");
        User user = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "String email", "String password");

        User result = UserDAO.createClient(user);
        User expResult = UserDAO.findUserById(result.getId());
        assertEquals(expResult, result);

    }

    /**
     * Test of createClient method, of class UserDAOImpl.
     */
    @Test
    public void testCreateClient1() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me@gmail.com", "String password");
        User user1 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "metoo@gmail.com", "String password");

        User result0 = UserDAO.createClient(user0);
        User result1 = UserDAO.createClient(user1);

        User expResult = UserDAO.findUserById(result1.getId());
        System.out.println(expResult);
        assertEquals(expResult, result1);
    }

    /**
     * Test of IsEmailExist method, of class UserDAOImpl.
     */
    @Test
    public void testIsEmailExist1() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");
        User user1 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "metoo2@gmail.com", "String password");

        User result0 = UserDAO.createClient(user0);
        User result1 = UserDAO.createClient(user1);
        System.out.println(result1.getEmail());
        boolean expectTrue = UserDAO.isEmailExists(result1.getEmail());

        assertTrue(expectTrue);
    }
    
    /**
     * Test of IsEmailExist method, of class UserDAOImpl.
     */
    @Test
    public void testIsEmailExist2() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");

        User result0 = UserDAO.createClient(user0);
        UserDAO.remove(result0);

        boolean expectFalse = UserDAO.isEmailExists(result0.getEmail());
        assertFalse(expectFalse);
    }
    

    /**
     * Test of CreateManager method, of class UserDAOImpl.
     */
    
    @Test
    public void testCreateManager() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");

        User user = UserDAO.createClient(user0);
        User client = UserDAO.createManager(user);

        assertEquals(client.getUserType().getType(),"manager");
    }
    
    /**
     * Test of CreateManager method, of class UserDAOImpl.
     */
    @Test
    public void testFindAll() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");
        User user1 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "metoo2@gmail.com", "String password");
        User user0FromDB = UserDAO.createClient(user0);
        User user1FromDB = UserDAO.createClient(user1);
        List<User> users = UserDAO.findAll();

        assertEquals(user0FromDB,users.get(0));
        assertEquals(user1FromDB,users.get(1));
        assertEquals(users.size(),2);
    }
    

    /**
     * Test of findUserById method, of class UserDAOImpl.
     */
    
    @Test
    public void testFindUserById() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");

        User user = UserDAO.createClient(user0);
        User userFromId =  UserDAO.findById(user.getId());

        assertEquals(userFromId,user);
    }
    
     /**
     * Test of findUserByEmail method, of class UserDAOImpl.
     */
    
    @Test
    public void testFindUserByEmail() {
        User user0 = new User("lastName", "firstName",
                "String address1", "String city",
                "String province", "String country",
                "String postalCode", "String homePhone",
                "me2@gmail.com", "String password");

        User user = UserDAO.createClient(user0);
        User userFromEmail =  UserDAO.findUserByEmail("me2@gmail.com");

        assertEquals(userFromEmail,user);
    }
}
