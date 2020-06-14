package dao.mysql_implementation;

import dao.sql_interface.SurveyQuestionDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import jpa.SurveyQuestion;

/**
 * The SurveyQuestionDAOImpl class defines a data access object (DAO) that 
 * accesses the Survey Questions table in the Musically database.
 * 
 * @author Johnny Lin
 */
@SessionScoped
public class SurveyQuestionDAOImpl extends JpaDaoImpl<SurveyQuestion> 
        implements SurveyQuestionDAO, Serializable  {

    public SurveyQuestionDAOImpl() {
        super(SurveyQuestion.class);
    }

    
    /**
     * Finds all entries in the table, and stores them as SurveyQuestion objects
     * in a List.
     * 
     * @return  a List of all SurveyQuestions in the database.
     */
    @Override
    public List<SurveyQuestion> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e");
        return (List<SurveyQuestion>) q.getResultList();
    }

    @Override
    public SurveyQuestion findActive() {
        Query q = entityManager.createQuery("SELECT e FROM SurveyQuestion e where e.active = true");
        return  ((List<SurveyQuestion>) q.getResultList()).get(0);
    }
    
}
