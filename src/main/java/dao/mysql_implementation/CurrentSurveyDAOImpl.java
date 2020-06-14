package dao.mysql_implementation;

import dao.sql_interface.CurrentSurveyDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.CurrentSurvey;
import jpa.SurveyQuestion;

/**
 * The CurrentSurveyDAOImpl class defines a data access object (DAO) that 
 * accesses the Current Survey table in the Musically database.
 * 
 * @author Johnny Lin
 */
@SessionScoped
public class CurrentSurveyDAOImpl extends JpaDaoImpl<CurrentSurvey> 
        implements CurrentSurveyDAO, Serializable {

    public CurrentSurveyDAOImpl() {
        super(CurrentSurvey.class);
    }

    /**
     * Finds all entries in the table, and stores them in a List of 
     * CurrentSurvey objects.
     * 
     * @return  a List, containing all CurrentSurveys in the database.
     */
    @Override
    public List<CurrentSurvey> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<CurrentSurvey>) q.getResultList();
    }

    @Override
    public CurrentSurvey findActive() {
        Query q = entityManager.createQuery("SELECT e FROM CurrentSurvey e where e.questionId.active = true");
        return ((List<CurrentSurvey>) q.getResultList()).get(0);
    }

    @Override
    public List<CurrentSurvey> findAllDescending() {
        Query q = entityManager.createQuery("SELECT e FROM CurrentSurvey e order by e.questionId.id desc");
        return ((List<CurrentSurvey>) q.getResultList());
    }
    
    
}
