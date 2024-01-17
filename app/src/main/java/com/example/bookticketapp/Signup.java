package com.example.bookticketapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    TextView Login;
    Button signUp;
    EditText etName,etEmail,etMob,etPass,etconPass;
    String name,number,email,password,confirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Login=findViewById(R.id.login_redirect);
        signUp=findViewById(R.id.signUp_btn);
        etName=findViewById(R.id.name);
        etEmail=findViewById(R.id.email);
        etMob=findViewById(R.id.mobile);
        etPass=findViewById(R.id.password);
        etconPass=findViewById(R.id.confPass);

        //login redirect
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Signup.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        });

        //signUp Button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etName.getText().toString();
                number=etMob.getText().toString();
                email=etEmail.getText().toString();
                password=etPass.getText().toString();
                confirmPass=etconPass.getText().toString();
                if(validate(name,number,email,password,confirmPass)) {
                    MyDbHelper db = new MyDbHelper(Signup.this);
                    if(db.insert(name, number, email, password, confirmPass)) {
                        Intent in = new Intent(Signup.this, MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                }
            }
        });

    }
        boolean validate(String name,String number,String email,String password,String confirmPass){
            if(name.isEmpty()) {
                etName.setError("name cannot be empty");
                return false;
            }else if(number.isEmpty()) {
                etMob.setError("number cannot be empty");
                return false;
            } else if(email.isEmpty()) {
                etEmail.setError("email cannot be empty");
                return false;
            }else if(password.isEmpty()) {
                etPass.setError("password cannot be empty");
                return false;
            }else if(confirmPass.isEmpty() ) {
                etconPass.setError("confirm password cannot be empty");
                return false;
            }else if(!confirmPass.equals(password) ) {
                etconPass.setError("confirm password and password should be the same");
                return false;
            }
            else return true;
        }
    }
