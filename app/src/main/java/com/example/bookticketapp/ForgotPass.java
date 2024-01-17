package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


public class ForgotPass extends AppCompatActivity {

    private EditText email, mobile, pass, conf_pass;
    private ToggleButton resetPasswordButton;
    View back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        pass = findViewById(R.id.pass);
        conf_pass = findViewById(R.id.confPass);
        resetPasswordButton = findViewById(R.id.toggleButton);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(in);
                finish();
            }
        });

        resetPasswordButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String res_email = email.getText().toString().trim();
                String res_mobile = mobile.getText().toString().trim();
                String res_pass = pass.getText().toString(); // No need to trim here
                String res_conf_pass = conf_pass.getText().toString(); // No need to trim here

                if (isChecked) {
                    pass.setVisibility(View.VISIBLE);
                    conf_pass.setVisibility(View.VISIBLE);

                    // Toggle button is checked (Update Password state)
                    if (res_email.isEmpty() || res_mobile.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Email and Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
                        resetPasswordButton.setChecked(false);
                        pass.setVisibility(View.GONE); // Hide the password and confirmation password fields
                        conf_pass.setVisibility(View.GONE);

                    } else if (res_pass.isEmpty() || res_conf_pass.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "enter password and confirmPassword ", Toast.LENGTH_SHORT).show();

                    } else if (!res_pass.equals(res_conf_pass)) {
                        Toast.makeText(getApplicationContext(), "Please enter matching new password and confirmation", Toast.LENGTH_SHORT).show();
                        resetPasswordButton.setChecked(false);
                    }

                } else {
                    // Toggle button is not checked (Submit state)
                    if (res_email.isEmpty() || res_mobile.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Email and Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                    MyDbHelper db = new MyDbHelper(getApplicationContext());
                    if (db.update_Usr(res_email, res_mobile, res_pass, res_conf_pass)) {
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(getApplicationContext(), "Password Successfully Updated", Toast.LENGTH_SHORT).show();
                        startActivity(in);
                    } else {
                        Toast.makeText(getApplicationContext(), "Re-enter the details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}