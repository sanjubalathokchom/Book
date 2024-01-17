package com.example.bookticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Landing_page extends AppCompatActivity {

        BottomNavigationView nav_view;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_landing_page);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            nav_view=findViewById(R.id.bottom_nav);

            //Bottom navigation bar(Home, Profile, MyBookings)
            nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id=item.getItemId();
                    if(id==R.id.nav_home){
                        loadFrag(new home_frag());
                    }
                    else if (id==R.id.profile) {
                        loadFrag(new Profile_frag());

                    }
                    else if (id==R.id.my_bookings) {

                    }
                    return true;
                }
            });
            nav_view.setSelectedItemId(R.id.nav_home);



        }
        public void loadFrag(Fragment fragment){
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.container,fragment);
            ft.commit();
        }
    }




/*-----------------Rough--------------------*/

//        RecyclerView recyclerView;
//        Toolbar toolbar;
//        ImageButton image1,image2,img1,img2,img3;
//        FloatingActionButton filter_btn;
//        LinearLayout loc_btn;

//            toolbar = findViewById(R.id.toolbar);
//            image1 = findViewById(R.id.location_btn);
//            image2 = findViewById(R.id.image2);
//            setSupportActionBar(toolbar);
//            image1.setImageResource(R.drawable.round_location_on_24);
//            image2.setImageResource(R.drawable.search_logo);
//            filter_btn=findViewById(R.id.float_filter);
//            loc_btn=findViewById(R.id.location);
//            loc_text=findViewById(R.id.edit1);

//            recyclerView=findViewById(R.id.recycler);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            ArrayList<Model> al=new ArrayList<>();
//            al.add(new Model(R.drawable.jailer,"Jailer","Tamil/Hindi","2D"));
//            al.add(new Model(R.drawable.bholaashankar,"Bholaa Shankar","Telugu","2D"));
//            al.add(new Model(R.drawable.jawan,"Jawan","Kannada","2D"));
//            al.add(new Model(R.drawable.gadar,"Gadar","Hindi","3D"));
//            al.add(new Model(R.drawable.omg,"OMG","Kannada","3D"));
//
//            RecycleAdapter recycleAdapter=new RecycleAdapter(this,al);
//            recyclerView.setAdapter(recycleAdapter);
//
//            //Location
//            image1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in =new Intent(Landing_page.this,Location.class);
//                    startActivity(in);
//                }
//            });



            //movies button
//            img1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in =new Intent(Landing_page.this,Theatre_list.class);
//                    startActivity(in);
//                }
//            });
//
            //Profile Button
//            img2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in =new Intent(Landing_page.this,Profile.class);
//                    startActivity(in);
//                }
//            });
//
//           //my Bookings Button
//            img3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in =new Intent(Landing_page.this,Theatre_list.class);
//                    startActivity(in);
//                }
//            });

            //filter
//            filter_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in =new Intent(Landing_page.this,Filter.class);
//                    startActivity(in);
//                }
//            });
//
//            Intent in1=getIntent();
//            String selected = in1.getStringExtra("name");
//            loc_text.setText(selected);