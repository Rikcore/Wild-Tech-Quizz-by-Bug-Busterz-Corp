package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class PlayQuizzActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private String quizzString;
    private ArrayList questionList;
    private TextView textViewChoiceA;
    private TextView textViewChoiceB;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private QuestionClass newQuestion;


    private int positionDansLesQuestions = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quizz);
        Intent goPlay = getIntent();
        quizzString = goPlay.getStringExtra("quizzRef");
        QuizClass newQuiz = (QuizClass) goPlay.getExtras().get("quizzObject");
        questionList = newQuiz.getQuestionList();


        newQuestion = (QuestionClass) questionList.get(0);



        textViewChoiceA = (TextView) findViewById(R.id.textViewChoiceA);
        textViewChoiceB = (TextView) findViewById(R.id.textViewChoiceB);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewScore = (TextView) findViewById(R.id.textViewScore);



        textViewChoiceA.setText(newQuestion.getChoiceA());
        textViewChoiceB.setText(newQuestion.getChoiceB());
        textViewQuestion.setText(newQuestion.getmQuestion());


        textViewChoiceA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newQuestion.getChoiceA().equals(newQuestion.getCorrectAnswer())){
                    textViewChoiceA.setBackgroundColor(Color.GREEN);
                    score++;
                    textViewScore.setText(score+"/"+questionList.size());
                    textViewChoiceA.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceA.setBackgroundColor(Color.BLUE);
                        }
                    }, 150);

                }
                else{
                    textViewChoiceA.setBackgroundColor(Color.RED);
                    textViewChoiceA.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceA.setBackgroundColor(Color.BLUE);
                        }
                    }, 150);
                }

                if(positionDansLesQuestions < questionList.size()-1) {

                    positionDansLesQuestions++;
                    newQuestion = (QuestionClass) questionList.get(positionDansLesQuestions);
                    textViewChoiceA.setText(newQuestion.getChoiceA());
                    textViewChoiceB.setText(newQuestion.getChoiceB());
                    textViewQuestion.setText(newQuestion.getmQuestion());
                }
                else{
                    finish();
                }

            }

        });

        textViewChoiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newQuestion.getChoiceB().equals(newQuestion.getCorrectAnswer())){

                    textViewChoiceB.setBackgroundColor(Color.GREEN);
                    score++;
                    textViewScore.setText(score+"/"+questionList.size());
                    textViewChoiceB.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceB.setBackgroundColor(Color.BLUE);
                        }
                    }, 150);

                }
                else{

                    textViewChoiceB.setBackgroundColor(Color.RED);
                    textViewChoiceB.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceB.setBackgroundColor(Color.BLUE);
                        }
                    }, 150);
                }

                if(positionDansLesQuestions < questionList.size()-1) {

                    positionDansLesQuestions++;
                    newQuestion = (QuestionClass) questionList.get(positionDansLesQuestions);
                    textViewChoiceA.setText(newQuestion.getChoiceA());
                    textViewChoiceB.setText(newQuestion.getChoiceB());
                    textViewQuestion.setText(newQuestion.getmQuestion());
                }
                else{
                    finish();
                }

            }
        });
    }


}
