package com.example.bookticketapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Profile_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView back_arrow;
    TextView log_out,profile_name,profile_number,delete_acc,terms,rate;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_frag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Profile_frag newInstance(String param1, String param2) {
        Profile_frag fragment = new Profile_frag();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile_frag, container, false);
        back_arrow=v.findViewById(R.id.backArrow);
        log_out=v.findViewById(R.id.log_out);
        profile_name=v.findViewById(R.id.profile_name);
        profile_number=v.findViewById(R.id.profile_number);
        delete_acc=v.findViewById(R.id.del_acc);
        terms=v.findViewById(R.id.termsConditions);
        rate=v.findViewById(R.id.rateApp);
        MyDbHelper db=new MyDbHelper(requireContext());

        String email= Store_usr_details.email;
        String temp_name="",temp_number="";
        ArrayList<Store_usr_details> al=db.select();
        for(int i=0;i<al.size();i++){
            if(al.get(i).email_address.equals(email)){
                temp_name=al.get(i).name;
                temp_number=al.get(i).phone;
            }
        }
        profile_name.setText(temp_name);
        profile_number.setText(temp_number);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(requireContext(),Landing_page.class);
                startActivity(in);
            }
        });

        //Logout Button
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

                // Set the title and message for the dialog
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to log out?");
                builder.setIcon(R.drawable.logout_icon_24);

                // Set the positive button (Yes)
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed the log out action
                        Intent in = new Intent(requireContext(), MainActivity.class);
                        Toast.makeText(getContext(), "User logged out", Toast.LENGTH_SHORT).show();
                        startActivity(in);
                    }
                });

                // Set the negative button (No)
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled the log out action
                        dialog.dismiss(); // Close the dialog
                    }
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        //Delete Account button
        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

                // Set the title and message for the dialog
                builder.setTitle("Delete Account");
                builder.setMessage("Are you sure you want to delete your account?");
                builder.setIcon(R.drawable.delete_icon_24);

                // Set the positive button (Yes)
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed the log out action
                        MyDbHelper db= new MyDbHelper(requireContext());
                        if(db.delete_usr(Store_usr_details.email)){
                            Intent in = new Intent(requireContext(), MainActivity.class);
                            Toast.makeText(requireContext(), "Your Account has been deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(in);
                        }
                    }
                });

                // Set the negative button (No)
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled the log out action
                        dialog.dismiss(); // Close the dialog
                    }
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        //terms and conditions
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(requireContext(), TermsConditions.class);
                startActivity(in);

            }
        });

        //Rate our app
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(requireContext(), RateApp.class);
                startActivity(in);
            }
        });

        return v;
    }
}