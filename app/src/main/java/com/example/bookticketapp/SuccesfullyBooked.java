package com.example.bookticketapp;


        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

public class SuccesfullyBooked extends AppCompatActivity {
    private TextView movieName, date, time, theater;
    Button confirm;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_booked);
        SharedPreferencesHandler sharedPreferencesHandler=new SharedPreferencesHandler(this);
        // Initialize EditText fields
        movieName = findViewById(R.id.Movie_name);
        date = findViewById(R.id.Conf_date);
        time = findViewById(R.id.conf_time);
        theater = findViewById(R.id.TheaterName);
        confirm = findViewById(R.id.confirm);

        movieName.setText(sharedPreferencesHandler.getMovieName());
        theater.setText(sharedPreferencesHandler.getTheatre());
        // Back button
        View backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click (e.g., go back to the previous screen)
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SuccesfullyBooked.this,Landing_page.class);
                startActivity(in);
                finish();
            }
        });
        // Create a SimpleDateFormat to format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        // Get the current date and time
        Date currentDateAndTime = new Date();

        // Format the date and time and set them in the TextViews
        String formattedDate = dateFormat.format(currentDateAndTime);
        String formattedTime = timeFormat.format(currentDateAndTime);

//        date.setText("Date: " + formattedDate);
//        time.setText("Time: " + formattedTime);
        date.setText(sharedPreferencesHandler.getMovieDate());
        time.setText(sharedPreferencesHandler.getMovieTime());
    }
}