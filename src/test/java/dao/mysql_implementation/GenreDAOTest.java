/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql_implementation;

import static dao.mysql_implementation.ArtistDAOTest.ArtistDAOImpl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.Artist;
import jpa.Genre;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * The GenreDAOTest class contains unit tests to ensure proper functionality of
 * the Genre DAO.
 *
 * @author Cerba Mihail
 */
public class GenreDAOTest {

    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static GenreDAOImpl GenreDAOImpl = null;

    public GenreDAOTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        GenreDAOImpl = new GenreDAOImpl();
        GenreDAOImpl.setEntityManager(entityManager);
    }

    @Before
    public void setUp() {
        List<Genre> genres = GenreDAOImpl.findAll();
        genres.forEach((genre) -> {
            GenreDAOImpl.remove(genre);
        });
    }

    @After
    public void tearDown() {
        List<Genre> genres = GenreDAOImpl.findAll();
        genres.forEach((genre) -> {
            GenreDAOImpl.remove(genre);
        });

    }

    /**
     * Test of findAll method, of class GenreDAOImpl.
     */
    @Test
    public void testFindAll() {

        Genre genre = new Genre();
        genre.setGenre("Test Rock");
        genre.setCoverFilepath("Filepath");
        genre = GenreDAOImpl.persist(genre);
        assertEquals(genre, GenreDAOImpl.findById(genre.getId()));

    }

    /**
     * Test of persist findByID, of class GenreDAOImpl.
     */
    @Test
    public void findByID() {
        Genre genre = new Genre();
        genre.setGenre("Test Rock");
        genre.setCoverFilepath("Filepath");
        genre = GenreDAOImpl.persist(genre);

        Genre genreFromDB = GenreDAOImpl.findById(genre.getId());
        assertEquals(genreFromDB, genre);
    }

    @Test
    public void searchAllMatchingTest1() {
        Genre genre = new Genre();
        genre.setGenre("Test Rock");
        genre.setCoverFilepath("Filepath");
        genre = GenreDAOImpl.persist(genre);

        List<Genre> all = GenreDAOImpl.searchAllMatching("Rock");
        assertEquals(all.get(0).getGenre(), "Test Rock");
    }

    @Test
    public void searchAllMatchingTest2() {
        Genre genre = new Genre();
        genre.setGenre("Test Rock");
        genre.setCoverFilepath("Filepath");
        genre = GenreDAOImpl.persist(genre);

        List<Genre> all = GenreDAOImpl.searchAllMatching("Not_Rock");
        assertEquals(all.size(), 0);
    }

}
