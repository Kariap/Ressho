package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.ressho.api.ResshoAPI;
import com.example.ressho.responses.ProductsResponse;

import java.io.UnsupportedEncodingException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogin extends AppCompatActivity {
    private Button btnSellerLogin;
    private Button btnResellerLogin;
    private EditText userName;
    private EditText password;
    //TODO:show dialog box while loggin in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnResellerLogin=findViewById(R.id.btn_reseller_login);
        btnSellerLogin=findViewById(R.id.btn_seller_login);
        userName=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);

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
        String unifiedUsernameAndPassword=userName+":"+password;
        byte[] data = new byte[0];
        try {
            data = unifiedUsernameAndPassword.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
        String encodedString="basic " + base64;
        Log.d("encodedString",encodedString);

        RetrofitService.getRetrofitInstanceForLogin().create(ResshoAPI.class)
                .login(encodedString).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("response Code",""+response.code());
                if(response.code()==200){
                    manageSharedPreferenceForSession(type);
                    if(type.equals("loggedInReseller")) {
                        startActivity(new Intent(UserLogin.this, ResellerActivity.class));
                    }else{
                        startActivity(new Intent(UserLogin.this, SellerActivity.class));
                    }
                    finish();
                }else if(response.code()==401){
                    Toast.makeText(UserLogin.this,"Incorrect password or username",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure",t.toString());
                Toast.makeText(UserLogin.this,"Please check your internet connection",Toast.LENGTH_LONG).show();

            }
        });

    }

    private void manageSharedPreferenceForSession(String type) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserLogin.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean(type,true);
            editor.apply();
    }

}