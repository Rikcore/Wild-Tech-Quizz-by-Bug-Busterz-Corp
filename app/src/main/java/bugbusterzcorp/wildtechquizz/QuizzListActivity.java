package bugbusterzcorp.wildtechquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizzListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");



        // APPELLE LA BASE DE DONNEES
        mDatabase = FirebaseDatabase.getInstance().getReference("Quizz");







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
                Intent goDetails = new Intent(QuizzListActivity.this, QuizzDetailActivity.class);

                goDetails.putExtra("quizzRef", quizzRef);
                goDetails.putExtra("quizzObject", newQuiz);
                startActivity(goDetails);
            }
        });

        quizzListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int currentPosition = position;

                new AlertDialog.Builder(QuizzListActivity.this)
                        .setTitle("Supprimer ce quizz ?")
                        .setMessage("Etes vous vraiment sûrs de vouloir supprimer ce quizz ?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                Quizzclass newQuiz = (Quizzclass) quizzListView.getAdapter().getItem(currentPosition);
                                if(user.getUid().equals(newQuiz.getmCreatorId()) || user.getUid().equals("jCcNcWkOv4c4m4xV4yWWaAT7AAS2")) {
                                    mDatabase.child(mQuizzListAdapter.getItemKey(currentPosition)).removeValue();
                                }
                                else{
                                    Toast.makeText(QuizzListActivity.this,"C'est pas beau de vouloir supprimer les quizzs des copains !",Toast.LENGTH_LONG).show();
                                }
                            }
                        })

                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return (true);
            }
        });


    }
}
