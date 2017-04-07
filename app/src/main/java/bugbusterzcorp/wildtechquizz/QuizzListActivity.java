package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizzListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_list);
        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");




        mDatabase = FirebaseDatabase.getInstance().getReference("Quizz"); // APPELLE LA BASE DE DONNEES

        final QuizzListAdapter mQuizzListAdapter = new QuizzListAdapter(mDatabase, this, R.layout.quizz_item ); // APPELLE L'ADAPTER
        final TextView textViewChoix = (TextView) findViewById(R.id.textViewChoix);
        textViewChoix.setTypeface(game_font);
        final ListView quizzListView = (ListView) findViewById(R.id.QuizzLIstView); //APPELLE LA LISTE .XML
        quizzListView.setAdapter(mQuizzListAdapter); //FUSION LIST ET ADAPTER


        mQuizzListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                quizzListView.setSelection(mQuizzListAdapter.getCount() - 1);
            }
        });


        quizzListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Quizzclass newQuiz = (Quizzclass) quizzListView.getAdapter().getItem(position);

                String quizzRef = mQuizzListAdapter.getItemKey(position);
                Toast.makeText(QuizzListActivity.this,"Bonne chance",Toast.LENGTH_LONG).show();
                Intent goPlay = new Intent(QuizzListActivity.this, PlayQuizzActivity.class);

                goPlay.putExtra("quizzRef", quizzRef);
                goPlay.putExtra("quizzObject", newQuiz);
                startActivity(goPlay);
            }
        });
    }
}
