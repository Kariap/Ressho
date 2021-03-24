package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);

                boolean loggedINSeller=prefs.getBoolean("loggedInSeller", false);
                boolean loggedINReseller=prefs.getBoolean("loggedInReseller", false);

                if(loggedINSeller){
                    Intent i = new Intent(SplashScreen.this, SellerActivity.class);
                    startActivity(i);
                    finish();
                }else if(loggedINReseller){
                    Intent i = new Intent(SplashScreen.this, ResellerActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this, UserLogin.class);
                    startActivity(i);
                    finish();
                }
            }

        }, 2*1000);
    }
}