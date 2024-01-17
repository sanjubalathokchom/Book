package com.example.bookticketapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    Context context;
    ArrayList<Store_movie_details> al;

    SharedPreferencesHandler sharedPreferencesHandler;

    RecycleAdapter(Context context, ArrayList<Store_movie_details> al){
        this.context=context;
        this.al=al;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(al.get(position).image);
        holder.txtName.setText(al.get(position).movie_name);
        holder.txtName1.setText(al.get(position).desc1);
        holder.txtName2.setText(al.get(position).desc2);
        sharedPreferencesHandler =new SharedPreferencesHandler(context);

        String s=holder.txtName.getText().toString();

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context1= view.getContext();
                if(s.equals("Jailer")){
                    Intent in=new Intent(context1,Jailer.class);
                    context1.startActivity(in);
                    sharedPreferencesHandler.saveMovieName("Jailer");
                } else if(s.equals("Bholaa Shankar")){
                    Intent in=new Intent(context1,BholaShankar.class);
                    context1.startActivity(in);
                    sharedPreferencesHandler.saveMovieName("Bholaa Shankar");
                } else if(s.equals("Jawan")){
                    Intent in=new Intent(context1,Jawan.class);
                    context1.startActivity(in);
                    sharedPreferencesHandler.saveMovieName("Jawan");
                }
                else if(s.equals("OMG")){
                     Intent in=new Intent(context1,Omg.class);
                     context1.startActivity(in);
                     sharedPreferencesHandler.saveMovieName("OMG");
                } else if(s.equals("Gadar")){
                    Intent in=new Intent(context1,Gadar.class);
                     context1.startActivity(in);
                     sharedPreferencesHandler.saveMovieName("Gadar");
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return al.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtName1,txtName2;
        ImageView img;
        Button btn;
        public ViewHolder(View view){
            super(view);
            txtName=view.findViewById(R.id.txtView);
            txtName1=view.findViewById(R.id.txtView1);
            txtName2=view.findViewById(R.id.txtView2);
            img=view.findViewById(R.id.movie_img);
            btn=view.findViewById(R.id.book);
        }
    }
}
