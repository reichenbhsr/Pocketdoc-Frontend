package managers;

import database.mappers.FollowupConntector;
import database.mappers.UserConnector;
import database.mappers.intermediateClassMappers.AnswerToHistoryConnector;
import models.Followup;
import models.Question;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Diese Klasse dient als Mittelstück der Applikation wenn es um Objekte der Klasse Followup geht.
 * <p>
 * Wenn ein solches Objekt verändert, gelesen oder etwas damit gemacht werden soll. Dann muss diese Klasse dafür aufgerufen werden.
 * <p>
 * Für mehr Informationen, siehe die Javadoc von {@link managers.BasicManager}
 *
 * @author Roman Eichenberger
 */
public class FollowupManager implements BasicManager<Followup>{

    private FollowupConntector followupConnector;
    private AnswerToHistoryConnector answerToHistoryConnector;
    private UserConnector userConnector;

    /**
     * Dieser Konstruktor soll offiziell gebraucht werden.
     */
    public FollowupManager(){

        followupConnector = new FollowupConntector();
        userConnector = new UserConnector();
        answerToHistoryConnector = new AnswerToHistoryConnector();
    }

    public int createFollowup(Followup followup){
        int id = followupConnector.create(followup);
        followup.setId(id);
        answerToHistoryConnector.storeCurrentRunToFollowup(followup);

        return id;
    }

    @Override
    public Followup add(){
        return null;
    }

    @Override
    public Followup update(Followup followup) {

        return null;
    }

    @Override
    public Followup get(int id) {
        return null;
    }

    @Override
    public ArrayList<Followup> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {
        followupConnector.delete(id);
        answerToHistoryConnector.deleteFromFollowup(id);
    }

    public ArrayList<Followup> getFollowupsOfUser(int userId){

        return followupConnector.readFromUser(userId);
    }

    public ArrayList<Question> getYesAnsweredQuestionOfFollowup(Followup followup){
        return answerToHistoryConnector.getYesAnsweredQuestionsFromFollowup(followup);
    }

}