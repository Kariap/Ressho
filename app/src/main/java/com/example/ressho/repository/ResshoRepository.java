package com.example.ressho.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ressho.networking.RetrofitService;
import com.example.ressho.networking.ResshoAPI;
import com.example.ressho.models.Order;
import com.example.ressho.models.Product;
import com.example.ressho.responses.OrdersResponse;
import com.example.ressho.responses.ProductsResponse;

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
    private MutableLiveData<List<Order>> orders=new MutableLiveData<>();

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
        resshoAPILogin.login(encodedString).enqueue(new Callback<ResponseBody>() {
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
    public LiveData<List<Order>> getOrders(String reseller){
        resshoAPI.getResellerOrders(reseller).enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                orders.setValue(response.body().getOrders());
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                orders.setValue(null);
            }
        });
        return orders;
    }
}
