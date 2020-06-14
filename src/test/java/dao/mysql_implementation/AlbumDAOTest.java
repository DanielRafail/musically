package dao.mysql_implementation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.Album;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * The AlbumDAOTest class contains unit tests to ensure proper functionality of 
 * the Album DAO.
 * 
 * @author Mihail Cerba, P. Bellefleur
 */

public class AlbumDAOTest {
    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static AlbumDAOImpl albumDao = null;
    Album newAlbum1;
    Album newAlbum2;
    Album newAlbum3;
    
    public AlbumDAOTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        albumDao = new AlbumDAOImpl();
        albumDao.setEntityManager(entityManager);
    }
    
    @Before
    public void setUp() {
        //tear down any entries, if present, before building test data
        List<Album> allAlbums = albumDao.findAll();
        allAlbums.forEach((album) -> {
            albumDao.remove(album);
        });
        
        //objects filled with test data
        newAlbum1 = new Album();
        newAlbum1.setTitle("First test album");
        newAlbum1.setRecordingLabel("First test label");
        newAlbum1.setNumberOfTracks(3);
        newAlbum1.setCostPrice(BigDecimal.ONE);
        newAlbum1.setListPrice(BigDecimal.ONE);
        newAlbum1.setSalePrice(BigDecimal.ZERO);
        newAlbum1.setCoverFilepath("test");
        newAlbum1.setReleaseDate(new Date());
        newAlbum1.setAddedDate(new Date());
        
        newAlbum2 = new Album();
        newAlbum2.setTitle("Second test album");
        newAlbum2.setRecordingLabel("Second test label");
        newAlbum2.setNumberOfTracks(5);
        newAlbum2.setCostPrice(BigDecimal.ONE);
        newAlbum2.setListPrice(BigDecimal.ONE);
        newAlbum2.setSalePrice(BigDecimal.ZERO);
        newAlbum2.setCoverFilepath("test");
        newAlbum2.setReleaseDate(new Date());
        newAlbum2.setAddedDate(new Date());
        
        newAlbum3 = new Album();
        newAlbum3.setTitle("Third test album");
        newAlbum3.setRecordingLabel("Third test label");
        newAlbum3.setNumberOfTracks(8);
        newAlbum3.setCostPrice(BigDecimal.ONE);
        newAlbum3.setListPrice(BigDecimal.ONE);
        newAlbum3.setSalePrice(BigDecimal.ZERO);
        newAlbum3.setCoverFilepath("test");
        newAlbum3.setReleaseDate(new Date());
        newAlbum3.setAddedDate(new Date());
        
        //persist test data to db
        albumDao.persist(newAlbum1);
        albumDao.persist(newAlbum2);
        albumDao.persist(newAlbum3);
        
    }
    
    @AfterClass
    public static void tearDown() {
        List<Album> allAlbums = albumDao.findAll();
        allAlbums.forEach((album) -> {
            albumDao.remove(album);
        });
    }

    /**
     * Tests the DAO's findAll method to ensure it retrieves all data in the 
     * database.
     */
    @Test
    public void testFindAll() {
        List<Album> all = albumDao.findAll();
        assertEquals(3, all.size());
        assertEquals((long)newAlbum1.getId(), (long)all.get(0).getId());
        assertEquals((long)newAlbum2.getId(), (long)all.get(1).getId());
        assertEquals((long)newAlbum3.getId(), (long)all.get(2).getId());
    }
    
    /**
     * Tests the DAO's findByName method to ensure it can retrieve a single 
     * result from the database.
     */
    @Test
    public void testFindByNameSingleResult() {
        List<Album> results = albumDao.findByName("Second test album");
        //results list should have a size of 1
        assertEquals(1, results.size());
        //Second test album should have an id of 2; verify result has this
        assertEquals((long)newAlbum2.getId(), (long)results.get(0).getId());
    }
    
    /**
     * Tests the DAO's findByName method to ensure it can retrieve multiple 
     * results from the database. 
     */
    @Test
    public void testFindByNameMultipleResults() {
        String title = "Test Duplicate Names";
        //create album
        Album dupeAlbum1 = new Album();
        dupeAlbum1.setTitle(title);
        dupeAlbum1.setRecordingLabel("test");
        dupeAlbum1.setNumberOfTracks(3);
        dupeAlbum1.setCostPrice(BigDecimal.ONE);
        dupeAlbum1.setListPrice(BigDecimal.ONE);
        dupeAlbum1.setSalePrice(BigDecimal.ZERO);
        dupeAlbum1.setCoverFilepath("test");
        dupeAlbum1.setReleaseDate(new Date());
        dupeAlbum1.setAddedDate(new Date());
        //create album with identical name
        Album dupeAlbum2 = new Album();
        dupeAlbum2.setTitle(title);
        dupeAlbum2.setRecordingLabel("test");
        dupeAlbum2.setNumberOfTracks(4);
        dupeAlbum2.setCostPrice(BigDecimal.ONE);
        dupeAlbum2.setListPrice(BigDecimal.ONE);
        dupeAlbum2.setSalePrice(BigDecimal.ZERO);
        dupeAlbum2.setCoverFilepath("test");
        dupeAlbum2.setReleaseDate(new Date());
        dupeAlbum2.setAddedDate(new Date());
        //persist
        albumDao.persist(dupeAlbum1);
        albumDao.persist(dupeAlbum2);
        //get list from db 
        List<Album> results = albumDao.findByName(title);
        //results list should be 2
        assertTrue((results.size() == 2));
        assertEquals((long)dupeAlbum1.getId(), (long)results.get(0).getId());
        assertEquals((long)dupeAlbum2.getId(), (long)results.get(1).getId());
    }
    
    /**
     * Tests the DAO's findByName method to ensure it retrieves nothing if 
     * given a name that does not exist in the database.
     */
    @Test
    public void testFindByNameDoesNotExist() {
        List<Album> results = albumDao.findByName("This doesn't exist in the database.");
        //results list should be empty
        assertEquals(0, results.size());
    }
    
}
