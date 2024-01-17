package com.example.bookticketapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class home_frag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageButton image1, image2;
    FloatingActionButton filter_btn;
    LinearLayout loc_btn;
    TextView loc_text;
    ArrayList<Store_movie_details> al;
    SharedPreferencesHandler sharedPreferencesHandler;

    public home_frag() {

    }

    public static home_frag newInstance(String param1, String param2) {
        home_frag fragment = new home_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        image1 = view.findViewById(R.id.location_btn);
        image2 = view.findViewById(R.id.image2);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        image1.setImageResource(R.drawable.round_location_on_24);
        image2.setImageResource(R.drawable.search_logo);
        filter_btn = view.findViewById(R.id.float_filter);
        loc_btn = view.findViewById(R.id.location);
        loc_text = view.findViewById(R.id.edit1);
        recyclerView = view.findViewById(R.id.recycler);
        sharedPreferencesHandler = new SharedPreferencesHandler(requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        loc_text.setText(sharedPreferencesHandler.getCityName());


        //CREATING MyDbHelper class object
        MyDbHelper db = new MyDbHelper(requireContext());

        //As we want to enter the data only once in the database we use a shared preference KEY-VALUE pair to determine whether the data entered is or not.
        // Checking the value of the shared preference if 'False' INSERTION takes place else if 'TRUE' means data is already entered once.
        if (!sharedPreferencesHandler.getVersion()) {

            //Jailer
            db.insert_movies(R.drawable.jailer, "Jailer", "Chennai", "Tamil", "2D", "Action,Comedy");
            db.insert_movies(R.drawable.jailer, "Jailer", "Bangalore", "Hindi", "2D", "Action,Comedy");
            db.insert_movies(R.drawable.jailer, "Jailer", "Manipal", "Hindi", "2D", "Action,Comedy");
            db.insert_movies(R.drawable.jailer, "Jailer", "Mumbai", "Hindi", "2D", "Action,Comedy");
            db.insert_movies(R.drawable.jailer, "Jailer", "Bangalore", "Kannada", "2D", "Action,Comedy");
            //Bhola Shankar
            db.insert_movies(R.drawable.bholaashankar, "Bholaa Shankar", "Hyderabad", "Telugu", "2D", "Action,Drama");
            db.insert_movies(R.drawable.bholaashankar, "Bholaa Shankar", "Chennai", "Tamil", "2D", "Action,Drama");
            db.insert_movies(R.drawable.bholaashankar, "Bholaa Shankar", "Bangalore", "Kannada", "2D", "Action,Drama");
            //Jawan
            db.insert_movies(R.drawable.jawan, "Jawan", "Bangalore", "Hindi", "2D", "Action,Drama");
            db.insert_movies(R.drawable.jawan, "Jawan", "Chennai", "Hindi", "2D", "Action,Drama");
            db.insert_movies(R.drawable.jawan, "Jawan", "Manipal", "Hindi", "2D", "Action,Drama");
            db.insert_movies(R.drawable.jawan, "Jawan", "Mumbai", "Hindi", "2D", "Action,Drama");
            //Gadar
            db.insert_movies(R.drawable.gadar, "Gadar", "Mumbai", "Hindi", "2D", "Drama,Romance");
            db.insert_movies(R.drawable.gadar, "Gadar", "Manipal", "Hindi", "2D", "Drama,Romance");
            db.insert_movies(R.drawable.gadar, "Gadar", "Bangalore", "Hindi", "2D", "Drama,Romance");
            //Omg2
            db.insert_movies(R.drawable.omg, "OMG", "Bangalore", "Hindi", "2D", "Drama,Comedy");
            db.insert_movies(R.drawable.omg, "OMG", "Manipal", "Hindi", "2D", "Drama,Comedy");
            db.insert_movies(R.drawable.omg, "OMG", "Mumbai", "Hindi", "2D", "Drama,Comedy");
            db.insert_movies(R.drawable.omg, "OMG", "Hyderabad", "Hindi", "2D", "Drama,Comedy");
            sharedPreferencesHandler.saveVersion(true);
        }

        // 'FETCHING' data from the DataBase in form of an ArrayList(al) as to give it to RecyclerView's Constructor(RecycleAdapter.java)
        if (!sharedPreferencesHandler.getMovieValue()) {
            al = db.selectMoviesByCity(sharedPreferencesHandler.getCityName());

        } else {

            if (sharedPreferencesHandler.getLanguage().substring(0, sharedPreferencesHandler.getLanguage().length() - 1).equals("All") && sharedPreferencesHandler.getMovieGenre().substring(0, sharedPreferencesHandler.getMovieGenre().length()-1).equals("All"))  //|| sharedPreferencesHandler.getMovieGenre().substring(0, sharedPreferencesHandler.getMovieGenre().length() - 1)=="All"
            {
                al = db.selectMoviesByCity(sharedPreferencesHandler.getCityName());
                sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
            } else {
                String shared = sharedPreferencesHandler.getMovieGenre();
                String lang = sharedPreferencesHandler.getLanguage();
                String Genrevalue[] = shared.substring(0, shared.length() - 1).split(",");
                String Langvalue[] = lang.substring(0, lang.length() - 1).split(",");

                if (Langvalue[0].equals("All") && Genrevalue[0].equals("All")) {
                    al = db.selectMoviesBy_City_Genre(sharedPreferencesHandler.getCityName(), Genrevalue[0], "HELLO");
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue[0].equals("All") && Genrevalue.length == 1) {
                    al = db.selectMoviesBy_City_Genre(sharedPreferencesHandler.getCityName(), Genrevalue[0], "XYZ");
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue[0].equals("All") && Genrevalue.length == 2) {
                    al = db.selectMoviesBy_City_Genre(sharedPreferencesHandler.getCityName(), Genrevalue[0], Genrevalue[1]);
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue.length == 1 && Genrevalue[0].equals("All")) {
                    al = db.selectMoviesBy_City_Lang(sharedPreferencesHandler.getCityName(), Langvalue[0], "XYZ");
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue.length == 2 && Genrevalue[0].equals("All")) {
                    al = db.selectMoviesBy_City_Lang(sharedPreferencesHandler.getCityName(), Langvalue[0], Langvalue[1]);
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue.length == 2 && Genrevalue.length == 2) {
                    al = db.selectMoviesBy_City_Genre_Lang(sharedPreferencesHandler.getCityName(), Genrevalue[0], Genrevalue[1], Langvalue[0], Langvalue[1]);
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                }else if (Langvalue.length == 1 && Genrevalue.length == 1) {
                    al = db.selectMoviesBy_City_Genre_Lang(sharedPreferencesHandler.getCityName(), Genrevalue[0], "XYZ", Langvalue[0], "XYZ");
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue.length == 1 && Genrevalue.length == 2) {
                    al = db.selectMoviesBy_City_Genre_Lang(sharedPreferencesHandler.getCityName(), Genrevalue[0], Genrevalue[1], Langvalue[0], "XYZ");
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                } else if (Langvalue.length == 2 && Genrevalue.length == 1) {
                    al = db.selectMoviesBy_City_Genre_Lang(sharedPreferencesHandler.getCityName(), Genrevalue[0], "HELLO", Langvalue[0], Langvalue[1]);
                    sharedPreferencesHandler.saveMovieGenre("All,",true,"All,");
                }

            }
        }


        //CREATING RecycleAdapter class object

            RecycleAdapter recycleAdapter = new RecycleAdapter(requireContext(), al);
            recyclerView.setAdapter(recycleAdapter);
            if(al.size()==0) {
                Toast.makeText(getContext(), "No Movies for applied filter ", Toast.LENGTH_SHORT).show();
            }

        //FILTER floating button
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(requireContext(), Filter.class);
                startActivity(in);
                getActivity().finish();
            }
        });

        //Linear layout(id=Location) has assigned with the object loc_btn to perform onclick operation
        loc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(requireContext(), Location.class);
                startActivity(in);
                getActivity().finish();
            }
        });

        return view;
    }
}



/*-----------------Rough--------------------*/

//        ArrayList<Model> al=new ArrayList<>();
//        al.add(new Model(R.drawable.jailer,"Jailer","Tamil","2D"));
//        al.add(new Model(R.drawable.bholaashankar,"Bholaa Shankar","Telugu","2D"));
//        al.add(new Model(R.drawable.jawan,"Jawan","Kannada","2D"));
//        al.add(new Model(R.drawable.gadar,"Gadar","Hindi","3D"));
//        al.add(new Model(R.drawable.omg,"OMG","Kannada","3D"));

//                else if(Langvalue.length==1){
//                    al = db.selectMoviesBy_City_Lang(sharedPreferencesHandler.getCityName(), Langvalue[0], "XYZ" );
//                }
//                else if(Genrevalue.length==1){
//                    al = db.selectMoviesBy_City_Genre(sharedPreferencesHandler.getCityName(), Genrevalue[0], "HELLO" );
//                }