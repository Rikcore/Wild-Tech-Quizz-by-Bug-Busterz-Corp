package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;

import static android.R.attr.data;
import static android.R.attr.name;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference refUser;

    //view objects
    private TextView textViewUsername;
    private ImageView imageViewUser;
    private Button buttonLogout;
    private Button buttonPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }


        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        imageViewUser = (ImageView) findViewById(R.id.imageViewUser);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {

                String providerId = profile.getProviderId();

                String uid = profile.getUid();

                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri profilePic = profile.getPhotoUrl();


                textViewUsername.setText(name);
                imageViewUser.setImageURI(profilePic);

            };
        }



        //adding listener to button
        buttonLogout.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));

            }
         else if (view == buttonPlay) {
            startActivity(new Intent(this, ThemeActivity.class));
        }
    }




}
