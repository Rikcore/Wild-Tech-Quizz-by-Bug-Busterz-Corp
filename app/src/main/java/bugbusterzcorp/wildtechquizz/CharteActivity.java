package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CharteActivity extends AppCompatActivity {

    TextView textViewCharte;
    Button buttonRetour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charte);

        textViewCharte = (TextView) findViewById(R.id.textViewCharte);
        buttonRetour = (Button)findViewById(R.id.buttonRetour);


        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // intent de cette activity vers ma list
                Intent retour = new Intent(CharteActivity.this, MainActivity.class);

                startActivity(retour);
                finish();
            }
        });
    }
}
