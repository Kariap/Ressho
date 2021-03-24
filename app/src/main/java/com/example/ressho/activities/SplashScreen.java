package com.example.ressho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.ressho.R;

public class SplashScreen extends AppCompatActivity {
    //This is a splashScreen this activity manages user session.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                //checks if a seller/reseller is logged in or not and if true then starts appropriate activity.
                //Only one user can login at a time.
                boolean loggedINSeller=prefs.getBoolean(UserLoginActivity.KEY_IS_SELLER_LOGGED_IN, false);
                boolean loggedINReseller=prefs.getBoolean(UserLoginActivity.KEY_IS_RESELLER_LOGGED_IN, false);

                if(loggedINSeller){
                    Intent i = new Intent(SplashScreen.this, SellerActivity.class);
                    startActivity(i);
                    finish();
                }else if(loggedINReseller){
                    Intent i = new Intent(SplashScreen.this, ResellerActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashScreen.this, UserLoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        }, 2*1000);
    }
}