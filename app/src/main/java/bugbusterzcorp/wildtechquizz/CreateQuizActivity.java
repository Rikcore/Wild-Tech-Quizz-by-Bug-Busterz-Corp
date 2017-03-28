package bugbusterzcorp.wildtechquizz;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;


public class CreateQuizActivity extends AppCompatActivity {



    String mCorrectAnswer;
    RadioButton radioButtonFirstChoice;
    RadioButton radioButtonSecondChoice;
    FloatingActionButton floatingActionButtonAddQuestion;
    String mQuestion;
    String mChoiceA;
    String mChoiceB;



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


        mQuestion = editTextQuestion.getText().toString();
        mChoiceA = editTextFirstChoice.getText().toString();
        mChoiceB = editTextSecondChoice.getText().toString();


        if(radioButtonFirstChoice.isChecked()){
            mCorrectAnswer = mChoiceA ;
            //radioButtonSecondChoice.setChecked(false);

        }

        else if(radioButtonSecondChoice.isChecked()){
            mCorrectAnswer = mChoiceB ;
            //radioButtonFirstChoice.setChecked(false);
        }




    }

    public void addQuestion(View view){

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

        mQuestion = editTextQuestion.getText().toString();
        mChoiceA = editTextFirstChoice.getText().toString();
        mChoiceB = editTextSecondChoice.getText().toString();

        if(editTextQuestion.length() > 0 && editTextFirstChoice.length() > 0 && editTextSecondChoice.length() > 0 ) {



            toastSuccess.show();
            editTextQuestion.setText("");
            editTextFirstChoice.setText("");
            editTextSecondChoice.setText("");

            QuestionClass newQuestion = new QuestionClass(mQuestion, mChoiceA, mChoiceB, mCorrectAnswer);

        } else{
            toastFail.show();
        }


    }


}
