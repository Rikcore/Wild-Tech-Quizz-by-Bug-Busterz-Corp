package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeActivity extends AppCompatActivity {
    TextView textViewTech;
    TextView textViewWild;
    ImageView imageViewTech;
    ImageView imageViewWild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        imageViewTech = (ImageView) findViewById(R.id.imageViewTech);
        imageViewWild = (ImageView) findViewById(R.id.imageViewTech);
        Intent techQuizz = getIntent();
        Intent wildQuizz = getIntent();


        imageViewTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent techQuizz = new Intent(ThemeActivity.this,)


            }
        });

        imageViewWild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wildQUizz = new Intent(ThemeActivity)
            }
        });



    }
}


