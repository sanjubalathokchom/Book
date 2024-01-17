package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    ImageView back_arrow;
    TextView log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back_arrow=findViewById(R.id.backArrow);
        log_out=findViewById(R.id.log_out);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Profile.this,Landing_page.class);
                startActivity(in);
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Profile.this,MainActivity.class);
                Toast.makeText(Profile.this,"User logged out", Toast.LENGTH_SHORT).show();
                startActivity(in);
            }
        });
    }
}