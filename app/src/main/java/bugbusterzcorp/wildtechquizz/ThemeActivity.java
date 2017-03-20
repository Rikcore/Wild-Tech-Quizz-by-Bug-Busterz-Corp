package bugbusterzcorp.wildtechquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeActivity extends AppCompatActivity {

    ImageView imageViewTech;
    ImageView imageViewWild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        imageViewTech = (ImageView) findViewById(R.id.imageViewTech);
        imageViewWild = (ImageView) findViewById(R.id.imageViewTech);







    }
}


