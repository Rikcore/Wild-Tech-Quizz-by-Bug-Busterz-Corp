package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.droidsonroids.gif.GifTextView;

import static bugbusterzcorp.wildtechquizz.CreateQuizActivity.TOTAL_QUESTION;
import static bugbusterzcorp.wildtechquizz.R.layout.activity_score;



public class ScoreActivity extends AppCompatActivity{
    final static double VERY_BAD_SCORE = 0.2;
    final static double BAD_SCORE = 0.4;
    final static double MEDIUM_CORE = 0.7;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");

        GifTextView gifTextViewResult = (GifTextView) findViewById(R.id.gifTextViewResult);
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        TextView textViewResult2= (TextView) findViewById(R.id.textViewResult2);
        final EditText editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        Button buttonMessage = (Button) findViewById(R.id.buttonMessage);
        textViewResult.setTypeface(game_font);
        textViewResult2.setTypeface(game_font);


        Intent scoreIntent = getIntent();
        final int killer = scoreIntent.getIntExtra("killer", 0);
        final int score = scoreIntent.getIntExtra("score", 0);
        final int quizzSize = scoreIntent.getIntExtra("total", 0);
        final float note = (float)score/quizzSize;
        final String quizzString = scoreIntent.getStringExtra("quizzRef");




            if (note <= VERY_BAD_SCORE) {
                gifTextViewResult.setBackgroundResource(R.drawable.alien);
                textViewResult.setTextSize(15);
                textViewResult.setText(score + "/" + quizzSize +" "+ getString(R.string.ScoreNul));
            }
            else if (note <= BAD_SCORE) {
                gifTextViewResult.setBackgroundResource(R.drawable.zombie);
                textViewResult.setText(score+"/"+quizzSize+" "+getString(R.string.scoreMediocre));
            }
            else if (note <= MEDIUM_CORE) {
                gifTextViewResult.setBackgroundResource(R.drawable.chevalier);
                textViewResult.setText(score + "/" + quizzSize + " "+getString(R.string.scoreIntermediaire));
            } else {

                gifTextViewResult.setBackgroundResource(R.drawable.victoire);

                textViewResult2.setText(score + "/" + quizzSize + " "+ getString(R.string.scoreBeauGosse));
                textViewResult2.setTextColor(getResources().getColor(R.color.purple));

            }

        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextMessage != null){
                    String message = editTextMessage.getText().toString().trim();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userName = user.getDisplayName();
                    String userID = user.getUid();

                    ScoreCommentUserClass scoreCommentUserClass = new ScoreCommentUserClass(message,userName, userID ,score);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference commentScoreRef = database.getReference("Quizz");

                    commentScoreRef.child(quizzString).child("comments").push().setValue(scoreCommentUserClass);

                    startActivity(new Intent(ScoreActivity.this, ProfileActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(ScoreActivity.this, R.string.LeaveComment,Toast.LENGTH_LONG).show();
                }
            }
        });

        }

    public void share(View view){
        Intent scoreIntent = getIntent();
        final int score = scoreIntent.getIntExtra("score", 0);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.monScore)+" " + score +"/"+TOTAL_QUESTION+" "+getString(R.string.surwildtechquizz) );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    }

