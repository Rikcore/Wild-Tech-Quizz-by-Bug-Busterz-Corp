package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class PlayQuizzActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private String quizzString;
    private ArrayList questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quizz);
        Intent goPlay = getIntent();
        quizzString = goPlay.getStringExtra("quizzRef");
        QuizClass newQuiz = (QuizClass) goPlay.getExtras().get("quizzObject");
        questionList = newQuiz.getQuestionList();









    }
}
