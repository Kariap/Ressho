package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserLogin extends AppCompatActivity {
    private Button btnSellerLogin;
    private Button btnResellerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnResellerLogin=findViewById(R.id.btn_reseller_login);
        btnSellerLogin=findViewById(R.id.btn_seller_login);

        //Starts reseller activity
        btnSellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this,UserLogin.class));
            }
        });

        //Starts seller activity
        btnSellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this,SellerActivity.class));
            }
        });
    }
}