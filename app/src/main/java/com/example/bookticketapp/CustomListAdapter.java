package com.example.bookticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<ListItem> listdata;

    private LayoutInflater layoutInflater;
    private double totalPrice = 0.0;
    private int totalQuantity = 0;

    SharedPreferencesHandler sharedPreferencesHandler;
    public CustomListAdapter(Context aContext, ArrayList<ListItem> listdata) {
        this.listdata=listdata;
        layoutInflater=LayoutInflater.from(aContext);
        sharedPreferencesHandler=new SharedPreferencesHandler(aContext);
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static  class ViewHolder{
        TextView uName,uPrice;
        Button incre;
        TextView Quantity;
        Button decre;
        ImageView image;

    }


    @Override
    public View getView(int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if (v==null){
            v=layoutInflater.inflate(R.layout.list_row,null);
            holder=new ViewHolder();
            final int[][] count = {{0}};
            holder.uName=(TextView) v.findViewById(R.id.name);
            holder.incre=(Button) v.findViewById(R.id.incre);
            holder.decre=(Button) v.findViewById(R.id.decre);
            holder.Quantity=(TextView) v.findViewById(R.id.Quantity);
            holder.uPrice=(TextView) v.findViewById(R.id.price);
            holder.image=(ImageView) v.findViewById(R.id.image);
            holder.incre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListItem item = listdata.get(position);
                    int quantity = item.getQuantity();
                    quantity++;
                    item.setQuantity(quantity);
                    holder.Quantity.setText(String.valueOf(quantity));
                    calculateTotal();
                }
            });
            holder.decre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListItem item = listdata.get(position);
                    int quantity = item.getQuantity();
                    if (quantity > 0) {
                        quantity--;
                        item.setQuantity(quantity);
                        holder.Quantity.setText(String.valueOf(quantity));
                        calculateTotal();
                    }
                }
            });
            v.setTag(holder);
        }else{
            holder=(ViewHolder) v.getTag();
        }
        holder.uName.setText(listdata.get(position).getName());
        holder.uPrice.setText(listdata.get(position).getPrice());
        holder.image.setImageResource(listdata.get(position).getImage());
        holder.Quantity.setText(String.valueOf(listdata.get(position).getQuantity()));
        return v;
    }
    public void calculateTotal() {
        totalPrice = 0.0;
        totalQuantity = 0;
        for (ListItem item : listdata) {
            int quantity = item.getQuantity();
            double price = Double.parseDouble(item.getPrice());
            totalPrice += quantity * price;
            totalQuantity += quantity;
        }
        sharedPreferencesHandler.saveBeverage(totalQuantity);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}

