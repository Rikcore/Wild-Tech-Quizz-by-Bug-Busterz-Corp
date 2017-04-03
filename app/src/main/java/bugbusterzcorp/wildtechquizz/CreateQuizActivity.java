package bugbusterzcorp.wildtechquizz;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class CreateQuizActivity extends AppCompatActivity {





    String mCorrectAnswer;
    RadioButton radioButtonFirstChoice;
    RadioButton radioButtonSecondChoice;
    //FloatingActionButton floatingActionButtonAddQuestion;
    String mQuestion;
    String mChoiceA;
    String mChoiceB;
    Button validate;
    ArrayList questionList;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);



        final FloatingActionButton floatingActionButtonAddQuestion = (FloatingActionButton) findViewById(R.id.floatingActionButtonAddQuestion);
        final Button validate =  (Button) findViewById(R.id.validate);
        validate.setVisibility(View.INVISIBLE);
        final ArrayList<QuestionClass> questionList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String username = user.getDisplayName();

        final EditText editTextQuizzName = (EditText) findViewById(R.id.editTextQuizzName);
        editTextQuizzName.setVisibility(View.INVISIBLE);



      floatingActionButtonAddQuestion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              EditText editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
              EditText editTextFirstChoice = (EditText) findViewById(R.id.editTextFirstChoice);
              EditText editTextSecondChoice = (EditText) findViewById(R.id.editTextSecondChoice);
              RadioButton radioButtonFirstChoice = (RadioButton) findViewById(R.id.radioButtonFirstChoice);
              RadioButton radioButtonSecondChoice = (RadioButton) findViewById(R.id.radioButtonSecondChoice);



              if(editTextQuestion.length() > 0 && editTextFirstChoice.length() > 0 && editTextSecondChoice.length() > 0 && (radioButtonFirstChoice.isChecked() || radioButtonSecondChoice.isChecked())) {

                  mQuestion = editTextQuestion.getText().toString();
                  mChoiceA = editTextFirstChoice.getText().toString();
                  mChoiceB = editTextSecondChoice.getText().toString();

                  if(radioButtonFirstChoice.isChecked()){
                      mCorrectAnswer = mChoiceA;

                  }

                  else if(radioButtonSecondChoice.isChecked()){
                      mCorrectAnswer = mChoiceB;

                  }


                  QuestionClass newQuestion = new QuestionClass(mQuestion, mChoiceA, mChoiceB, mCorrectAnswer);
                  Toast.makeText(CreateQuizActivity.this,"Quesion enregistrée !",Toast.LENGTH_LONG).show();


                  questionList.add(newQuestion);
                  editTextQuestion.setText("");
                  editTextFirstChoice.setText("");
                  editTextSecondChoice.setText("");
                  radioButtonFirstChoice.setChecked(false);
                  radioButtonSecondChoice.setChecked(false);




                  if (questionList.size() == 2){
                      floatingActionButtonAddQuestion.setVisibility(View.INVISIBLE);
                      validate.setVisibility(View.VISIBLE);
                      editTextQuizzName.setVisibility(View.VISIBLE);
                      editTextQuestion.setVisibility(View.INVISIBLE);
                      editTextFirstChoice.setVisibility(View.INVISIBLE);
                      editTextSecondChoice.setVisibility(View.INVISIBLE);
                      radioButtonFirstChoice.setVisibility(View.INVISIBLE);
                      radioButtonSecondChoice.setVisibility(View.INVISIBLE);

                      Toast.makeText(CreateQuizActivity.this,"Trouve un titre maintenant !",Toast.LENGTH_LONG).show();
                      return;
                  }




          }
          else{
                  Toast.makeText(CreateQuizActivity.this,"Tu dois remplir tous les champs, et sélectionner la bonne réponse",Toast.LENGTH_LONG).show();
                  return;
              }
      }




    });


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(CreateQuizActivity.this);
                progressDialog.setMessage("Enregistrement de ton quizz");
                progressDialog.show();*/

                String quizzName = editTextQuizzName.getText().toString();
                QuizClass newQuiz = new QuizClass(questionList, username, quizzName);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Quizz");
                ref.push().setValue(newQuiz);
                editTextQuizzName.setText("");

                Toast.makeText(CreateQuizActivity.this,"Quizz envoyé",Toast.LENGTH_LONG).show();

                //progressDialog.dismiss();
                finish();


            }
        });

    }




}
