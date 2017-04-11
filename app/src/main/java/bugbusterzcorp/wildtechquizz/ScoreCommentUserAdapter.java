package bugbusterzcorp.wildtechquizz;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

/**
 * Created by apprenti on 11/04/17.
 */

public class ScoreCommentUserAdapter extends FirebaseListAdapter<ScoreCommentUserClass> {
    public ScoreCommentUserAdapter(Query ref, Activity activity, int layout) {
        super(ref, ScoreCommentUserClass.class, layout, activity);

    }


    @Override
    protected void populateView(View v, ScoreCommentUserClass info) {

        TextView quizzPseudo = (TextView) v.findViewById(R.id.textViewPseudo);
        TextView quizzMessage = (TextView) v.findViewById(R.id.textViewMessage);
        TextView quizzNote = (TextView) v.findViewById(R.id.textViewNote);
       // ImageView quizzAvatar = (ImageView) v.findViewById(R.id.avatar);

        quizzPseudo.setText(String.valueOf(info.getmUserNickName()));
        quizzMessage.setText(String.valueOf(info.getmMessage()));
        quizzNote.setText(String.valueOf(info.getmScore()));
        //quizzAvatar.setImageBitmap(imag);
    }
}


