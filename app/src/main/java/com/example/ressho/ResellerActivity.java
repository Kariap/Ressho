package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.ressho.adapters.ProductsResellerAdapters;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.models.Product;
import com.example.ressho.responses.ProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResellerActivity extends AppCompatActivity {
private RecyclerView rvProducts;
private ProductsResellerAdapters productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseller);
        rvProducts=findViewById(R.id.rv_products_all);
        productsAdapter=new ProductsResellerAdapters();
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        ResshoAPI apiClient=RetrofitInstance.getRetrofitInstance().create(ResshoAPI.class);
        apiClient.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ProductsResponse productsResponse=response.body();
                productsAdapter.setProducts(productsResponse.getProducts());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }
}