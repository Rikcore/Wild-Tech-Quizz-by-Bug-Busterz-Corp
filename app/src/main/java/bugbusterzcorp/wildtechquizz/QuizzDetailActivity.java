package bugbusterzcorp.wildtechquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class QuizzDetailActivity extends AppCompatActivity {
    TextView textViewTitre;
    TextView textViewMonScore;
    ListView InfoQuizzListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_detail);
        textViewTitre = (TextView) findViewById(R.id.textViewTitre);
        textViewMonScore = (TextView) findViewById(R.id.textViewMonScore);
        InfoQuizzListview = (ListView) findViewById(R.id.InfoQuizzListview);



    }
}
