/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql_implementation;

import static dao.mysql_implementation.UserTypeDAOTest.userTypeDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.Artist;
import jpa.UserType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * The ArtistDAOTest class contains unit tests to ensure proper functionality of 
 * the Artist DAO.
 * @author Cerb Mihail 
 */
public class ArtistDAOTest {
   
    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static ArtistDAOImpl ArtistDAOImpl = null;

    public ArtistDAOTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        ArtistDAOImpl = new ArtistDAOImpl();
        ArtistDAOImpl.setEntityManager(entityManager);
    }


    @Before
    public void setUp() {
        List<Artist> artists = ArtistDAOImpl.findAll();
        artists.forEach((usersType) -> {
            ArtistDAOImpl.remove(usersType);
        });
    }

    @After
    public void tearDown() {
        List<Artist> artists = ArtistDAOImpl.findAll();
        artists.forEach((usersType) -> {
            ArtistDAOImpl.remove(usersType);
        });
        
    }
    /**
     * Test of persist method, of class ArtistDAOImpl.
     */
    @Test
    public void testPersist() {
        Artist artist = new Artist();
        artist.setArtistName("Test");

       artist = ArtistDAOImpl.persist(artist);
       Artist testArtist = ArtistDAOImpl.findById(artist.getId());
       assertEquals(artist, testArtist);
    }
   
    /**
     * Test of persist findByID, of class ArtistDAOImpl.
     */
    @Test
    public void findByID() {
        Artist artist = new Artist();
        artist.setArtistName("Test1");

        artist = ArtistDAOImpl.persist(artist);
        
        Artist customerFromDB = ArtistDAOImpl.findById(artist.getId());
        assertEquals (customerFromDB, artist);
    }
    
    @Test
    public void findAll() {
        Artist artist1 = new Artist();
        artist1.setArtistName("Test1");
        artist1 = ArtistDAOImpl.persist(artist1);
        
        Artist artist2 = new Artist();
        artist2.setArtistName("Test2");
        artist2 = ArtistDAOImpl.persist(artist2);
        
        List<Artist> all = ArtistDAOImpl.findAll();
        assertEquals(all.size(), 2);
    }
    
    @Test
    public void searchAllMatchingTest1() {
        Artist artist1 = new Artist();
        artist1.setArtistName("Above & Beyond");
        artist1 = ArtistDAOImpl.persist(artist1);
        
        List<Artist> all = ArtistDAOImpl.searchAllMatching("Above");
        assertEquals(all.get(0).getArtistName(),"Above & Beyond");
    }
    
    @Test
    public void searchAllMatchingTest2() {
        Artist artist1 = new Artist();
        artist1.setArtistName("Above & Beyond");
        artist1 = ArtistDAOImpl.persist(artist1);
        
        List<Artist> all = ArtistDAOImpl.searchAllMatching("NOT_Above");
        assertEquals(all.size(),0);
    }
}
