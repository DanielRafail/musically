package managedBean;

import dao.sql_interface.AlbumDAO;
import dao.sql_interface.GenreDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import jpa.Album;
import jpa.Genre;
import jpa.User;

@Named
@SessionScoped
public class RecommendedBean implements Serializable {

    final int limit = 4;

    @Inject
    private GenreDAO genreDao;

    @Inject
    private AlbumDAO albumDAO;

    public List<Album> getAlbumsFromAGenre() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().getOrDefault("User", null);
        String genre = null;
        if (user != null) {
            genre = user.getLastSearchGenre().getGenre();
        }
        if (genre == null) {
            return getAlbumsRandomGenre();
        }
        List<Genre> genres = genreDao.searchAllMatchingExact(genre);
        if (genres.size() > 0) {
            List<Album> albums = new ArrayList(genres.get(0).getAlbumCollection());
            List<Album> returnArray = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                if (i >= albums.size()) {
                    return returnArray;
                }
                returnArray.add(albums.get(i));
            }
            return returnArray;
        } else {
            return getAlbumsRandomGenre();
        }
    }

    public List<Album> getAlbumsRandomGenre() {
        List<Genre> genres = genreDao.findAll();
        Random rand = new Random();
        List<Album> albums = new ArrayList(genres.get(rand.nextInt(genres.size())).getAlbumCollection());
        List<Album> returnArray = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i >= albums.size()) {
                return returnArray;
            }
            returnArray.add(albums.get(i));
        }
        return returnArray;
    }

    public List<Album> getSimilarAlbums(String keyword) {
        List<Album> currentAlbum = albumDAO.findByName(keyword);
        List<Genre> currentGenre = new ArrayList(currentAlbum.get(0).getGenreCollection());
        List<Album> albumsToShow = new ArrayList(currentGenre.get(0).getAlbumCollection());
        List<Album> returnArray = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            if (i >= albumsToShow.size()) {
                return returnArray;
            }
            if (albumsToShow.get(i) == currentAlbum.get(0) && albumsToShow.size() != 1) {
                continue;
            }
            returnArray.add(albumsToShow.get(i));
        }
        return returnArray;
    }

    public String goToAlbums(long id) {
        //FacesContext.getCurrentInstance().getExternalContext().redirect(location + ".xhtml");
        return "album?faces-redirect=true&albumID=" + Long.toString(id);
    }
}
