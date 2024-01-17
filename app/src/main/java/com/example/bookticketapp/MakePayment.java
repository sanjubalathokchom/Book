package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class MakePayment extends AppCompatActivity {

    TextView tick_no,final_amt,beverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);


        tick_no=findViewById(R.id.Ticket_no);
        final_amt=findViewById(R.id.f_amount);
        beverage=findViewById(R.id.Beverage_no);

        SharedPreferencesHandler sharedPreferencesHandler=new SharedPreferencesHandler(this);
        int no_of_tickets=sharedPreferencesHandler.getSeatQty();
        int tot_seat_price=Integer.valueOf(sharedPreferencesHandler.getSeatPrice());
        Intent in=getIntent();
        double total_amt = in.getDoubleExtra("totalPrice",0);
        //int qty=in.getIntExtra("totalqty",0);
        total_amt = total_amt + tot_seat_price;
        View backButton = (View)findViewById(R.id.back);
        if(true){
            Toast.makeText(getApplicationContext(),"Discount Applied",Toast.LENGTH_LONG).show();
        final_amt.setText(String.valueOf(total_amt-100));
        }
        //setting the textview field of no.of Seats
        tick_no.setText(String.valueOf(no_of_tickets));
        TextView gg = (TextView) findViewById(R.id.amount);
        Button gpay = (Button) findViewById(R.id.google_pay);
        Button paytm = (Button) findViewById(R.id.Paytm);
        Button phpay = (Button) findViewById(R.id.Phone_pay);

//        setting the textview field of total amount
        gg.setText(String.valueOf(total_amt));

        //setting text for beverage no
        beverage.setText(String.valueOf(sharedPreferencesHandler.getBeverageQty()));

        //back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakePayment.this, SuccesfullyBooked.class);
                startActivity(intent);
                finish();
            }
        });

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakePayment.this, SuccesfullyBooked.class);
                startActivity(intent);
                finish();
            }
        });
        phpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakePayment.this, SuccesfullyBooked.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
