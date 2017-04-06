package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WildTestQuizzActivity extends AppCompatActivity implements View.OnClickListener {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private TextView textViewTimer;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonQuit;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private CountDownTimer timer;
    private  CountDownTimer timer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_test_quizz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        textViewTimer = (TextView)findViewById(R.id.textViewTimer);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice1.setOnClickListener(this);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice2.setOnClickListener(this);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice3.setOnClickListener(this);
        mButtonQuit = (Button)findViewById(R.id.Finish);


       timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(" " + millisUntilFinished / 1000);
                textViewTimer.setTextColor(getResources().getColor(R.color.red));
            }

        public void onFinish() {
            updateQuestion();
        }
  }.start();

        timer2  = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer.setTextColor(getResources().getColor(R.color.green));
            }

            public void onFinish() {
                cancel();

            }
        }.start();







        //updateQuestion();

        //Start Of Button Listener for Button1

/*
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS TRY THIS

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    mButtonChoice1.setBackgroundColor(Color.GREEN);
                    updateQuestion();
                  //  mButtonChoice1.setBackgroundColor(getResources().getColor(R.color.blue));


                    //Toast
                    Toast.makeText(WildTestQuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(WildTestQuizzActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();

                }

            }
        });

        //End of Button 1 Listener

        //Start Of Button Listener for Button2


        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS TRY THIS

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);

                    updateQuestion();
                   // mButtonChoice2.setBackgroundColor(getResources().getColor(R.color.blue));


                    // BACK_GROUND_COLOR

                    mButtonChoice2.setBackgroundColor(Color.GREEN);



                    //Toast
                    Toast.makeText(WildTestQuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(WildTestQuizzActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();

                }

            }
        });

        //End of Button 2 Listener

        //Start Of Button Listener for Button3


        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS TRY THIS

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    mButtonChoice3.setBackgroundColor(getResources().getColor(R.color.blue));

                    //Toast
                    Toast.makeText(WildTestQuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(WildTestQuizzActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();

                }

            }
        });*/

        //End of Button 3 Listener

    }

    private void updateScore(int point){
        mScoreView.setText("" +mScore);
    }
    private void updateQuestion(){
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswers(mQuestionNumber);
        if (mQuestionNumber < mQuestionLibrary.getQuestionNumber()-1) {
            mQuestionNumber++;

            timer.start();
            timer2.start();






          /*  mButtonChoice1.setBackgroundColor(Color.BLUE);
            mButtonChoice2.setBackgroundColor(Color.BLUE);
            mButtonChoice3.setBackgroundColor(Color.BLUE); */
        }
        else{
            Intent monProfil = new Intent(WildTestQuizzActivity.this, ProfileActivity.class);
            startActivity(monProfil);
            finish();
        }


        mButtonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void timerColor(boolean bol, final Button button){
        if(bol) {
            new CountDownTimer(200, 20) {
                public void onTick(long millisUntilFinished) {
                    button.setBackgroundColor(Color.GREEN);
                }

                public void onFinish() {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    button.setBackgroundColor(getResources().getColor(R.color.blue));



                }
            }.start();
        }
        else{
            new CountDownTimer(200, 20) {
                public void onTick(long millisUntilFinished) {
                    button.setBackgroundColor(Color.RED);
                }

                public void onFinish() {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    button.setBackgroundColor(getResources().getColor(R.color.blue));


                }
            }.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choice1:
            case R.id.choice2:
            case R.id.choice3:
                Button button = (Button) findViewById(v.getId());
                timerColor(button.getText() == mAnswer, button);

        }
    }
}


