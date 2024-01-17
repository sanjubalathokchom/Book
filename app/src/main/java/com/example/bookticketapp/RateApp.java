package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateApp extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitButton;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        ratingBar = findViewById(R.id.ratingBar);
        commentEditText = findViewById(R.id.commentEditText);
        submitButton = findViewById(R.id.submitButton);
        back=findViewById(R.id.back);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void submitRating() {
        // Get the rating and comment from UI
        float rating = ratingBar.getRating();
        String comment = commentEditText.getText().toString();

        // You can perform actions with the rating and comment here
        // For now, let's just show a Toast message
        String message = "Rating: " + rating + "\nComment: " + comment;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}