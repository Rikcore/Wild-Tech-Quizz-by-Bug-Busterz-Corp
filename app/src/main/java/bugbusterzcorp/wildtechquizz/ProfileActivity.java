package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.R.attr.data;
import static android.R.attr.name;
import static bugbusterzcorp.wildtechquizz.R.id.imageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference refUser;
    private StorageReference UserImageRef;
    private FirebaseUser user;


    private TextView textViewUsername;
    private TextView textview2;
    private ImageView imageViewUser;
    private Button buttonLogout;
    private Button buttonPlay;
    private Button buttonUpload;

    private static int RESULT_LOAD_IMAGE = 1;
    private String picturePath;
    private Uri selectedImage;


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
        textview2 = (TextView) findViewById(R.id.textView2);
        imageViewUser = (ImageView) findViewById(R.id.imageViewUser);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);



        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

                String providerId = user.getProviderId();
                String uid = user.getUid();
                String username = user.getDisplayName();
                String email = user.getEmail();

            for (UserInfo userInfo : user.getProviderData()) {
                if (username == null && userInfo.getDisplayName() != null) {
                    username = userInfo.getDisplayName();
                }

            }

                textViewUsername.setText(email);
                textview2.setText(username);




        }

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        imageViewUser.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            imageViewUser.setImageURI(selectedImage);


        }
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
        else if (view == imageViewUser){
            final Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
        else if (view == buttonUpload){
            UserImageRef = FirebaseStorage.getInstance().getReference();
            UserImageRef = UserImageRef.child(user.getUid());
            UserImageRef.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });

        }
    }


    public void goToCreate(View view){

        startActivity(new Intent(this, CreateQuizActivity.class));


    }




}
