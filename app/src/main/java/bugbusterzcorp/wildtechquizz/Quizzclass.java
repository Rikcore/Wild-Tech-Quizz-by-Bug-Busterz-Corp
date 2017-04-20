package bugbusterzcorp.wildtechquizz;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;



public class Quizzclass implements Serializable {

    private ArrayList<QuestionClass> mQuestionList;
    private String mUsername;
    private String mQuizzName;
    private String mCreatorId;
    private String mUrlQuizz;


    private Quizzclass() {

    }


    public Quizzclass(ArrayList<QuestionClass> questions, String username, String quizzName, String creatorId, String urlQuizz){

        mQuestionList = questions;
        mUsername = username;
        mQuizzName = quizzName;
        mCreatorId = creatorId;
        mUrlQuizz = urlQuizz;

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

    public String getmCreatorId() {
        return mCreatorId;
    }

    public void setmCreatorId(String mCreatorId) {
        this.mCreatorId = mCreatorId;
    }


    public String getUrlQuizz() {
        return mUrlQuizz;
    }

    public void setmUrlQuizz(String mUrlQuizz) {
        this.mUrlQuizz = mUrlQuizz;
    }
}

