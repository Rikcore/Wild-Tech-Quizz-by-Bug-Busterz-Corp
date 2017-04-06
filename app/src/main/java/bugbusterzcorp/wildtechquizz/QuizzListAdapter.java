package bugbusterzcorp.wildtechquizz;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;



public class QuizzListAdapter extends FirebaseListAdapter<Quizzclass> {


    public QuizzListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Quizzclass.class, layout, activity);

    }

    @Override
    protected void populateView(View v, Quizzclass newQuiz) {
        TextView quizzName = (TextView)v.findViewById(R.id.textViewQuizzName);
        TextView quizzAutor = (TextView)v.findViewById(R.id.textViewAuthor);

        quizzName.setText(String.valueOf(newQuiz.getQuizzName()));
        quizzAutor.setText(String.valueOf("Par "+newQuiz.getUsername()));
    }
}



