package managedBean;

import dao.mysql_implementation.GenreDAOImpl;
import dao.mysql_implementation.UserDAOImpl;
import dao.sql_interface.AlbumDAO;
import dao.sql_interface.GenreDAO;
import dao.sql_interface.UserDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.Genre;
import jpa.Track;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import jpa.User;

/**
 * This class manage album page
 *
 * @author Cerba Mihail, P. Bellefleur
 */
@Named
@SessionScoped
public class AlbumBean implements Serializable {

    @Inject
    AlbumDAO albumDAO;

    @Inject
    GenreDAOImpl genreDAO;

    @Inject
    UserDAO userDao;

    private Album albumToDisplay;
    private Album album = new Album();
    private String genreName;

    /**
     * This method get all albums from DataBase or all albums from with specific
     * genre if it specified
     *
     * @return List<Album> List with Albums from Database
     */
    public List<Album> getAlbums() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String genre = params.get("genre");
        List<Album> albumsForDisplay = new ArrayList<>();
        List<Album> allAlbums = albumDAO.findAll();
        for (Album album : allAlbums) {
            Collection<Genre> genreCollection = album.getGenreCollection();
            for (Genre genreOfAlbum : genreCollection) {
                if (genreOfAlbum.getGenre() == null ? genre == null : genreOfAlbum.getGenre().equals(genre)) {
                    albumsForDisplay.add(album);
                    System.out.println(album);
                }
            }
        }
        return albumsForDisplay;
    }

    /**
     * Prepares an album's data to be displayed by fetching it from the database 
     * and saving it in a list.
     */
    public void setUpAlbum() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String albumID = params.get("albumID");
        this.albumToDisplay = albumDAO.findById(Long.parseLong(albumID));
        setLastSearchedGenre();
    }

    /**
     * Gets the AlbumToDisplay value. 
     * 
     * @return  an Album to be displayed 
     */
    public Album getAlbumToDisplay() {
        return albumToDisplay;
    }

    /**
     * Sets the AlbumToDisplay value.
     * 
     * @param albumToDisplay    an Album to display.
     */
    public void setAlbumToDisplay(Album albumToDisplay) {
        this.albumToDisplay = albumToDisplay;
    }

    /**
     * Gets the Album value. 
     * 
     * @return  the Album.
     */
    public Album getAlbum() {
        return album;
    }

    /**
     * Gets the Album's genre name. 
     * 
     * @return  the Album's genre name.
     */
    public String getGenreName() {
        return this.genreName;
    }

    /**
     * Sets the Album's genre name.
     * 
     * @param genreName the album's genre name.
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * Accepts a user's input, converts it into an Album object, and persists 
     * the object in the database.
     */
    public void addAlbum() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        //album.setReleaseDate(new Date());
        album.setAddedDate(new Date());
        album.setSalePrice(BigDecimal.ZERO);
        album.setRemoved(false);

        List<Genre> genreResults = genreDAO.findByName(genreName);

        album.setGenreCollection(genreResults);

        albumDAO.persist(album);

        System.out.println(album.getTitle());
        System.out.println(album.getId());

        try {
            externalContext.redirect("album.xhtml?albumID=" + album.getId());
        } catch (IOException ioe) {

        }
    }

    /**
     * Retrieves the album's genre, and assigns this value to a user's "last 
     * searched genre" value.
     */
    private void setLastSearchedGenre() {
        //get logged on user
        FacesContext fc = FacesContext.getCurrentInstance();
        User user = (User) fc.getExternalContext().getSessionMap().getOrDefault("User", null);

        //if user is logged on, save last searched genre
        if (user != null) {
            user.setLastSearchGenre(new ArrayList<>(this.albumToDisplay.getGenreCollection()).get(0));
            //System.out.println("SAVED GENRE " + this.searchedGenres.get(0).getGenre());
            user = this.userDao.update(user);

            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("User", user);
        }
    }

    /**
     * Accepts a user's input, saves it in an Album object, and updates the 
     * appropriate values in the database.
     */
    public void updateAlbum() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        if (albumToDisplay.getRemoved()) {
            albumToDisplay.setRemoveDate(new Date());
        } else {
            albumToDisplay.setRemoveDate(null);
        }

        List<Genre> genreResults = genreDAO.findByName(genreName);

        albumToDisplay.setGenreCollection(genreResults);

        albumDAO.update(albumToDisplay);

        try {
            externalContext.redirect("album.xhtml?albumID=" + albumToDisplay.getId());
        } catch (IOException ioe) {

        }
    }

    /**
     * Validates a user's input to ensure it is a valid genre in the database.
     * 
     * @param context   The current FacesContext.
     * @param component The UI component with the user's input.
     * @param value The value a user has input.
     */
    public void validateGenreName(FacesContext context, UIComponent component, Object value) {
        this.genreName = (String) value;
        System.out.println(genreName);
        
        List<Genre> genreResults = genreDAO.findByName(genreName);

        System.out.println("genres found: " + genreResults.size());
        
        //if no matches found, display error
        if (genreResults.isEmpty()) {
            String message = context.getApplication().evaluateExpressionGet(context, "Genre not found", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            throw new ValidatorException(msg);
        }
    }
}
