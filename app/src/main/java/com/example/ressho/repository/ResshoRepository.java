package com.example.ressho.repository;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ressho.R;
import com.example.ressho.ResellerActivity;
import com.example.ressho.RetrofitService;
import com.example.ressho.SellerActivity;
import com.example.ressho.UserLogin;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.models.Product;
import com.example.ressho.responses.ProductsResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResshoRepository {
    private final ResshoAPI resshoAPI;
    private final ResshoAPI resshoAPILogin;
    private static final ResshoRepository repo = new ResshoRepository();
    private MutableLiveData<Response> loginResponse=new MutableLiveData<>();
    private MutableLiveData<List<Product>> products=new MutableLiveData<>();

    public ResshoRepository() {
        resshoAPI=RetrofitService.getRetrofitInstance().create(ResshoAPI.class);
        resshoAPILogin=RetrofitService.getRetrofitInstanceForLogin().create(ResshoAPI.class);
    }
    public static ResshoRepository getInstance(){
        return repo;
    }
    public LiveData<List<Product>> getProductsForSeller(String sellerID){
        resshoAPI.getSellerProducts(sellerID).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
               products.setValue(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                products.setValue(null);
            }
        });
        return products;
    }
    public LiveData<Response> login(String encodedString){
        RetrofitService.getRetrofitInstanceForLogin().create(ResshoAPI.class)
                .login(encodedString).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("response Code",""+response.code());
                loginResponse.setValue(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure",t.toString());
                loginResponse.setValue(null);
            }
        });
        return loginResponse;
    }

    public LiveData<List<Product>> getProductsReseller() {
        resshoAPI.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                products.setValue(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                products.setValue(null);
            }
        });
        return products;
    }
}
