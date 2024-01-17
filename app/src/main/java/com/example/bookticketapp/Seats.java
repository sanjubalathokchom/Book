package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class Seats extends AppCompatActivity {

    private int grayColor;
    private int blueColor;
    Button bookButton;
    View back;
    int selectedSeatsCount = 0;
    //GOLD
    ImageView t1,t2,t3,t4,t5,t6,t7,t8;

    //SILVER
    ImageView t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32;

    //CLUB
    ImageView t33,t34,t35,t36,t37,t38,t39,t40,t41,t42,t43,t44,t45,t46,t47,t48,t49,t50,t51,t52,t53,t54,t55,t56;

    //private HashMap<ImageView, String> selectedSeats = new HashMap<>();
    private static final int PRICE_GOLD = 300;
    private static final int PRICE_SILVER = 200;
    private static final int PRICE_CLUB = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);

        grayColor = Color.GRAY;
        blueColor = Color.CYAN;
        bookButton = findViewById(R.id.book);
        back=findViewById(R.id.back);
        SharedPreferencesHandler sharedPreferencesHandler=new SharedPreferencesHandler(this);

        // Add more grid layouts as needed
        GridLayout gridLayout = findViewById(R.id.grid);
        GridLayout gridLayout1 = findViewById(R.id.grid1);
        GridLayout gridLayout3 = findViewById(R.id.grid3);

        // Call this method for each GridView
        initializeGridView(gridLayout);
        initializeGridView(gridLayout1);
        initializeGridView(gridLayout3);

        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //book now button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedSeatsCount==0){
                    Toast.makeText(getApplicationContext(),"No seat selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    //int totalAmount = func(selectedSeats);
                    Intent in=new Intent(Seats.this,AddFood.class);
                    sharedPreferencesHandler.saveSeatQty(selectedSeatsCount);
                    sharedPreferencesHandler.saveSeatPrice(300);
                    startActivity(in);
                }
            }
        });
    }

    private void initializeGridView(GridLayout gridLayout) {
        // Initialize all seat ImageViews in the given GridView to gray
        for (int row = 0; row < gridLayout.getRowCount(); row++) {
            for (int col = 0; col < gridLayout.getColumnCount(); col++) {
                ImageView seatImageView = (ImageView) gridLayout.getChildAt(row * gridLayout.getColumnCount() + col);
                // seatImageView.setColorFilter(grayColor);
                seatImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleSeatSelection((ImageView) v);
                    }
                });
            }
        }
    }

    private void handleSeatSelection(ImageView imageView) {
        if (imageView.getColorFilter() == null) {
            // Select the seat (change color filter to blue)
            imageView.setColorFilter(blueColor);
            selectedSeatsCount++;

            String seatType = determineSeatType(imageView);
           // selectedSeats.put(imageView, seatType);
        } else {
            // Deselect the seat (remove the color filter to make it gray)
            imageView.setColorFilter(null);
            selectedSeatsCount--;

            //selectedSeats.remove(imageView);
        }
    }

//    private void func(int x){
//        if()
//    }

    private String determineSeatType(ImageView imageView) {
        // You can determine the type based on the imageView's ID, location, or any other criteria.
        // For simplicity, let's assume you have a naming convention for seat IDs (e.g., gold1, silver1, club1).
        String seatId = getResources().getResourceEntryName(imageView.getId());
        if (seatId.startsWith("gold")) {
            return "Gold";
        } else if (seatId.startsWith("silver")) {
            return "Silver";
        } else if (seatId.startsWith("club")) {
            return "Club";
        }
        // Default to an unknown type if not recognized
        return "Unknown";
    }

    private int calculateTotalAmount(HashMap<ImageView, String> selectedSeats) {
        int totalAmount = 0;
        for (ImageView seat : selectedSeats.keySet()) {
            String seatType = selectedSeats.get(seat);
            if ("Gold".equals(seatType)) {
                totalAmount += PRICE_GOLD;
            } else if ("Silver".equals(seatType)) {
                totalAmount += PRICE_SILVER;
            } else if ("Club".equals(seatType)) {
                totalAmount += PRICE_CLUB;
            }
        }
        return totalAmount;
    }
}
