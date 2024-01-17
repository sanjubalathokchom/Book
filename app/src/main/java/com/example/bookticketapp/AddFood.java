package com.example.bookticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddFood extends AppCompatActivity {


    private List<ListItem> productList;
    private CustomListAdapter productAdapter;
    private ListView listView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        View backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click (e.g., go back to the previous screen)
                finish();
            }
        });

        listView = findViewById(R.id.listview1);
        button = findViewById(R.id.add);
        // TextView rateview = (TextView) findViewById(R.id.rateview);
        int total = 0;

        productList = new ArrayList<>();
        productList.add(new ListItem(R.drawable.download, "50", "Pepsi"));
        productList.add(new ListItem(R.drawable.cheesepopcorn, "100", "Cheese Popcorn"));
        productList.add(new ListItem(R.drawable.frenchfry, "100", "French Fries"));

        productAdapter = new CustomListAdapter(this, (ArrayList<ListItem>) productList);
        listView.setAdapter(productAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productAdapter.calculateTotal(); // Calculate total price and quantity
                double totalPrice = productAdapter.getTotalPrice();
                int totalQuantity = productAdapter.getTotalQuantity();

                Intent intent = new Intent(AddFood.this, MakePayment.class);
                intent.putExtra("totalPrice", totalPrice);

               // intent.putExtra("totalqty", ListItem.qty);
                //intent.putExtra("totalQuantity", totalQuantity);
                startActivity(intent);

            }
        });
    }
}

