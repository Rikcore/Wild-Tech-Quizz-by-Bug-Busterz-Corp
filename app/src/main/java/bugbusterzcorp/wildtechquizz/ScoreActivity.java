package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifTextView;

import static bugbusterzcorp.wildtechquizz.R.layout.activity_score;



public class ScoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_score);


        GifTextView gifTextViewResult = (GifTextView) findViewById(R.id.gifTextViewResult);
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        SoundPlayer sound;

        Intent scoreIntent = getIntent();
        int score = scoreIntent.getIntExtra("score", 0);
        int total = scoreIntent.getIntExtra("total", 0);

        sound = new SoundPlayer(this);

        sound.playBoogieSound();

        if (score < 2) {
            gifTextViewResult.setBackgroundResource(R.drawable.zombie);
            textViewResult.setText(score+"/"+total+" So bad");
        } else if (score <= 4) {
            gifTextViewResult.setBackgroundResource(R.drawable.lucky);
            textViewResult.setText(score+"/"+total+" Not bad !");
        } else {

            gifTextViewResult.setBackgroundResource(R.drawable.victoire);

            textViewResult.setText(score+"/"+total+" Beau gosse !");

        }


    }

}
