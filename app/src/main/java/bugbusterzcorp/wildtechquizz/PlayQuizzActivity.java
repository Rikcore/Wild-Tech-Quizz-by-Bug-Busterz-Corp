package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class PlayQuizzActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private String quizzString;
    private ArrayList questionList;
    private TextView textViewChoiceA;
    private TextView textViewChoiceB;
    private TextView textViewQuestion;
    private TextView textViewTimer;
    private TextView textViewScore;
    private QuestionClass newQuestion;
    private SoundPlayer sound;
    private CountDownTimer timer;

    private int positionDansLesQuestions = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quizz);
        Intent goPlay = getIntent();
        quizzString = goPlay.getStringExtra("quizzRef");
        Quizzclass newQuiz = (Quizzclass) goPlay.getExtras().get("quizzObject");
        questionList = newQuiz.getQuestionList();
        sound = new SoundPlayer(this);

        newQuestion = (QuestionClass) questionList.get(0);

        sound.playBoogieSound();


        textViewChoiceA = (TextView) findViewById(R.id.textViewChoiceA);
        textViewChoiceB = (TextView) findViewById(R.id.textViewChoiceB);
        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);

        textViewChoiceA.setText(newQuestion.getChoiceA());
        textViewChoiceB.setText(newQuestion.getChoiceB());
        textViewQuestion.setText(newQuestion.getmQuestion());


        // TIMER

        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished >= 6000) {
                    textViewTimer.setText(" " + millisUntilFinished / 1000);
                    textViewTimer.setTextColor(getResources().getColor(R.color.green));
                } else {
                    textViewTimer.setText(" " + millisUntilFinished / 1000);
                    textViewTimer.setTextColor(getResources().getColor(R.color.red));

                }

            }

            public void onFinish() {
                UpdateQuestion();


            }
        }.start();



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
                            textViewChoiceA.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }, 150);

                }
                else{
                    textViewChoiceA.setBackgroundColor(Color.RED);
                    textViewChoiceA.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceA.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }, 150);
                }

                if(positionDansLesQuestions < questionList.size()-1) {

                    positionDansLesQuestions++;
                    newQuestion = (QuestionClass) questionList.get(positionDansLesQuestions);
                    textViewChoiceA.setText(newQuestion.getChoiceA());
                    textViewChoiceB.setText(newQuestion.getChoiceB());
                    textViewQuestion.setText(newQuestion.getmQuestion());
                    timer.start();

                }
                else{
                    Intent scoreIntent = new Intent(PlayQuizzActivity.this, ScoreActivity.class);
                    scoreIntent.putExtra("score", score);
                    scoreIntent.putExtra("total", questionList.size());
                    scoreIntent.putExtra("quizzRef", quizzString);
                    startActivity(scoreIntent);
                    timer.cancel();
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
                            textViewChoiceB.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }, 150);

                }
                else{

                    textViewChoiceB.setBackgroundColor(Color.RED);
                    textViewChoiceB.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textViewChoiceB.setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }, 150);
                }

                if(positionDansLesQuestions < questionList.size()-1) {

                    positionDansLesQuestions++;
                    newQuestion = (QuestionClass) questionList.get(positionDansLesQuestions);
                    textViewChoiceA.setText(newQuestion.getChoiceA());
                    textViewChoiceB.setText(newQuestion.getChoiceB());
                    textViewQuestion.setText(newQuestion.getmQuestion());
                    timer.start();


                }
                else{
                    Intent scoreIntent = new Intent(PlayQuizzActivity.this, ScoreActivity.class);
                    scoreIntent.putExtra("score", score);
                    scoreIntent.putExtra("total", questionList.size());
                    scoreIntent.putExtra("quizzRef", quizzString);
                    startActivity(scoreIntent);
                    timer.cancel();
                    finish();
                }

            }
        });
    }
    public  void UpdateQuestion(){

        if(positionDansLesQuestions < questionList.size()-1) {

            positionDansLesQuestions++;
            newQuestion = (QuestionClass) questionList.get(positionDansLesQuestions);
            textViewChoiceA.setText(newQuestion.getChoiceA());
            textViewChoiceB.setText(newQuestion.getChoiceB());
            textViewQuestion.setText(newQuestion.getmQuestion());
            timer.cancel();
            timer.start();


        }
        else{
            timer.cancel();
            Intent scoreIntent = new Intent(PlayQuizzActivity.this, ScoreActivity.class);
            scoreIntent.putExtra("score", score);
            scoreIntent.putExtra("total", questionList.size());
            scoreIntent.putExtra("quizzRef", quizzString);
            startActivity(scoreIntent);
            finish();
        }


    }

}







