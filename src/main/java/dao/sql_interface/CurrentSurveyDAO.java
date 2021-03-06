/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.sql_interface;

import java.util.List;
import javax.ejb.Stateless;
import jpa.CurrentSurvey;
import jpa.SurveyQuestion;

/**
 *
 * @author Johnny Lin
 */
public interface CurrentSurveyDAO extends Dao<CurrentSurvey> {
    List<CurrentSurvey> findAll();
    CurrentSurvey findActive();
    List<CurrentSurvey> findAllDescending();
}
