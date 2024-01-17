package com.example.bookticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Filter extends AppCompatActivity {
    ImageView backbtn;
    Button apply_btn;
    String value="",lang="";
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_by);

        backbtn=findViewById(R.id.arrowBack);
        apply_btn=findViewById(R.id.apply_filter);
        SharedPreferencesHandler sharedPreferencesHandler=new SharedPreferencesHandler(getApplicationContext());

        //Language
        cb1=findViewById(R.id.checkBox1);
        cb2=findViewById(R.id.checkBox2);
        cb3=findViewById(R.id.checkBox3);
        cb4=findViewById(R.id.checkBox4);
        cb5=findViewById(R.id.checkBox5);
        cb6=findViewById(R.id.checkBox6);

        //Genre
        cb7=findViewById(R.id.checkBox7);
        cb8=findViewById(R.id.checkBox8);
        cb9=findViewById(R.id.checkBox9);
        cb10=findViewById(R.id.checkBox10);
        cb11=findViewById(R.id.checkBox11);

        //apply filter button
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                func();
                func2();
               // Toast.makeText(Filter.this, "hello"+lang+value, Toast.LENGTH_SHORT).show();
                sharedPreferencesHandler.saveMovieGenre(value,true,lang);
                Intent homePage = new Intent(Filter.this, Landing_page.class);
                startActivity(homePage);
                finish();
            }
        });

        //back arrow button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Filter.this,Landing_page.class);
                startActivity(in);
                finish();

            }
        });
    }

    //for language
    public void func2(){

        int count=0;
        if(cb1.isChecked()){
            lang=lang.concat(cb1.getText().toString() + ",");
            count++;
        }if(cb2.isChecked()){
            lang=lang.concat(cb2.getText().toString() + ",");
            count++;
        }if(cb3.isChecked()){
            lang=lang.concat(cb3.getText().toString() + ",");
            count++;
        }if(cb4.isChecked()){
            lang=lang.concat(cb4.getText().toString() + ",");
            count++;
        }if(cb5.isChecked()){
            lang=lang.concat(cb5.getText().toString() + ",");
            count++;
        }if(cb6.isChecked()){
            lang=lang.concat(cb6.getText().toString() + ",");
            count++;
        }
        if(count==0){
            lang="All,";
        }

    }

    public void func(){

        int count=0;
        //for Genre
        if(cb7.isChecked()){
            value=value.concat(cb7.getText().toString()+",");
            count++;
        }
        if(cb8.isChecked()){
            value=value.concat(cb8.getText().toString()+",");
            count++;
        }
        if(cb9.isChecked()){
            value=value.concat(cb9.getText().toString()+",");
            count++;
        }
        if(cb10.isChecked()){
            value=value.concat(cb10.getText().toString()+",");
            count++;
        }
        if(cb11.isChecked()){
            value=value.concat(cb11.getText().toString()+",");
            count++;
        }
        if(count==0){
            value="All,";
        }

    }

}
