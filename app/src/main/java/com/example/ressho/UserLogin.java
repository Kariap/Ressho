package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ressho.viewmodels.UserLoginViewModel;

import java.io.UnsupportedEncodingException;

import retrofit2.Response;

public class UserLogin extends AppCompatActivity {
    private Button btnSellerLogin;
    private Button btnResellerLogin;
    private EditText userName;
    private EditText password;
    private UserLoginViewModel loginViewModel;
    //TODO:show dialog box while loggin in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnResellerLogin=findViewById(R.id.btn_reseller_login);
        btnSellerLogin=findViewById(R.id.btn_seller_login);
        userName=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        loginViewModel=new UserLoginViewModel();
        btnResellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userName.getText().toString(),password.getText().toString(),"loggedInReseller");
            }
        });

        btnSellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userName.getText().toString(),password.getText().toString(),"loggedInSeller");
            }
        });
    }

    private void login(String userName, String password,String type) {

        String encodedString=getEncodedString(userName,password);
        loginViewModel.login(encodedString).observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                if (response.code() == 200) {
                    setPrefForLoggedInType(type);
                    if (type.equals("loggedInReseller")) {
                        startActivity(new Intent(UserLogin.this, ResellerActivity.class));
                    } else {
                        startActivity(new Intent(UserLogin.this, SellerActivity.class));
                    }
                    finish();
                } else if (response.code() == 401) {
                    Toast.makeText(UserLogin.this, "Incorrect password or username", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserLogin.this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private String getEncodedString(String userName, String password) {
        String unifiedUsernameAndPassword=userName+":"+password;
        byte[] data = new byte[0];
        try {
            data = unifiedUsernameAndPassword.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
        String encodedString="basic " + base64;
        return encodedString;
    }

    private void setPrefForLoggedInType(String type) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserLogin.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean(type,true);
            editor.apply();
    }

}