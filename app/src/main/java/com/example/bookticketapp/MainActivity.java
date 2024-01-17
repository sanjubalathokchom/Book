package com.example.bookticketapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    TextView signUp,forgot_pass;
    Button btn;
    EditText email, password;
    String emailText,passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp = findViewById(R.id.signup_redirect);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.login_btn);
        forgot_pass=findViewById(R.id.textView3);

        //Redirecting to SignUp page
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Signup.class);
                startActivity(in);
//                finish();
            }
        });

        //Login button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 emailText = email.getText().toString();
                 passText = password.getText().toString();

                //casual login
//                Intent in = new Intent(MainActivity.this, Landing_page.class);
//                startActivity(in);

                if (validate(emailText, passText)) {
                    //user authentication for email and password matching
                    if (authenticate()) {
                        Store_usr_details.email = emailText;
                        Intent in = new Intent(MainActivity.this, Landing_page.class);
                        startActivity(in);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //forgot Password
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(), ForgotPass.class);
                startActivity(in);
            }
        });
    }

    //Authenticate User
    public boolean authenticate(){
        MyDbHelper db= new MyDbHelper(MainActivity.this);
        ArrayList<Store_usr_details> al=db.select();
        for(int i=0;i<al.size();i++){
            if(al.get(i).email_address.equals(emailText) && al.get(i).password.equals(passText)){
                return true;
            }
        }
        return false;
    }

    Boolean validate(String emailText, String passText) {
        if (emailText.isEmpty()) {
            email.setError("email cannot be empty");
            return false;
        }
        else if (passText.isEmpty()){
        password.setError("Password cannot be empty");
            return false;
        }
        else if (!emailText.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
            email.setError("Invalid email type");
            return false;
        }
        else if (!passText.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-+]).{8,}$")) {
            password.setError("Invalid password type");
            return false;
        }
        else{
            return true;
        }

    }

}

