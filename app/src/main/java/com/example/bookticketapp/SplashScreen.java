package com.example.bookticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);

        //Setting the progress starting value to be 0
        setProgressValue(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);

    }

    private void setProgressValue(final int progress) {

        // set the progress
        if(progress<100){
            simpleProgressBar.setProgress(progress);

        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 95);
            }
        });
        thread.start();
        }
        else
            start();

    }


    public void start(){
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}




/*-----------------Rough--------------------*/
// on below line we are
// starting a new activity.

// on below line we are configuring our window to full screen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        // on below line we are calling handler to run a task
//        // for specific time interval
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // on below line we are
//                // creating a new intent
//                Intent i = new Intent(SplashScreen.this, MainActivity.class);
//
//                // on below line we are
//                // starting a new activity.
//                startActivity(i);
//
//                // on the below line we are finishing
//                // our current activity.
//                finish();
//            }
//        }, 2000);