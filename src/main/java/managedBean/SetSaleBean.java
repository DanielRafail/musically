package managedBean;

import dao.mysql_implementation.AlbumDAOImpl;
import dao.mysql_implementation.TrackDAOImpl;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Album;
import jpa.Track;

/**
 * The SetSaleBean is a JSF managed bean responsible for representing tracks 
 * and albums for the purpose of modifying their sale prices.
 * 
 * @author P. Bellefleur
 */


@SessionScoped
@Named(value = "setSaleBean")
public class SetSaleBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //injection for DAO objects
    @Inject
    AlbumDAOImpl albumDao;
    @Inject
    TrackDAOImpl trackDao;
    
    //lists of search results 
    private List<Album> albumList;
    private List<Track> trackList;
    
    //field to store user input
    private String toSearch;
    
    //flags to track progress & errors; use to display errors
    private boolean isSearchComplete = false;
    private boolean isUpdateComplete = false;
    private boolean isTrackUpdateComplete = false;
    private boolean isErrorUpdatingTrack = false;
    private boolean isErrorUpdatingAlbum = false;
    
    public List<Album> getAlbumList() {
        return this.albumList;
    }
    
    public List<Track> getTrackList() {
        return this.trackList;
    }
    
    public String getToSearch() {
        return this.toSearch;
    }
    
    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }

    public boolean isIsSearchComplete() {
        return isSearchComplete;
    }

    public boolean isIsUpdateComplete() {
        return isUpdateComplete;
    }
    
    public boolean isIsTrackUpdateComplete() {
        return isTrackUpdateComplete;
    }

    public boolean isIsErrorUpdatingTrack() {
        return isErrorUpdatingTrack;
    }

    public boolean isIsErrorUpdatingAlbum() {
        return isErrorUpdatingAlbum;
    }
     
    /**
     * Uses the user's input to search for Tracks and Albums in the database 
     * with a specific name. Lists of search results can be empty if no results 
     * are found.
     */
    public void getAlbumsAndTracksFromSearch() {
        //reset update flags
        isUpdateComplete = false;
        isTrackUpdateComplete = false;
        //use DAO classes to call database 
        albumList = albumDao.findByName(toSearch);
        trackList = trackDao.findByName(toSearch);
        //mark search flag as having completed search
        isSearchComplete = true;
    }
    
    /**
     * Updates all Tracks and Albums stored in their relevant lists. As the 
     * only field the user can update is the sale price, this will update the 
     * sale price in the database.
     */
    public void updateSales() {
        //reset search flag
        isSearchComplete = false;
        //mark update flags as (tentatively) having completed update
        isUpdateComplete = true;
        isTrackUpdateComplete = true;
        //prepare external context
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        //mark error updating flag as (tentatively) false; iterate through list
        isErrorUpdatingAlbum = false;
        for (Album albumToUpdate : albumList) {
            if (albumToUpdate.getSalePrice().compareTo(albumToUpdate.getListPrice()) >= 0) {
                //if sale price is greater than or equal to list price, 
                //don't update & set update error flag to true
                albumToUpdate.setSalePrice(BigDecimal.ZERO);
                isErrorUpdatingAlbum = true;
                isUpdateComplete = false;
            } else {
                //if sale price is less than list price, update in database
                albumDao.update(albumToUpdate);
            }
        }
        
        //mark error updating flag as (tentatively) false; iterate through list
        isErrorUpdatingTrack = false;
        for (Track trackToUpdate : trackList) {
            if (trackToUpdate.getSalePrice().compareTo(trackToUpdate.getListPrice()) >= 0) {
                //if sale price is greater than or equal to list price, 
                //don't update & set update error flag to true
                trackToUpdate.setSalePrice(BigDecimal.ZERO);
                isErrorUpdatingTrack = true;
                isTrackUpdateComplete = false;
            } else {
                //if sale price is less than list price, update in database
                trackDao.update(trackToUpdate);
            }
        }
        
        //redirect to sales page once complete
        try {
            externalContext.redirect("sales.xhtml");
        } catch (IOException ioe) {
            
        }
    }
    
}
