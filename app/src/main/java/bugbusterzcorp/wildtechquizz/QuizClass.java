package bugbusterzcorp.wildtechquizz;

import java.util.ArrayList;



public class QuizClass {

    private ArrayList<QuestionClass> mQuestionList;
    private QuestionClass question;



    public QuizClass(ArrayList<QuestionClass> questions){

        mQuestionList = questions;

    }

    public ArrayList<QuestionClass> getQuestionList() {
        return mQuestionList;
    }

    public void setmQuestionList(ArrayList<QuestionClass> mQuestionList) {
        this.mQuestionList = mQuestionList;
    }



}
