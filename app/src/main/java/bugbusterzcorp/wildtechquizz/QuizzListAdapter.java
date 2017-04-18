package bugbusterzcorp.wildtechquizz;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuizzListAdapter extends FirebaseListAdapter<Quizzclass> {
    public QuizzListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Quizzclass.class, layout, activity);

    }

    @Override
    protected void populateView(final View v, Quizzclass newQuiz) {


        TextView quizzName = (TextView)v.findViewById(R.id.textViewQuizzName);
        TextView quizzAutor = (TextView)v.findViewById(R.id.textViewAuthor);
        final CircleImageView quizzImageView = (CircleImageView)v.findViewById(R.id.quizzImage);
        quizzName.setText(String.valueOf(newQuiz.getQuizzName()));
        quizzAutor.setText((R.string.parAuteur)+newQuiz.getUsername());

        StorageReference mStorage;
        mStorage = FirebaseStorage.getInstance().getReference();

        mStorage.child("imagesQuizz/"+newQuiz.getQuizzName()+newQuiz.getmCreatorId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {


            @Override
            public void onSuccess(Uri uri) {
                Picasso
                        .with(v.getContext())
                        .load(uri)
                        .resize(700, 700)
                        .centerCrop()
                        .into(quizzImageView);
            }
        });
    }

}


