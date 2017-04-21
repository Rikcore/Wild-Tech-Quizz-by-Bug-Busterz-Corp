package bugbusterzcorp.wildtechquizz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import android.text.Html;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String RIKCORE ="jCcNcWkOv4c4m4xV4yWWaAT7AAS2";
    public final static String ASLAN = "TqXppWpupGNC0tyMPldr58XmVZ33";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private TextView textViewSignin;
    private TextView textViewTitre;
    private TextView textViewCreation;
    private Button buttonSignup;
    private CheckBox checkBoxCgu;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewCgu;
    String username;
    Spanned Text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");

        textViewCgu = (TextView)findViewById(R.id.textViewCgu);
        textViewCgu.setMovementMethod(LinkMovementMethod.getInstance());


        textViewTitre = (TextView) findViewById(R.id.textViewTitre);
        textViewTitre.setTypeface(game_font);

        textViewCreation = (TextView) findViewById(R.id.textViewCreation);
        textViewCreation.setTextSize(15);
        textViewCreation.setTypeface(game_font);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        textViewSignin.setTypeface(game_font);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        checkBoxCgu = (CheckBox) findViewById(R.id.checkBoxCgu);
        progressDialog = new ProgressDialog(this);

        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser(){

        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        username = editTextUsername.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.fournirEmailAdress,Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, R.string.renseigneMDP,Toast.LENGTH_LONG).show();
            return;
        }

        if(checkBoxCgu.isChecked()==false){
            Toast.makeText(this, R.string.checkboxWarning,Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage(getString(R.string.envoieDonnées));
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            //LORS DE LA CREATION DU COMPTE, JE L'UPDATE EN Y AJOUTANT UN PSEUDO
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                     .setDisplayName(username)
                                    .build();

                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                    }
                                }
                            });


                        }else{

                            Toast.makeText(MainActivity.this, R.string.Error,Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){

            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
