package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Gadar extends AppCompatActivity {

    int date=0,cinema=0;
    TextView t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadar);

        Button Button1 = findViewById(R.id.button1);
        Button Button2 = findViewById(R.id.button2);
        Button Button3 = findViewById(R.id.button3);
        Button Button4 = findViewById(R.id.button4);
        Button Button5 = findViewById(R.id.button5);
        Button Button6 = findViewById(R.id.button6);
        Button Button7 = findViewById(R.id.button7);
        Button Button8 = findViewById(R.id.button8);
        Button Button9 = findViewById(R.id.button9);
        Button Button10 = findViewById(R.id.button10);
        Button Button11 = findViewById(R.id.button11);

        SharedPreferencesHandler sharedPreferencesHandler=new SharedPreferencesHandler(this);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView6);

        ImageView back= findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText1 = Button1.getText().toString();
                Button1.getBackground().setTint(getResources().getColor(R.color.blue));
                date++;
                sharedPreferencesHandler.saveMovieDate(buttonText1);
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText2 = Button2.getText().toString();
                Button2.getBackground().setTint(getResources().getColor(R.color.blue));
                date++;
                sharedPreferencesHandler.saveMovieDate(buttonText2);
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText3 = Button3.getText().toString();
                Button3.getBackground().setTint(getResources().getColor(R.color.blue));
                date++;
                sharedPreferencesHandler.saveMovieDate(buttonText3);
            }
        });
        Button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText4 = Button4.getText().toString();
                Button4.getBackground().setTint(getResources().getColor(R.color.blue));
                date++;
                sharedPreferencesHandler.saveMovieDate(buttonText4);
            }
        });
        Button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText5 = Button5.getText().toString();
                Button5.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t3.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText5);
            }
        });
        Button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText6 = Button6.getText().toString();
                Button6.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t3.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText6);
            }
        });
        Button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText7 = Button7.getText().toString();
                Button7.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t3.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText7);
            }
        });
        Button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText8 = Button8.getText().toString();
                Button8.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t4.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText8);
            }
        });
        Button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText9 = Button9.getText().toString();
                Button9.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t4.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText9);
            }
        });
        Button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String buttonText10 = Button10.getText().toString();
                Button10.getBackground().setTint(getResources().getColor(R.color.blue));
                cinema++;
                String s=String.valueOf(t4.getText());
                sharedPreferencesHandler.saveTheatre(s);
                sharedPreferencesHandler.saveMovieTime(buttonText10);
            }
        });
        Button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(date==0 || cinema==0) {
                    Toast.makeText(Gadar.this, "Select Date And Time", Toast.LENGTH_SHORT).show();
                }else{
                    Intent in = new Intent(Gadar.this, Seats.class);
                    startActivity(in);
                }
            }
        });
    }
}
