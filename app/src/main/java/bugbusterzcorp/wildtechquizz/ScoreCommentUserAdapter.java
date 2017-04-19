package bugbusterzcorp.wildtechquizz;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static bugbusterzcorp.wildtechquizz.CreateQuizActivity.TOTAL_QUESTION;

/**
 * Created by apprenti on 11/04/17.
 */

public class ScoreCommentUserAdapter extends FirebaseListAdapter<ScoreCommentUserClass> {
    public ScoreCommentUserAdapter(Query ref, Activity activity, int layout) {
        super(ref, ScoreCommentUserClass.class, layout, activity);


    }


    @Override
    protected void populateView(final View v, ScoreCommentUserClass info) {

        TextView quizzPseudo = (TextView) v.findViewById(R.id.textViewPseudo);
        TextView quizzMessage = (TextView) v.findViewById(R.id.textViewMessage);
        TextView quizzNote = (TextView) v.findViewById(R.id.textViewNote);
        final CircleImageView profileImage = (CircleImageView) v.findViewById(R.id.quizzImage);

        quizzPseudo.setText(String.valueOf(info.getmUserNickName()));
        quizzMessage.setText(String.valueOf(info.getmMessage()));
        quizzNote.setText("Score : " + String.valueOf(info.getmScore()) + "/" + TOTAL_QUESTION);

        StorageReference mStorage;
        mStorage = FirebaseStorage.getInstance().getReference();
        mStorage.child("images/" + info.getmUserID()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {


            @Override
            public void onSuccess(Uri uri) {
                Picasso
                        .with(v.getContext())
                        .load(uri)
                        .resize(700, 700)
                        .centerCrop()
                        .into(profileImage);
            }
        });


    }

}
