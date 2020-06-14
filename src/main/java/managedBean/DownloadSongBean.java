package managedBean;

import dao.sql_interface.AlbumDAO;
import dao.sql_interface.TrackDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.OrderItem;
import jpa.Track;
import jpa.User;

/**
 * DownloadSongBean will be in charge of providing the user with the song they
 * downloaded.
 *
 * @author Johnny Lin
 */
@Named
@SessionScoped
public class DownloadSongBean implements Serializable {
    @Inject
    private AlbumDAO albumDao;
    
    @Inject
    private TrackDAO trackDao;
    
    public DownloadSongBean() {
    }

    /**
     * Downloads the appropriate track or album for the user.
     *
     * @param orderItem Order item
     */
    public void downloadItem(OrderItem orderItem) {
        /*
            1) check for logged in and if emails match
            2) get the song or album
            3) make it download for the user
         */
        if (orderItem == null) 
            return;
        User user = (User) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().getOrDefault("User", null);

        if (user == null) {
            return;
        }
        if (!user.getEmail().equals(orderItem.getOrderId().getUserId().getEmail())) {
            return;
        }

        if (orderItem.isAlbum()) {
            downloadAlbum(orderItem.getAlbumId());
        } else {
            downloadTrack(orderItem.getTrackId());
        }
    }

    /**
     * Starts the download for the album.
     * 
     * @param album album to download
     */
    private void downloadAlbum(Album album) {
        try {
            URL fileURL = this.getClass().getClassLoader().getResource("META-INF/Your downloaded album.zip");
            //File file = Paths.get("DownloadItems", "Your downloaded album.zip").toFile();
            File file = new File(fileURL.toURI());
            System.out.println(file.getAbsolutePath());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset();
            ec.setResponseContentType("application/zip");
            ec.setResponseContentLength((int) file.length());
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            try (OutputStream output = ec.getResponseOutputStream();
                    FileInputStream fis = new FileInputStream(file);) {
                byte[] fileData = new byte[(int) file.length()];
                fis.read(fileData);
                output.write(fileData);
            } catch (IOException ioe) {
                System.out.println("Download failed for album: " + album.getId());
            }

            fc.responseComplete();
            
            addDownloadCount(album);
        } catch (URISyntaxException urise) {
            System.out.println("Uri is wrong");
        }

    }

    /**
     * Starts the download for the track.
     * 
     * @param track track to download
     */
    private void downloadTrack(Track track) {
        try {
            URL fileURL = this.getClass().getClassLoader().getResource("META-INF/Your downloaded song.mp3");
            //File file = Paths.get("DownloadItems", "Your downloaded album.zip").toFile();
            File file = new File(fileURL.toURI());
            System.out.println(file.getAbsolutePath());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset();
            ec.setResponseContentType("audio/mpeg");
            ec.setResponseContentLength((int) file.length());
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            try (OutputStream output = ec.getResponseOutputStream();
                    FileInputStream fis = new FileInputStream(file);) {
                byte[] fileData = new byte[(int) file.length()];
                fis.read(fileData);
                output.write(fileData);
            } catch (IOException ioe) {
                System.out.println("Download failed for track: " + track.getId());
            }

            fc.responseComplete();
            
            addDownloadCount(track);
        } catch (URISyntaxException urise) {
            System.out.println("Uri is wrong");
        }
    }

    private void addDownloadCount(Track t) {
        t.setDownloadCount(t.getDownloadCount()+1);
        this.trackDao.update(t);
        //System.out.println("Download count for " + t + ": " + t.getDownloadCount());
    }
    private void addDownloadCount(Album a) {
        a.setDownloadCount(a.getDownloadCount()+1);
        this.albumDao.update(a);
        //System.out.println("Download count for " + a + ": " + a.getDownloadCount());
    }
}
