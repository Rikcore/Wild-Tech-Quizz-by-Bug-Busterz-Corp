package bugbusterzcorp.wildtechquizz;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);


        EditText editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        EditText editTextFirstChoice = (EditText) findViewById(R.id.editTextFirstChoice);
        EditText editTextSecondChoice = (EditText) findViewById(R.id.editTextSecondChoice);
        RadioButton radioButtonFirstChoice = (RadioButton) findViewById(R.id.radioButtonFirstChoice);
        RadioButton radioButtonSecondChoice = (RadioButton) findViewById(R.id.radioButtonSecondChoice);
        FloatingActionButton floatingActionButtonAddQuestion = (FloatingActionButton) findViewById(R.id.floatingActionButtonAddQuestion);
        Button validate =  (Button) findViewById(R.id.validate);
        final ArrayList<QuestionClass> questionList = new ArrayList<>();

        mQuestion = editTextQuestion.getText().toString();
        mChoiceA = editTextFirstChoice.getText().toString();
        mChoiceB = editTextSecondChoice.getText().toString();


     validate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             QuizClass newQuiz = new QuizClass(questionList);
             FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference ref = database.getReference("Quizz");
             ref.push().setValue(newQuiz);


         }
     });

      floatingActionButtonAddQuestion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              /*Toast toastSuccess = Toast.makeText(context, textSuccess, durationSuccess);

              CharSequence textFail = "Veuillez remplir tous les champs et cocher la bonne réponse";
              int durationFail = Toast.LENGTH_SHORT;


              Toast toastFail = Toast.makeText(context, textFail, durationFail); */

              EditText editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
              EditText editTextFirstChoice = (EditText) findViewById(R.id.editTextFirstChoice);
              EditText editTextSecondChoice = (EditText) findViewById(R.id.editTextSecondChoice);
              RadioButton radioButtonFirstChoice = (RadioButton) findViewById(R.id.radioButtonFirstChoice);
              RadioButton radioButtonSecondChoice = (RadioButton) findViewById(R.id.radioButtonSecondChoice);

              if(radioButtonFirstChoice.isChecked()){
                  mCorrectAnswer = mChoiceA;
                  //radioButtonSecondChoice.setChecked(false);

              }

              else if(radioButtonSecondChoice.isChecked()){
                  mCorrectAnswer = mChoiceB;
                  //radioButtonFirstChoice.setChecked(false);
              }

              if(editTextQuestion.length() > 0 && editTextFirstChoice.length() > 0 && editTextSecondChoice.length() > 0 ) {

                  mQuestion = editTextQuestion.getText().toString();
                  mChoiceA = editTextFirstChoice.getText().toString();
                  mChoiceB = editTextSecondChoice.getText().toString();

                  QuestionClass newQuestion = new QuestionClass(mQuestion, mChoiceA, mChoiceB, mCorrectAnswer);

                  questionList.add(newQuestion);
                  //toastSuccess.show();
                  editTextQuestion.setText("");
                  editTextFirstChoice.setText("");
                  editTextSecondChoice.setText("");

             /* } else{
                  toastFail.show();
              } */
          }
      }




    });

    }

   /* public void addQuestion(View view){

        Context context = getApplicationContext();
        CharSequence textSuccess = "Question enregistrée";
        int durationSuccess = Toast.LENGTH_SHORT;


        Toast toastSuccess = Toast.makeText(context, textSuccess, durationSuccess);

        CharSequence textFail = "Veuillez remplir tous les champs et cocher la bonne réponse";
        int durationFail = Toast.LENGTH_SHORT;


        Toast toastFail = Toast.makeText(context, textFail, durationFail);

        EditText editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        EditText editTextFirstChoice = (EditText) findViewById(R.id.editTextFirstChoice);
        EditText editTextSecondChoice = (EditText) findViewById(R.id.editTextSecondChoice);
        RadioButton radioButtonFirstChoice = (RadioButton) findViewById(R.id.radioButtonFirstChoice);
        RadioButton radioButtonSecondChoice = (RadioButton) findViewById(R.id.radioButtonSecondChoice);

        if(radioButtonFirstChoice.isChecked()){
            mCorrectAnswer = mChoiceA;
            //radioButtonSecondChoice.setChecked(false);

        }

        else if(radioButtonSecondChoice.isChecked()){
            mCorrectAnswer = mChoiceB;
            //radioButtonFirstChoice.setChecked(false);
        }

        if(editTextQuestion.length() > 0 && editTextFirstChoice.length() > 0 && editTextSecondChoice.length() > 0 ) {

            mQuestion = editTextQuestion.getText().toString();
            mChoiceA = editTextFirstChoice.getText().toString();
            mChoiceB = editTextSecondChoice.getText().toString();

            QuestionClass newQuestion = new QuestionClass(mQuestion, mChoiceA, mChoiceB, mCorrectAnswer);

            questionList.add(newQuestion);
            toastSuccess.show();
            editTextQuestion.setText("");
            editTextFirstChoice.setText("");
            editTextSecondChoice.setText("");

        } else{
            toastFail.show();
        }


    }*/


}
