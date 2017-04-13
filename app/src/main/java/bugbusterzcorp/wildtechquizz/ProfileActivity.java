package bugbusterzcorp.wildtechquizz;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;
import static android.R.attr.data;
import static android.R.attr.drawable;
import static android.R.attr.name;
import static android.R.attr.path;
import static bugbusterzcorp.wildtechquizz.R.id.image;
import static bugbusterzcorp.wildtechquizz.R.id.imageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;
    private StorageReference mUserRef;
    private FirebaseUser user;


    private TextView textViewUsername;
    private ImageView imageViewUser;
    private CircleImageView profileImage;
    private TextView textViewResetPassword;
    private TextView textViewFormat;
    private Button buttonLogout;
    private Button buttonPlay;
    private Button buttonUpload;

    private static int RESULT_LOAD_IMAGE = 1;
    private String picturePath;
    private Uri selectedImage;
    private Bitmap correctImage;
    private String uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");


        mStorage = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewResetPassword = (TextView) findViewById(R.id.textViewForgetPassword);
        textViewFormat = (TextView) findViewById(R.id.textViewFormat);
        //imageViewUser = (ImageView) findViewById(R.id.imageViewUser);
        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setVisibility(View.INVISIBLE);



        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

                String providerId = user.getProviderId();
                uid = user.getUid();
                String username = user.getDisplayName();
                String email = user.getEmail();

                textViewUsername.setText(username);
                textViewUsername.setTypeface(game_font);


            mStorage.child("images/"+uid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {




                @Override
                public void onSuccess(Uri uri) {

                    Picasso
                            .with(ProfileActivity.this)
                            .load(uri)
                            .resize(500,500)
                            .centerCrop()
                            .into(profileImage);
                    buttonUpload.setVisibility(View.INVISIBLE);
                    textViewFormat.setVisibility(View.INVISIBLE);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Exception Prout = exception;
                }
            });

        }

        buttonLogout.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        //imageViewUser.setOnClickListener(this);
        profileImage.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String username = user.getDisplayName();
        textViewUsername.setText(username);


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

                Picasso
                        .with(ProfileActivity.this)
                        .load(selectedImage)
                        .resize(700,700)
                        .centerCrop()
                        .into(profileImage);

            buttonUpload.setVisibility(View.VISIBLE);


        }
    }


    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

            }
        else if (view == buttonPlay) {
            startActivity(new Intent(this, QuizzListActivity.class));
        }
        else if (view == profileImage){
            final Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
        else if (view == buttonUpload){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();


            mStorage = mStorage.child("images/"+uid);

            mStorage.putFile(selectedImage)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                            progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });


            buttonUpload.setVisibility(View.INVISIBLE);
            textViewFormat.setVisibility(View.INVISIBLE);


        }

    }

    public void goToCreate(View view){

        startActivity(new Intent(this, CreateQuizActivity.class));

    }






}
