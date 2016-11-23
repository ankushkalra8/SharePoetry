package com.example.k.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread th = new Thread() {
            public void run() {
                try{
                    Thread.sleep(6000);
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } finally {

                }
            }
        };
        th.start();
    }

    public void onPause() {
        super.onPause();
        finish();
    }
}