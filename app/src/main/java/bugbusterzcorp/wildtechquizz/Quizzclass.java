package bugbusterzcorp.wildtechquizz;

import java.io.Serializable;
import java.util.ArrayList;



public class Quizzclass implements Serializable {

    private ArrayList<QuestionClass> mQuestionList;
    private QuestionClass question;
    private String mUsername;
    private String mQuizzName;

    private Quizzclass() {

    }


    public Quizzclass(ArrayList<QuestionClass> questions, String username, String quizzName){

        mQuestionList = questions;
        mUsername = username;
        mQuizzName = quizzName;

    }

    public ArrayList<QuestionClass> getQuestionList() {
        return mQuestionList;
    }

    public void setQuestionList(ArrayList<QuestionClass> mQuestionList) {
        this.mQuestionList = mQuestionList;
    }
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getQuizzName() {
        return mQuizzName;
    }

    public void setQuizzName(String mQuizzName) {
        this.mQuizzName = mQuizzName;
    }
}

