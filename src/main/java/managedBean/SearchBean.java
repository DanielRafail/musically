package managedBean;

import dao.sql_interface.AlbumDAO;
import dao.sql_interface.ArtistDAO;
import dao.sql_interface.GenreDAO;
import dao.sql_interface.TrackDAO;
import dao.sql_interface.UserDAO;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.Artist;
import jpa.Genre;
import jpa.Track;
import jpa.User;

/**
 * Bean that manages the search page and search engine.
 *
 * @author Johnny Lin
 */
@Named
@SessionScoped
public class SearchBean implements Serializable {

    @Inject
    private TrackDAO trackDao;

    @Inject
    private AlbumDAO albumDao;

    @Inject
    private ArtistDAO artistDao;

    @Inject
    private GenreDAO genreDao;

    @Inject
    private UserDAO userDao;

    private String keyword;

    private List<Album> searchedAlbums;
    private List<Track> searchedTracks;
    private List<Artist> searchedArtists;
    private List<Genre> searchedGenres;

    //filters
    private boolean checkboxAlbums = true;
    private boolean checkboxTracks = true;
    private boolean checkboxArtists = true;
    private boolean checkboxGenres = true;

    private java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");

    private String afterDate = "31-12-0000";
    private String beforeDate = dateFormatter.format(new java.util.Date());

    /**
     * Empty constructor for SearchBean
     */
    public SearchBean() {
    }

    /**
     * Processes the search with keyword and filters and redirect them to the
     * appropriate location. The filters are reset if there is a redirect to
     * prevent next time auto-redirect again when searching. When there is a
     * genre that matches the search keyword, it will be added to the user's
     * last searched genre. If there are multiple search results, the search
     * face will display all results.
     *
     * @return searched web page
     */
    public String search() {
        //System.out.println("Search term: " + keyword);

        //search for items
        this.searchAlbums();
        this.searchArtists();
        this.searchGenres();
        this.searchTracks();

        //apply filters here
        applyDateFilter();

        //single result processing here
        if (this.searchedAlbums.size() + this.searchedArtists.size()
                + this.searchedGenres.size() + this.searchedTracks.size() == 1) {
            System.out.println("SIZE IS ONE FOR ALL!");
            if (this.searchedArtists.size() == 1) {
                resetFilters();
                System.out.println("ONLY ONE RESULT FOR " + this.keyword);
                this.searchArtist(this.searchedArtists.get(0).getId());
            }
            if (this.searchedAlbums.size() == 1) {
                resetFilters();
                return "album?faces-redirect=true&albumID=" + this.searchedAlbums.get(0).getId();
            }
            if (this.searchedTracks.size() == 1) {
                resetFilters();
                return "track?faces-redirect=true&trackID=" + this.searchedTracks.get(0).getId();
            }
            if (this.searchedGenres.size() == 1) {
                resetFilters();
                return "albums.xhtml?faces-redirect=true&genre=" + this.searchedGenres.get(0).getGenre();
            }
        }

        //Verbose for search result and filters
        /*for (Album a : this.searchedAlbums) {
            System.out.println("Album: " + a.getTitle());
        }
        for (Track t : this.searchedTracks) {
            System.out.println("Track: " + t.getTitle());
        }
        for (Artist a : this.searchedArtists) {
            System.out.println("Artist: " + a.getArtistName());
        }
        for (Genre g : this.searchedGenres) {
            System.out.println("Genre: " + g.getGenre());
        }
        System.out.println("f_albums: " + this.checkboxAlbums);
        System.out.println("f_artists: " + this.checkboxArtists);
        System.out.println("f_genres: " + this.checkboxGenres);
        System.out.println("f_tracks: " + this.checkboxTracks);

        System.out.println("f_after: " + this.afterDate);
        System.out.println("f_before: " + this.beforeDate);*/
        return "search?redirect=true";
    }

    /**
     * Searches all albums with the matching keyword
     */
    private void searchAlbums() {
        if (this.keyword == null || this.keyword.equals("") || !this.checkboxAlbums) {
            this.searchedAlbums = new ArrayList<>();
        } else {
            this.searchedAlbums = this.albumDao.searchAllMatching(this.keyword);
        }
    }

    /**
     * Searches all tracks with the matching keyword
     */
    private void searchTracks() {
        if (this.keyword == null || this.keyword.equals("") || !this.checkboxTracks) {
            this.searchedTracks = new ArrayList<>();
        } else {
            this.searchedTracks = this.trackDao.searchAllMatching(this.keyword);
        }
    }

    /**
     * Searches all artists with the matching keyword
     */
    private void searchArtists() {
        if (this.keyword == null || this.keyword.equals("") || !this.checkboxArtists) {
            this.searchedArtists = new ArrayList<>();
        } else {
            this.searchedArtists = this.artistDao.searchAllMatching(this.keyword);
        }
    }

    /**
     * Searches all genre with the matching keyword, and sets the last searched
     * genre if the user is logged on.
     */
    private void searchGenres() {
        if (this.keyword == null || this.keyword.equals("") || !this.checkboxGenres) {
            this.searchedGenres = new ArrayList<>();
        } else {
            this.searchedGenres = this.genreDao.searchAllMatching(this.keyword);
            if (this.searchedGenres.size() != 0) {
                //System.out.println("setLastSearchedGenre");
                setLastSearchedGenre();
            }
        }
    }

