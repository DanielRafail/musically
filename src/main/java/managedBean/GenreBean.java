package managedBean;

import dao.sql_interface.GenreDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Genre;

/**
 * This class manage genres page
 *
 * @author Cerba Mihail
 */
@Named
@SessionScoped
public class GenreBean implements Serializable {

    @Inject
    GenreDAO GenreDAO;

    /**
     *
     * @return List<Genre> List with all genres from Database
     */
    public List<Genre> getAllGenres() {
        return GenreDAO.findAll();
    }

    public String goToAlbums() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String genre = params.get("genre");
        return "albums?faces-redirect=true&genre=" + genre;
    }

}
