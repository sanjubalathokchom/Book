package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;

public class Theatre_list extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    Button btn1,btn2;
    ImageView backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre_list);
        btn1=findViewById(R.id.button2);
        btn2=findViewById(R.id.button3);
        backArrow=findViewById(R.id.image1);
        String theatre[]={"INOX","PVR","IMAX","PVR GOLD","CINEPOLIS"};
        searchView=findViewById(R.id.searchv);
        listView=findViewById(R.id.listv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,theatre);
        listView.setAdapter(arrayAdapter);

        //search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Theatre_list.this,Landing_page.class);
                startActivity(in);
            }
        });
    }
}