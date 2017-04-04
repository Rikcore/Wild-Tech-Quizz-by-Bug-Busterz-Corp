package bugbusterzcorp.wildtechquizz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;



public class QuizClass implements Parcelable {

    private ArrayList<QuestionClass> mQuestionList;
    private QuestionClass question;
    private String mUsername;
    private String mQuizzName;

    private QuizClass() {

    }


    public QuizClass(ArrayList<QuestionClass> questions, String username, String quizzName){

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
