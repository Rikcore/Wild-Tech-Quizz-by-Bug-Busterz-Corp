package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.content.pm.ActivityInfo;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CreateQuizActivity extends AppCompatActivity {

    String mCorrectAnswer;
    String mQuestion;
    String mChoiceA;
    String mChoiceB;
    private ImageView imageViewPhoto;
    private FirebaseAuth firebaseAuth;
    private String quizzName;
    private String uid;
    private StorageReference mStorage;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap correctImage;
    private Uri selectedImageQuizz;
    private FirebaseUser user;
    private String picturePath;
    private static String urlPhotoQuizz;
    final static int TOTAL_QUESTION = 10;
    int count = 1;
    int size = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final ArrayList<QuestionClass> questionList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String username = user.getDisplayName();
        imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
        final TextView textViewQuizzEtape = (TextView) findViewById(R.id.textViewQuizzEtape);
        textViewQuizzEtape.setTextSize(25);
        final EditText editTextQuizzName = (EditText) findViewById(R.id.editTextQuizzName);
        final Button buttonContinue = (Button) findViewById(R.id.buttonContinue);
        mStorage = FirebaseStorage.getInstance().getReference();
        final FloatingActionButton floatingActionButtonAddQuestion = (FloatingActionButton) findViewById(R.id.floatingActionButtonAddQuestion);
        floatingActionButtonAddQuestion.setVisibility(View.INVISIBLE);
        final TextView textViewQuestionCount = (TextView) findViewById(R.id.textViewQuestionCount);
        textViewQuestionCount.setVisibility(View.INVISIBLE);
        final EditText editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        editTextQuestion.setVisibility(View.INVISIBLE);
        final EditText editTextFirstChoice = (EditText) findViewById(R.id.editTextFirstChoice);
        editTextFirstChoice.setVisibility(View.INVISIBLE);
        final EditText editTextSecondChoice = (EditText) findViewById(R.id.editTextSecondChoice);
        editTextSecondChoice.setVisibility(View.INVISIBLE);
        final RadioButton radioButtonFirstChoice = (RadioButton) findViewById(R.id.radioButtonFirstChoice);
        radioButtonFirstChoice.setVisibility(View.INVISIBLE);
        final RadioButton radioButtonSecondChoice = (RadioButton) findViewById(R.id.radioButtonSecondChoice);
        radioButtonSecondChoice.setVisibility(View.INVISIBLE);
        final RadioGroup radiogroupAnswer = (RadioGroup) findViewById(R.id.radioGroupAnswer);
        final ImageView bufferButton = (ImageView) findViewById(R.id.imageViewBuffer);
        bufferButton.setVisibility(View.INVISIBLE);

        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");
        textViewQuizzEtape.setTypeface(game_font);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String uid = user.getUid();

            imageViewPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

            buttonContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextQuizzName.getText().toString().length() != 0 && selectedImageQuizz != null) {
                        quizzName = editTextQuizzName.getText().toString();
                        textViewQuizzEtape.setTextSize(18);
                        textViewQuizzEtape.setText(getString(R.string.CreateQuizz)+" "+TOTAL_QUESTION+" "+getString(R.string.questionsDuQuizz));
                        textViewQuestionCount.setTextSize(15);
                        editTextQuizzName.setVisibility(View.INVISIBLE);
                        buttonContinue.setVisibility(View.INVISIBLE);
                        imageViewPhoto.setVisibility(View.INVISIBLE);
                        editTextQuestion.setVisibility(View.VISIBLE);
                        editTextFirstChoice.setVisibility(View.VISIBLE);
                        editTextSecondChoice.setVisibility(View.VISIBLE);
                        radioButtonFirstChoice.setVisibility(View.VISIBLE);
                        radioButtonSecondChoice.setVisibility(View.VISIBLE);
                        floatingActionButtonAddQuestion.setVisibility(View.VISIBLE);
                        textViewQuestionCount.setVisibility(View.VISIBLE);
                        textViewQuestionCount.setText(getString(R.string.compteurzero)+TOTAL_QUESTION);
                        textViewQuestionCount.setTextSize(size);

                        Toast.makeText(CreateQuizActivity.this, R.string.placeAuxQuestions, Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        Toast.makeText(CreateQuizActivity.this, R.string.QuizzNameImage, Toast.LENGTH_LONG).show();
                    }
                }
            });

            floatingActionButtonAddQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (editTextQuestion.length() > 0 && editTextFirstChoice.length() > 0 && editTextSecondChoice.length() > 0 && (radioButtonFirstChoice.isChecked() || radioButtonSecondChoice.isChecked())) {
                        mQuestion = editTextQuestion.getText().toString();
                        mChoiceA = editTextFirstChoice.getText().toString();
                        mChoiceB = editTextSecondChoice.getText().toString();

                        if (radioButtonFirstChoice.isChecked()) {
                            mCorrectAnswer = mChoiceA;

                        } else if (radioButtonSecondChoice.isChecked()) {
                            mCorrectAnswer = mChoiceB;
                        }

                        QuestionClass newQuestion = new QuestionClass(mQuestion, mChoiceA, mChoiceB, mCorrectAnswer);
                        Toast.makeText(CreateQuizActivity.this, R.string.QuestionEnregistree, Toast.LENGTH_LONG).show();
                        questionList.add(newQuestion);
                        editTextQuestion.setText("");
                        editTextFirstChoice.setText("");
                        editTextSecondChoice.setText("");
                        radiogroupAnswer.clearCheck();
                        textViewQuestionCount.setText(count+"/"+TOTAL_QUESTION);
                        textViewQuestionCount.setTextSize(size);
                        count++;
                        size++;

                        if (questionList.size() == TOTAL_QUESTION) {
                            floatingActionButtonAddQuestion.setVisibility(View.INVISIBLE);
                            bufferButton.setVisibility(View.VISIBLE);
                            textViewQuestionCount.setTextSize(30);
                            textViewQuizzEtape.setText(R.string.BuzzerSend);
                            editTextQuestion.setVisibility(View.INVISIBLE);
                            editTextFirstChoice.setVisibility(View.INVISIBLE);
                            editTextSecondChoice.setVisibility(View.INVISIBLE);
                            radioButtonFirstChoice.setVisibility(View.INVISIBLE);
                            radioButtonSecondChoice.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Toast.makeText(CreateQuizActivity.this, R.string.ConditionCreationQuizz, Toast.LENGTH_LONG).show();
                        return;
                    }
                }


            });

            bufferButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStorage = mStorage.child("imagesQuizz/"+quizzName+uid);
                    mStorage.putFile(selectedImageQuizz)

                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    urlPhotoQuizz = downloadUrl.toString();
                                    Quizzclass newQuiz = new Quizzclass(questionList, username, quizzName, uid, urlPhotoQuizz);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference ref = database.getReference("Quizz");
                                    ref.push().setValue(newQuiz);
                                    editTextQuizzName.setText("");

                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    @SuppressWarnings("VisibleForTests") double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                }
                            });
                    Toast.makeText(CreateQuizActivity.this, R.string.QuizzCréeEnvoyé, Toast.LENGTH_LONG).show();
                    finish();

                }
            });

            bufferButton.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            view.getDrawable().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
        }
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImageQuizz = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageQuizz, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            Picasso
                    .with(CreateQuizActivity.this)
                    .load(selectedImageQuizz)
                    .resize(700, 700)
                    .centerCrop()
                    .into(imageViewPhoto);
        }
    }
}





