package com.example.ressho.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ressho.R;
import com.example.ressho.viewmodels.UserLoginViewModel;

import java.io.UnsupportedEncodingException;

import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {
    public static final String KEY_IS_RESELLER_LOGGED_IN = "loggedInReseller";
    public static final String KEY_IS_SELLER_LOGGED_IN = "loggedInSeller";
    private Button btnSellerLogin;
    private Button btnResellerLogin;
    private EditText userName;
    private EditText password;
    private UserLoginViewModel loginViewModel;
    //This activity handles user login.User can log in as seller or reseller by pressing respective buttons after
    //entering username and password.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnResellerLogin=findViewById(R.id.btn_reseller_login);
        btnSellerLogin=findViewById(R.id.btn_seller_login);
        userName=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        loginViewModel=new ViewModelProvider(this).get(UserLoginViewModel.class);
        btnResellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userName.getText().toString(),password.getText().toString(), KEY_IS_RESELLER_LOGGED_IN);
            }
        });

        btnSellerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userName.getText().toString(),password.getText().toString(), KEY_IS_SELLER_LOGGED_IN);
            }
        });
    }

    private void login(String userName, String password,String type) {

        String encodedString=getEncodedString(userName,password);
        loginViewModel.login(encodedString).observe(this, new Observer<Response>() {
            @Override
            public void onChanged(Response response) {
                //Server sends status code 200 with response body if authentication is successful.
                //If not show error message.
                if (response.code() == 200) {
                    setPrefForLoggedInType(type);
                    if (type.equals("loggedInReseller")) {
                        startActivity(new Intent(UserLoginActivity.this, ResellerActivity.class));
                    } else {
                        startActivity(new Intent(UserLoginActivity.this, SellerActivity.class));
                    }
                    finish();
                } else if (response.code() == 401) {
                    Toast.makeText(UserLoginActivity.this, "Incorrect password or username", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserLoginActivity.this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //encodes userName:password as base64 to pass in as header.
    private String getEncodedString(String userName, String password) {
        String unifiedUsernameAndPassword=userName+":"+password;
        byte[] data = new byte[0];
        try {
            data = unifiedUsernameAndPassword.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
        return "basic " + base64;
    }

    private void setPrefForLoggedInType(String type) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserLoginActivity.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean(type,true);
            editor.apply();
    }

}