package bugbusterzcorp.wildtechquizz;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;



public class QuizzListAdapter extends FirebaseListAdapter<QuizClass> {


    public QuizzListAdapter(Query ref, Activity activity, int layout) {
        super(ref, QuizClass.class, layout, activity);

    }

    @Override
    protected void populateView(View v, QuizClass newQuiz) {
        TextView quizzName = (TextView)v.findViewById(R.id.textViewQuizzName);
        TextView quizzAutor = (TextView)v.findViewById(R.id.textViewAuthor);

        quizzName.setText(String.valueOf(newQuiz.getQuizzName()));
        quizzAutor.setText(String.valueOf(newQuiz.getUsername()));
    }
}