    /**
     * Set the filters back to original settings.
     */
    private void resetFilters() {
        this.checkboxAlbums = true;
        this.checkboxTracks = true;
        this.checkboxArtists = true;
        this.checkboxGenres = true;

        this.dateFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");

        this.afterDate = "31-12-0000";
        this.beforeDate = this.dateFormatter.format(new java.util.Date());
    }

    /**
     * Filters tracks and albums to be between 2 dates.
     */
    private void applyDateFilter() {
        //first compares if both dates are not the default ones.
        //then the format must match with dashes and length
        if ((!this.afterDate.equals("31-12-0000")
                || !this.beforeDate.equals(this.dateFormatter.format(new Date())))
                && this.afterDate.length() == 10 && this.beforeDate.length() == 10
                && this.afterDate.charAt(2) == '-' && this.afterDate.charAt(5) == '-'
                && this.beforeDate.charAt(2) == '-' && this.beforeDate.charAt(5) == '-') {
            try {
                Date before = this.dateFormatter.parse(this.beforeDate); //today
                Date after = this.dateFormatter.parse(this.afterDate); //year 0

                //filter albums
                List<Album> unfilteredAlbums = this.searchedAlbums;
                this.searchedAlbums = new ArrayList<>();
                for (Album a : unfilteredAlbums) {
                    if (a.getReleaseDate().after(after) && a.getReleaseDate().before(before)) {
                        this.searchedAlbums.add(a);
                    }
                }

                //filter tracks
                List<Track> unfilteredTracks = this.searchedTracks;
                this.searchedTracks = new ArrayList<>();
                for (Track t : unfilteredTracks) {
                    if (t.getAlbum().getReleaseDate().after(after) && t.getAlbum().getReleaseDate().before(before)) {
                        this.searchedTracks.add(t);
                    }
                }
                return;
            } catch (ParseException pe) {
                //do not filter and reset dates

            }
        }
        //System.out.println("invalid dates");
        this.afterDate = "31-12-0000";
        this.beforeDate = this.dateFormatter.format(new Date());
    }

    /**
     * Sets last searched genre.
     */
    private void setLastSearchedGenre() {
        //get logged on user
        FacesContext fc = FacesContext.getCurrentInstance();
        User user = (User) fc.getExternalContext().getSessionMap().getOrDefault("User", null);

        //if user is logged on, save last searched genre
        if (user != null && this.searchedGenres.size() != 0) {
            user.setLastSearchGenre(this.searchedGenres.get(0));
            //System.out.println("SAVED GENRE " + this.searchedGenres.get(0).getGenre());
            user = this.userDao.update(user);

            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("User", user);
        }
    }

    /**
     * Search all albums from an artist. if the id is 0, will get ar
     *
     * @param id artist_id
     */
    public String searchArtist(long id) {
        System.out.println("init id " + id);
        if (id == 0) {
            String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("artist_id");
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().put("artist_id", null);

            try {
                id = Long.parseLong(param);
                System.out.println("new id " + id);
            } catch (NumberFormatException nfe) {
                this.resetFilters();
                return "search";
            }
        }

        //Checks:
        if (id <= 0) {
            this.resetFilters();
            return "serach";
        }

        Artist artist = this.artistDao.findById(id);
        System.out.println("Artist = " + artist);
        this.searchedAlbums = new ArrayList(artist.getAlbumCollection());
        this.searchedArtists = new ArrayList<>();
        this.searchedGenres = new ArrayList<>();
        this.searchedTracks = new ArrayList<>();

        if (this.searchedAlbums.size() == 1) {
            resetFilters();
            return "album?faces-redirect=true&albumID=" + this.searchedAlbums.get(0).getId();
        }

        return "search";
    }

    //standard getters and setters below
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Album> getSearchedAlbums() {
        return searchedAlbums;
    }

    public void setSearchedAlbums(List<Album> searchedAlbums) {
        this.searchedAlbums = searchedAlbums;
    }

    public List<Track> getSearchedTracks() {
        return searchedTracks;
    }

    public void setSearchedTracks(List<Track> searchedTracks) {
        this.searchedTracks = searchedTracks;
    }

    public List<Artist> getSearchedArtists() {
        return searchedArtists;
    }

    public void setSearchedArtists(List<Artist> searchedArtists) {
        this.searchedArtists = searchedArtists;
    }

    public List<Genre> getSearchedGenres() {
        return searchedGenres;
    }

    public void setSearchedGenres(List<Genre> searchedGenres) {
        this.searchedGenres = searchedGenres;
    }

    public boolean isCheckboxAlbums() {
        return checkboxAlbums;
    }

    public void setCheckboxAlbums(boolean checkboxAlbums) {
        this.checkboxAlbums = checkboxAlbums;
    }

    public boolean isCheckboxTracks() {
        return checkboxTracks;
    }

    public void setCheckboxTracks(boolean checkboxTracks) {
        this.checkboxTracks = checkboxTracks;
    }

    public boolean isCheckboxArtists() {
        return checkboxArtists;
    }

    public void setCheckboxArtists(boolean checkboxArtists) {
        this.checkboxArtists = checkboxArtists;
    }

    public boolean isCheckboxGenres() {
        return checkboxGenres;
    }

    public void setCheckboxGenres(boolean checkboxGenres) {
        this.checkboxGenres = checkboxGenres;
    }

    public String getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(String afterDate) {
        this.afterDate = afterDate;
    }

    public String getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(String beforeDate) {
        this.beforeDate = beforeDate;
    }
}
