/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.sql_interface.CurrentSurveyDAO;
import dao.sql_interface.SurveyQuestionDAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jpa.CurrentSurvey;
import jpa.SurveyQuestion;

/**
 * SurveyManagementBean will be able to manage surveys, 
 * made for managers. It will display the current active survey in the home page.
 * 
 * @author Johnny Lin
 */
@Named
@SessionScoped
public class SurveyManagementBean implements Serializable {
    @Inject
    private CurrentSurveyDAO currentSurveyDao;
    @Inject
    private SurveyQuestionDAO surveyQuestionDao;
    
    
    private SurveyQuestion survey;
    private CurrentSurvey surveyData;
    
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    
    private List<CurrentSurvey> allSurveys;
    
    /**
     * Add survey to the db. fields must not be empty.
     * @return pollsmanagement page
     */
    public String addSurvey() {
        if (this.question.length() == 0 || this.answer1.length() == 0 
                || this.answer2.length() == 0 || this.answer3.length() == 0 
                || this.answer4.length() == 0) return "pollsmanagement";
        
//        System.out.println("Q: " + question);
//        System.out.println("A1: " + answer1);
//        System.out.println("A2: " + answer2);
//        System.out.println("A3: " + answer3);
//        System.out.println("A4: " + answer4);
        
        SurveyQuestion sq = new SurveyQuestion();
        sq.setActive(false);
        sq.setQuestion(question);
        sq.setAnswer1(answer1);
        sq.setAnswer2(answer2);
        sq.setAnswer3(answer3);
        sq.setAnswer4(answer4);
        sq = this.surveyQuestionDao.persist(sq);
        
        CurrentSurvey cs = new CurrentSurvey();
        cs.setAnswer1Count(0);
        cs.setAnswer2Count(0);
        cs.setAnswer3Count(0);
        cs.setAnswer4Count(0);
        cs.setQuestionId(sq);
        this.currentSurveyDao.persist(cs);
        
        this.question = "";
        this.answer1 = "";
        this.answer2 = "";
        this.answer3 = "";
        this.answer4 = "";
        
        return "pollsmanagement";
    }
    
    /**
     * set the survey as the active survey for the front page
     * 
     * @param surveyQuestion Survey Question
     * @return pollsmanagement
     */
    public String setActiveSurvey(SurveyQuestion surveyQuestion) {
        if (surveyQuestion == null) return "404";
        
        this.survey.setActive(false);
        this.surveyQuestionDao.update(survey);
        
        surveyQuestion.setActive(true);
        this.surveyQuestionDao.update(surveyQuestion);
        
        return "pollsmanagement";
    }
    
    /**
     * gets the currently active survey and it's data
     */
    public void setupSurvey() {
        this.surveyData = this.currentSurveyDao.findActive();
        this.survey = surveyData.getQuestionId();
    }
    
    /**
     * Votes for the selected question
     * 
     * @param question question number (1-4)
     * @return index page
     */
    public String vote(int question) {
        switch (question) {
            case 1:
                this.surveyData.setAnswer1Count(this.surveyData.getAnswer1Count()+1);
                this.surveyData = this.currentSurveyDao.update(surveyData);
                break;
            case 2:
                this.surveyData.setAnswer2Count(this.surveyData.getAnswer2Count()+1);
                this.surveyData = this.currentSurveyDao.update(surveyData);
                break;
            case 3:
                this.surveyData.setAnswer3Count(this.surveyData.getAnswer3Count()+1);
                this.surveyData = this.currentSurveyDao.update(surveyData);
                break;
            case 4:
                this.surveyData.setAnswer4Count(this.surveyData.getAnswer4Count()+1);
                this.surveyData = this.currentSurveyDao.update(surveyData);
                break;
            default:
        }
        return "index";
    }
    
    /**
     * Gets all surveys from the database.
     */
    public void setupManagement(){
        allSurveys = this.currentSurveyDao.findAllDescending();
    }
    
    //standard getters and setters
    public SurveyQuestion getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyQuestion survey) {
        this.survey = survey;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    
    public CurrentSurvey getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(CurrentSurvey surveyData) {
        this.surveyData = surveyData;
    }

    public List<CurrentSurvey> getAllSurveys() {
        return allSurveys;
    }

    public void setAllSurveys(List<CurrentSurvey> allSurveys) {
        this.allSurveys = allSurveys;
    }
    
}
