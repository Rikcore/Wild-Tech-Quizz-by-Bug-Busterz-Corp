package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizzDetailActivity extends AppCompatActivity {
    TextView textViewTitre;
    ListView InfoQuizzListview;
    TextView textViewEmptyList;
    Button buttonGoPlay;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_detail);

        Intent intent = getIntent();
        final String quizzRef = intent.getStringExtra("quizzRef");
        final Quizzclass newQuiz = (Quizzclass) intent.getExtras().get("quizzObject");
        textViewTitre = (TextView) findViewById(R.id.textViewTitre);
        textViewEmptyList = (TextView) findViewById(R.id.textViewEmptyList);
        InfoQuizzListview = (ListView) findViewById(R.id.InfoQuizzListview);
        buttonGoPlay = (Button)findViewById(R.id.buttonGoPlay);
        mDatabase = FirebaseDatabase.getInstance().getReference("Quizz")
                .child(intent.getStringExtra("quizzRef")).child("comments");

        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");

        textViewTitre.setTypeface(game_font);
        textViewTitre.setText(newQuiz.getQuizzName());

        textViewEmptyList.setTypeface(game_font);



        // FirebaseDatabase.child(quizzString).child(user.getDisplayName()).push().setValue(scoreCommentUserClass);


        final ScoreCommentUserAdapter mScoreCommentUserAdapter = new ScoreCommentUserAdapter(mDatabase, this, R.layout.infoquizz ); // APPELLE L'ADAPTER
        final ListView InfoQuizzListview = (ListView) findViewById(R.id.InfoQuizzListview); //APPELLE LA LISTE .XML
        InfoQuizzListview.setAdapter(mScoreCommentUserAdapter); //FUSION LIST ET ADAPTER
        InfoQuizzListview.setEmptyView(findViewById(R.id.textViewEmptyList));

        mScoreCommentUserAdapter.registerDataSetObserver(new DataSetObserver() {
             @Override
             public void onChanged() {
                 super.onChanged();
                 InfoQuizzListview.setSelection(mScoreCommentUserAdapter.getCount() - 1);
             }
         });





        buttonGoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Quizzclass newQuiz = (Quizzclass) InfoQuizzListview.getAdapter().getItem(position);
                Toast.makeText(QuizzDetailActivity.this,"Bonne chance",Toast.LENGTH_LONG).show();
                Intent goPlay = new Intent(QuizzDetailActivity.this, PlayQuizzActivity.class);

                goPlay.putExtra("quizzRef", quizzRef);
                goPlay.putExtra("quizzObject", newQuiz);
                startActivity(goPlay);


            }
        });


    }
}
