package bugbusterzcorp.wildtechquizz;

import android.widget.EditText;




public class QuestionClass {

    private String mQuestion;


    private String mChoiceA;
    private String mChoiceB;
    private String mCorrectAnswer;


    private QuestionClass(){}

    public QuestionClass(String question, String choiceA, String choiceB, String correctAnswer){
        mQuestion = question;
        mChoiceA = choiceA;
        mChoiceB = choiceB;
        mCorrectAnswer = correctAnswer;

    }


    public String getChoiceA() {
        return mChoiceA;
    }

    public String getChoiceB() {
        return mChoiceB;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }




}
