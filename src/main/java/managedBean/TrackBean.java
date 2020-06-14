
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.mysql_implementation.UserDAOImpl;
import dao.mysql_implementation.AlbumDAOImpl;
import dao.sql_interface.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Genre;
import jpa.Track;
import jpa.Album;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;



/**
 * The TrackBean class is a JSF managed bean responsible for representing a 
 * specific track in the store.
 * @author Cerba Mihail, P. Bellefleur
 */
@Named
@SessionScoped
public class TrackBean implements Serializable {
    
    @Inject
    private TrackDAO trackDAO;
    
    @Inject
    private AlbumDAO albumDao;
    
    private String trackID;
    private Track trackToDisplay;
    private Track track = new Track();
    private String albumName = "";
    
    
    /**
     * Prepares the track to be displayed by fetching it from the database.
     */
    public void setUpTrack(){
        this.trackToDisplay =  trackDAO.findById(Long.parseLong(trackID));
        this.albumName = trackToDisplay.getAlbum().getTitle();
    }

    public Track getTrackToDisplay() {
        return trackToDisplay;
    }

    public void setTrackToDisplay(Track trackToDisplay) {
        this.trackToDisplay = trackToDisplay;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }



    /**
     * Creates a new instance of TrackBean.
     */
    public TrackBean() {
        
    }
    
    public Track getTrack() {
        return this.track;
    }
    
    public String getAlbumName() {
        return this.albumName;
    }
    
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Updates a track's information in the database, persists it, then 
     * redirects to the track info page.
     * 
     * @return  A String, representing the page to load after data is persisted.
     */
    public void updateTrack() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        
        if(trackToDisplay.getRemoved()) {
            trackToDisplay.setRemoveDate(new Date());
        } else {
            trackToDisplay.setRemoveDate(null);
        }
        
        List<Album> titleResults = albumDao.findByName(albumName);
        trackToDisplay.setAlbum(titleResults.get(0));
        
        trackDAO.update(trackToDisplay);
        
        try {
            externalContext.redirect("track.xhtml?trackID=" + trackToDisplay.getId());
        } catch (IOException ioe) {
            
        }
    }
    
    /**
     * Adds a new track to the database, then redirects to the track info page. 
     * 
     * @return  A String, representing the page to load after data is persisted.
     */
    public void addTrack() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        track.setSalePrice(BigDecimal.ZERO);
        track.setRemoved(false);
        track.setRemoveDate(null);
        
        List<Album> titleResults = albumDao.findByName(albumName);
        track.setAlbum(titleResults.get(0));
        
        System.out.println(track.getTitle());
        System.out.println(track.getSongwriter());
        System.out.println(track.getPlayLength());
        System.out.println(track.getAlbumSelection());
        System.out.println(track.getCostPrice());
        System.out.println(track.getListPrice());
        System.out.println(track.getSalePrice());
        System.out.println(track.getSellableAsSingle());
        System.out.println(track.getRemoved());
        System.out.println(track.getRemoveDate());
        System.out.println(track.getAlbum().getTitle());
        
        trackDAO.persist(track);
        
        try {
            externalContext.redirect("track.xhtml?trackID=" + track.getId());
        } catch (IOException ioe) {
            
        }
    }
    
    /**
     * Validates a user's input by ensuring they have typed a valid album name.
     * 
     * @param context   The current FacesContext.
     * @param component The UI component containing user input.
     * @param value The value entered by the user.
     */
    public void validateAlbumName(FacesContext context, UIComponent component, Object value) {
        this.albumName = (String) value;
        System.out.println(albumName);
        
        List<Album> titleResults = albumDao.findByName(albumName);
        
        System.out.println("albums found: " + titleResults.size());
        
        if(titleResults.isEmpty()) {
            String message = context.getApplication().evaluateExpressionGet(context, "Album not found", String.class);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
            throw new ValidatorException(msg);
        }
    }
}