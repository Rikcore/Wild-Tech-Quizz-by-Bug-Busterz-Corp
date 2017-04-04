package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PlayQuizzActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private String quizzRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quizz);
        Intent goPlay = getIntent();
        quizzRef = goPlay.getStringExtra("quizzRef");
        mRef = FirebaseDatabase.getInstance().getReference("Quizz");





    }
}
