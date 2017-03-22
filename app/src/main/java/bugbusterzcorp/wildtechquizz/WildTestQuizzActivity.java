package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WildTestQuizzActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonQuit;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_test_quizz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonQuit = (Button)findViewById(R.id.Finish);

        updateQuestion();

        //Start Of Button Listener for Button1


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS TRY THIS

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

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

                    //Toast
                    Toast.makeText(WildTestQuizzActivity.this, "Correct", Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(WildTestQuizzActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();

                }

            }
        });

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
        }
        else{
            Intent monProfil = new Intent(WildTestQuizzActivity.this, ProfileActivity.class);
            startActivity(monProfil);
        }


        mButtonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
