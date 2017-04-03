package bugbusterzcorp.wildtechquizz;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizzListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_list);



        mDatabase = FirebaseDatabase.getInstance().getReference("Quizz"); // APPELLE LA BASE DE DONNEES

        final QuizzListAdapter mQuizzListAdapter = new QuizzListAdapter(mDatabase, this, R.layout.quizz_item ); // APPELLE L'ADAPTER

        final ListView quizzListView = (ListView) findViewById(R.id.QuizzLIstView); //APPELLE LA LISTE .XML
        quizzListView.setAdapter(mQuizzListAdapter); //FUSION LIST ET ADAPTER


        mQuizzListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                quizzListView.setSelection(mQuizzListAdapter.getCount() - 1);
            }
        });
    }
}
