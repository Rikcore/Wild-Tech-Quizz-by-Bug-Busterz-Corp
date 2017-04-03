package bugbusterzcorp.wildtechquizz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CharteActivity extends AppCompatActivity {

    TextView textViewCharte;
    Button buttonRetour;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charte);

        textViewCharte = (TextView) findViewById(R.id.textViewCharte);
        buttonRetour = (Button)findViewById(R.id.buttonRetour);

        progressDialog = new ProgressDialog(this);

        textViewCharte.setMovementMethod(new ScrollingMovementMethod());


        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent GoProfile = new Intent(CharteActivity.this, ProfileActivity.class);
                startActivity(GoProfile);
                finish();
            }
        });
    }
}
