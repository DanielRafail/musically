/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql_implementation;

import static dao.mysql_implementation.GenreDAOTest.GenreDAOImpl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.Album;
import jpa.Genre;
import jpa.Track;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

/**
 * The TrackDAOTest class contains unit tests to ensure proper functionality of
 * the Track DAO.
 *
 * @author Cerba Mihail
 */
public class TrackDAOTest {

    EntityManager entityManager = null;
    EntityManagerFactory entityManagerFactory = null;
    static TrackDAOImpl TrackDAOImpl = null;

    public TrackDAOTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("musicallyTest");
        entityManager = entityManagerFactory.createEntityManager();
        TrackDAOImpl = new TrackDAOImpl();
        TrackDAOImpl.setEntityManager(entityManager);
    }

    @Before
    public void setUp() {
        List<Track> tracks = TrackDAOImpl.findAll();
        tracks.forEach((track) -> {
            TrackDAOImpl.remove(track);
        });
    }

    @After
    public void tearDown() {
        List<Track> tracks = TrackDAOImpl.findAll();
        tracks.forEach((track) -> {
            TrackDAOImpl.remove(track);
        });

    }

    /**
     * Test of findAll method, of class TrackDAOImpl.
     */
    @Test
    public void testFindAll() {

        Track track = this.createMockTrack();

        track = TrackDAOImpl.persist(track);
        assertEquals(track, TrackDAOImpl.findAll().get(0));

    }

    /**
     * Test of persist findByID, of class TrackDAOImpl.
     */
    @Test
    public void findByID() {

        Track track = this.createMockTrack();

        track = TrackDAOImpl.persist(track);

        Track trackFromDB = TrackDAOImpl.findById(track.getId());
        assertEquals(trackFromDB, track);
    }

    //findPurchaseCount
    /**
     * Test of persist findByNameTest, of class TrackDAOImpl.
     */
    @Test
    public void findByNameTest() {
        Track track = this.createMockTrack();
        track.setTitle("Best");
        track = TrackDAOImpl.persist(track);

        List<Track> tracksFromDB = TrackDAOImpl.findByName("Best");
        assertEquals(tracksFromDB.get(0), track);
    }

    /**
     * Test of persist searchAllMatchingTest, of class TrackDAOImpl.
     */
    @Test
    public void searchAllMatchingTest1() {
        Track track = this.createMockTrack();
        track.setTitle("Best Track");
        track = TrackDAOImpl.persist(track);

        List<Track> tracksFromDB = TrackDAOImpl.searchAllMatching("Best");
        assertEquals(tracksFromDB.get(0), track);
    }

    /**
     * Test of persist searchAllMatchingTest, of class TrackDAOImpl.
     */
    @Test
    public void searchAllMatchingTest2() {
        Track track = this.createMockTrack();
        track.setTitle("Best Track");
        track = TrackDAOImpl.persist(track);

        List<Track> tracksFromDB = TrackDAOImpl.searchAllMatching("Not_Best");
        assertEquals(tracksFromDB.size(), 0);
    }

    private Album createMockAlbum() {
        //objects filled with test data
        Album newAlbum1 = new Album();
        newAlbum1.setTitle("First test album");
        newAlbum1.setRecordingLabel("First test label");
        newAlbum1.setNumberOfTracks(3);
        newAlbum1.setCostPrice(BigDecimal.ONE);
        newAlbum1.setListPrice(BigDecimal.ONE);
        newAlbum1.setSalePrice(BigDecimal.ZERO);
        newAlbum1.setCoverFilepath("test");
        newAlbum1.setReleaseDate(new Date());
        newAlbum1.setAddedDate(new Date());
        return newAlbum1;
    }

    private Track createMockTrack() {
        //objects filled with test data
        Track track = new Track();
        track.setTitle("First test album");
        track.setTitle("Title");
        track.setCostPrice(BigDecimal.ONE);
        track.setListPrice(BigDecimal.ONE);
        track.setSalePrice(BigDecimal.ZERO);
        track.setAlbum(this.createMockAlbum());
        track.setPlayLength("3:45");
        track.setRemoveDate(new Date());
        track.setRemoved(false);
        track.setSongwriter("Test Rock");

        return track;
    }
}
