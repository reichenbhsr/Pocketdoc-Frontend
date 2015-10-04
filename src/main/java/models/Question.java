package models;

import database.mappers.AnswerConnector;
import models.intermediateClassModels.QuestionDescription;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Entity, also eins zu eins Abbildung der Datenbanktabelle questions
 *
 * @author Oliver Frischknecht
 */

public class Question {

    private int id;
    private String name;

    private int answerYesId;
    private Answer answerYes;

    private int answerNoId;
    private Answer answerNo;

    private int dependsOnId;
    private Answer dependsOn;

    private boolean isSymptom;

    private Set<QuestionDescription> descriptions;

    public Question() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAnswerYesId(int answerYesId){ this.answerYesId = answerYesId; }
    public Answer getAnswerYes() {

        if (answerYes == null) {
            AnswerConnector con = new AnswerConnector();
            answerYes = con.read(answerYesId);
        }

        return answerYes;
    }
    public void setAnswerYes(Answer answerYes) {
        this.answerYes = answerYes;
    }

    public void setAnswerNoId(int answerNoId){ this.answerNoId = answerNoId; }
    public Answer getAnswerNo() {

        if (answerNo == null){
            AnswerConnector con = new AnswerConnector();
            answerNo = con.read(answerNoId);
        }

        return answerNo;
    }
    public void setAnswerNo(Answer answerNo) {
        this.answerNo = answerNo;
    }

    public void setDependsOnId(int dependsOnId){ this.dependsOnId = dependsOnId; }
    public Answer getDependsOn() {

        if (dependsOn == null){
            AnswerConnector con = new AnswerConnector();
            dependsOn = con.read(dependsOnId);
        }

        return dependsOn;
    }
    public void setDependsOn(Answer dependsOn) {
        this.dependsOn = dependsOn;
    }

    public boolean isSymptom() {
        return isSymptom;
    }
    public void setSymptom(boolean isSymptom) {
        this.isSymptom = isSymptom;
    }

    public Set<QuestionDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<QuestionDescription> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Question) {
            return ((Question) obj).getId() == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
