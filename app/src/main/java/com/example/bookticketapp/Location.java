package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class Location extends AppCompatActivity {
    SearchView searchView;
    View oldView;
    ListView listView;
    SharedPreferencesHandler sharedPreferencesHandler;

    ImageView back_btn;
    String selected_name="";
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        String cities[]={"Bangalore","Chennai","Hyderabad","Manipal","Mumbai"};
        oldView = null;

        searchView=findViewById(R.id.searchv);
        listView=findViewById(R.id.listv);
        back_btn=findViewById(R.id.image1);
        confirm=findViewById(R.id.confirm);
        sharedPreferencesHandler=new SharedPreferencesHandler(getApplicationContext());
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cities);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
        listView.setAdapter(arrayAdapter);

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

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in =new Intent(Location.this,Landing_page.class);
                startActivity(in);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_name = cities[i];

                if(oldView==null){
                    oldView = view;
                } else if(oldView!=null){
                    oldView.setBackgroundResource(R.color.white);
                    oldView = view;
                }

                view.setBackgroundResource(R.color.app_color);
                sharedPreferencesHandler.saveCityName(cities[i]);

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  Intent in=new Intent(Location.this,Landing_page.class);
                  startActivity(in);
                  finish();


            }
        });
    }
}