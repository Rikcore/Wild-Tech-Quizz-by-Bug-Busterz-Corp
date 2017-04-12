package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.constraint.solver.SolverVariable;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class CreateQuizActivity extends AppCompatActivity {


    String mCorrectAnswer;
    String mQuestion;
    String mChoiceA;
    String mChoiceB;
    RadioGroup radioGroupAnswer;
    ArrayList questionList;
    private FirebaseAuth firebaseAuth;
    private String quizzName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        final ArrayList<QuestionClass> questionList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String username = user.getDisplayName();

        final TextView textViewQuizzName = (TextView) findViewById(R.id.textViewQuizzNameChoice);
        final EditText editTextQuizzName = (EditText) findViewById(R.id.editTextQuizzName);
        final Button buttonContinue = (Button) findViewById(R.id.buttonContinue);




        final FloatingActionButton floatingActionButtonAddQuestion = (FloatingActionButton) findViewById(R.id.floatingActionButtonAddQuestion);
        floatingActionButtonAddQuestion.setVisibility(View.INVISIBLE);
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

        final TextView textViewEnd = (TextView) findViewById(R.id.textViewEnd);
        textViewEnd.setVisibility(View.INVISIBLE);
        final ImageView bufferButton = (ImageView) findViewById(R.id.imageViewBuffer);
        bufferButton.setVisibility(View.INVISIBLE);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/bits.TTF");
        Typeface geek_font = Typeface.createFromAsset(getAssets(), "fonts/digiffiti.ttf");
        Typeface game_font = Typeface.createFromAsset(getAssets(), "fonts/Gamegirl.ttf");


        textViewQuizzName.setTypeface(game_font);
        textViewEnd.setTypeface(game_font);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String providerId = user.getProviderId();
            final String uid = user.getUid();


            buttonContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextQuizzName == null) {
                        Toast.makeText(CreateQuizActivity.this, "Ton quizz a besoin d'un nom", Toast.LENGTH_LONG).show();
                    } else {
                        quizzName = editTextQuizzName.getText().toString();
                        textViewQuizzName.setVisibility(View.INVISIBLE);
                        editTextQuizzName.setVisibility(View.INVISIBLE);
                        buttonContinue.setVisibility(View.INVISIBLE);
                        editTextQuestion.setVisibility(View.VISIBLE);
                        editTextFirstChoice.setVisibility(View.VISIBLE);
                        editTextSecondChoice.setVisibility(View.VISIBLE);
                        radioButtonFirstChoice.setVisibility(View.VISIBLE);
                        radioButtonSecondChoice.setVisibility(View.VISIBLE);
                        floatingActionButtonAddQuestion.setVisibility(View.VISIBLE);
                        Toast.makeText(CreateQuizActivity.this, "Titre OK, place aux questions !", Toast.LENGTH_LONG).show();


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
                        Toast.makeText(CreateQuizActivity.this, "Quesion enregistrée !", Toast.LENGTH_LONG).show();


                        questionList.add(newQuestion);
                        editTextQuestion.setText("");
                        editTextFirstChoice.setText("");
                        editTextSecondChoice.setText("");
                        radiogroupAnswer.clearCheck();



                        if (questionList.size() == 5) {
                            floatingActionButtonAddQuestion.setVisibility(View.INVISIBLE);
                            bufferButton.setVisibility(View.VISIBLE);
                            textViewEnd.setVisibility(View.VISIBLE);
                            editTextQuestion.setVisibility(View.INVISIBLE);
                            editTextFirstChoice.setVisibility(View.INVISIBLE);
                            editTextSecondChoice.setVisibility(View.INVISIBLE);
                            radioButtonFirstChoice.setVisibility(View.INVISIBLE);
                            radioButtonSecondChoice.setVisibility(View.INVISIBLE);


                        }


                    } else {
                        Toast.makeText(CreateQuizActivity.this, "Tu dois remplir tous les champs, et sélectionner la bonne réponse", Toast.LENGTH_LONG).show();
                        return;
                    }
                }


            });


            bufferButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Quizzclass newQuiz = new Quizzclass(questionList, username, quizzName, uid);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Quizz");
                    ref.push().setValue(newQuiz);
                    editTextQuizzName.setText("");


                    Toast.makeText(CreateQuizActivity.this, "Quizz envoyé", Toast.LENGTH_LONG).show();


                    finish();


                }
            });


            bufferButton.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            //overlay is black with transparency of 0x77 (119)
                            view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            //clear the overlay
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

}
