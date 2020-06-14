package managedBean;

import dao.sql_interface.AlbumDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.Genre;

/**
 * This class manage genres page
 *
 * @author Cerba Mihail
 */
@Named
@SessionScoped
public class LatestBean implements Serializable {

    @Inject
    AlbumDAO albumDAO;

    public List<Album> getLatestAlbum() {
        List<Album> albums = albumDAO.searchByNewest();
        return albums;
    }
}
