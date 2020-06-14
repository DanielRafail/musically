package managedBean;

import dao.mysql_implementation.ReviewDAOImpl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.Review;

/**
 * The ReviewApprovalBean class is a JSF managed bean responsible for adding 
 * and removing approval from reviews on tracks and albums in the database. 
 * 
 * @author P. Bellefleur
 */

@SessionScoped
@Named(value = "reviewApprovalBean")
public class ReviewApprovalBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    ReviewDAOImpl reviewDao;
    
    private List<Review> reviewList;
    
    public List<Review> getReviewList() {
        return this.reviewList;
    }
    
    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
    
    /**
     * Fetches all reviews from the database & stores them in a list.
     */
    public void getAllReviews() {
        reviewList = reviewDao.findAll();
        System.out.println("all reviews fetched. found: " + reviewList.size());
    }
    
    /**
     * Fetches all approved reviews for a specific album & stores them in a list.
     */
    public void setUpReviewsForAlbum() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
        String albumID = params.get("albumID");
        this.reviewList = reviewDao.findAllByAlbum(Long.parseLong(albumID));
    }
    
    /**
     * Fetches all approved reviews for a specific track & stores them in a list.
     */
    public void setUpReviewsForTrack() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
        String trackID = params.get("trackID");
        this.reviewList = reviewDao.findAllByTrack(Long.parseLong(trackID));
    }
    
    /**
     * Approves a review at a given index in the review list. Sets its 
     * "approved" flag to true, then updates the database.
     */
    public void approve() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        int index = Integer.parseInt(params.get("index"));
        
        System.out.println(index);
        
        reviewList.get(index).setApproved(true);
        reviewDao.update(reviewList.get(index));
    }
    
    /**
     * Denies a review at a given index in the review list. Sets its 
     * "approved" flag to false, then updates the database.
     */
    public void deny() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        int index = Integer.parseInt(params.get("index"));
        
        System.out.println(index);
        
        reviewList.get(index).setApproved(false);
        reviewDao.update(reviewList.get(index));
    }
}
