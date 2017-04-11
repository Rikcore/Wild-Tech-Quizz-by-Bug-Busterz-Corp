package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
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

import static bugbusterzcorp.wildtechquizz.R.layout.activity_score;



public class ScoreActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_score);


        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");

        GifTextView gifTextViewResult = (GifTextView) findViewById(R.id.gifTextViewResult);
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        TextView textViewResult2= (TextView) findViewById(R.id.textViewResult2);
        final EditText editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        Button buttonMessage = (Button) findViewById(R.id.buttonMessage);
        textViewResult.setTypeface(game_font);
        textViewResult2.setTypeface(game_font);


        Intent scoreIntent = getIntent();
        final int score = scoreIntent.getIntExtra("score", 0);
        int total = scoreIntent.getIntExtra("total", 0);
        final String quizzString = scoreIntent.getStringExtra("quizzRef");



        if (score < 2) {
            gifTextViewResult.setBackgroundResource(R.drawable.zombie);
            textViewResult.setText(score+"/"+total+" So bad");
        } else if (score <= 4) {
            gifTextViewResult.setBackgroundResource(R.drawable.lucky);
            textViewResult.setText(score+"/"+total+" Not bad !");
        } else {

            gifTextViewResult.setBackgroundResource(R.drawable.victoire);

            textViewResult2.setText(score+"/"+total+" Beau gosse !");
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
                    commentScoreRef.child(quizzString).child(user.getDisplayName()).push().setValue(scoreCommentUserClass);
                    startActivity(new Intent(ScoreActivity.this, ProfileActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(ScoreActivity.this,"Laisse nous un petit commentaire, connard!",Toast.LENGTH_LONG).show();

                }
            }
        });




        }


    }

