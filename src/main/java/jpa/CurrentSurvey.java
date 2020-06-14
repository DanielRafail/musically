/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The User class is a JPA class that represents the "CurrentSurveys" table from the 
 * Musically database as a Java entity.
 * 
 * @author Cerba Mihail
 * @author Johnny Lin
 */
@Entity
@Table(name = "current_survey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrentSurvey.findAll", query = "SELECT c FROM CurrentSurvey c")
    , @NamedQuery(name = "CurrentSurvey.findById", query = "SELECT c FROM CurrentSurvey c WHERE c.id = :id")
    , @NamedQuery(name = "CurrentSurvey.findByAnswer1Count", query = "SELECT c FROM CurrentSurvey c WHERE c.answer1Count = :answer1Count")
    , @NamedQuery(name = "CurrentSurvey.findByAnswer2Count", query = "SELECT c FROM CurrentSurvey c WHERE c.answer2Count = :answer2Count")
    , @NamedQuery(name = "CurrentSurvey.findByAnswer3Count", query = "SELECT c FROM CurrentSurvey c WHERE c.answer3Count = :answer3Count")
    , @NamedQuery(name = "CurrentSurvey.findByAnswer4Count", query = "SELECT c FROM CurrentSurvey c WHERE c.answer4Count = :answer4Count")})
public class CurrentSurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_1_count")
    private int answer1Count;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_2_count")
    private int answer2Count;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_3_count")
    private int answer3Count;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_4_count")
    private int answer4Count;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SurveyQuestion questionId;

    public CurrentSurvey() {
    }

    public CurrentSurvey(Long id) {
        this.id = id;
    }

    public CurrentSurvey(Long id, int answer1Count, int answer2Count, int answer3Count, int answer4Count) {
        this.id = id;
        this.answer1Count = answer1Count;
        this.answer2Count = answer2Count;
        this.answer3Count = answer3Count;
        this.answer4Count = answer4Count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnswer1Count() {
        return answer1Count;
    }

    public void setAnswer1Count(int answer1Count) {
        this.answer1Count = answer1Count;
    }

    public int getAnswer2Count() {
        return answer2Count;
    }

    public void setAnswer2Count(int answer2Count) {
        this.answer2Count = answer2Count;
    }

    public int getAnswer3Count() {
        return answer3Count;
    }

    public void setAnswer3Count(int answer3Count) {
        this.answer3Count = answer3Count;
    }

    public int getAnswer4Count() {
        return answer4Count;
    }

    public void setAnswer4Count(int answer4Count) {
        this.answer4Count = answer4Count;
    }

    public SurveyQuestion getQuestionId() {
        return questionId;
    }

    public void setQuestionId(SurveyQuestion questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof CurrentSurvey)) {
            return false;
        }
        CurrentSurvey other = (CurrentSurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.CurrentSurvey[ id=" + id + " ]";
    }
    
}
