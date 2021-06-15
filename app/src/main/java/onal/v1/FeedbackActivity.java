package onal.v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    private TextView getRating;
    private Button Submit;
    private RatingBar RatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        getRating = findViewById(R.id.rate);
        Submit = findViewById(R.id.submit);
        RatingBar = (RatingBar)findViewById(R.id.ratingBar2);

        RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(android.widget.RatingBar ratingBar, float nilai, boolean b) {

                getRating.setText("Rating: "+nilai);
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "The rating you submitted: "+RatingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}