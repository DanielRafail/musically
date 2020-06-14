/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The User class is a JPA class that represents the "SurveyQuestions" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 * @author Johnny Lin
 */
@Entity
@Table(name = "survey_questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestion.findAll", query = "SELECT s FROM SurveyQuestion s")
    , @NamedQuery(name = "SurveyQuestion.findById", query = "SELECT s FROM SurveyQuestion s WHERE s.id = :id")
    , @NamedQuery(name = "SurveyQuestion.findByQuestion", query = "SELECT s FROM SurveyQuestion s WHERE s.question = :question")
    , @NamedQuery(name = "SurveyQuestion.findByAnswer1", query = "SELECT s FROM SurveyQuestion s WHERE s.answer1 = :answer1")
    , @NamedQuery(name = "SurveyQuestion.findByAnswer2", query = "SELECT s FROM SurveyQuestion s WHERE s.answer2 = :answer2")
    , @NamedQuery(name = "SurveyQuestion.findByAnswer3", query = "SELECT s FROM SurveyQuestion s WHERE s.answer3 = :answer3")
    , @NamedQuery(name = "SurveyQuestion.findByAnswer4", query = "SELECT s FROM SurveyQuestion s WHERE s.answer4 = :answer4")})
public class SurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "question")
    private String question;
    
    @NotNull
    @Column(name = "active")
    private boolean active = false;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "answer_1")
    private String answer1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "answer_2")
    private String answer2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "answer_3")
    private String answer3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "answer_4")
    private String answer4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<CurrentSurvey> currentSurveyCollection;

    public SurveyQuestion() {
    }

    public SurveyQuestion(Long id) {
        this.id = id;
    }

    public SurveyQuestion(Long id, String question, String answer1, String answer2, String answer3, String answer4) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlTransient
    public Collection<CurrentSurvey> getCurrentSurveyCollection() {
        return currentSurveyCollection;
    }

    public void setCurrentSurveyCollection(Collection<CurrentSurvey> currentSurveyCollection) {
        this.currentSurveyCollection = currentSurveyCollection;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestion)) {
            return false;
        }
        SurveyQuestion other = (SurveyQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.SurveyQuestion[ id=" + id + " ]";
    }
    
}
