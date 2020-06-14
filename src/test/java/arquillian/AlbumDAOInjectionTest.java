/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquillian;

import dao.mysql_implementation.AlbumDAOImpl;
import dao.mysql_implementation.UserDAOImpl;
import managedBean.*;
import static org.junit.Assert.*;
import dao.sql_interface.AlbumDAO;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import jpa.Album;
import jpa.User;
import jpa.UserType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * This class tests all Injection from the musically project
 * @author Cerba Mihail
 */
@RunWith(Arquillian.class)
public class AlbumDAOInjectionTest {

    @Before
    public void setUp() {
        try {
            albumDao.deleteAll();
        } catch (Exception ex) {
            Logger.getLogger(AlbumDAOInjectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @After
    public void tearDown() {
        try {
            albumDao.deleteAll();
        } catch (Exception ex) {
            Logger.getLogger(AlbumDAOInjectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
    @Deployment
    public static WebArchive createDeployment() {
        System.out.println("creating jar");
        
        final File[] dependencies = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.assertj:assertj-core")
                .withoutTransitivity()
                .asFile();
        
        final WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
        
                .addPackage(AlbumBean.class.getPackage())
                .addPackage(Album.class.getPackage())
                .addPackage(AlbumDAO.class.getPackage())
                .addPackage(AlbumDAOImpl.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "src/main/resources/META-INF/beans.xml")
                .addAsWebInfResource(
                        new File("src/main/setup/glassfish-resources.xml"),
                        "glassfish-resources.xml")
                .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),
                        "META-INF/persistence.xml")
                .addAsLibraries(dependencies);
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

   
   @Inject
   private AlbumDAOImpl albumDao;
    
    @org.junit.Test
    public void test2() {
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
        
        //save to DB
        newAlbum1 = albumDao.persist(newAlbum1);
        
        //
        Album albumFromDB = albumDao.findById(newAlbum1.getId());
        assertEquals(albumFromDB, newAlbum1);
        
    }
}
