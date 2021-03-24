package com.example.ressho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ressho.adapters.ProductsSellerAdapters;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.responses.ProductsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerActivity extends AppCompatActivity {
    private RecyclerView rvProducts;
    private ProductsSellerAdapters sellerProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Hi Pallav!");
        setContentView(R.layout.activity_seller);
        rvProducts=findViewById(R.id.rv_products_by_seller);
        sellerProductAdapter=new ProductsSellerAdapters();
        rvProducts.setVisibility(View.VISIBLE);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(sellerProductAdapter);
        rvProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ResshoAPI resshoAPI=RetrofitInstance.getRetrofitInstance().create(ResshoAPI.class);
        resshoAPI.getSellerProducts("pallav").enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ProductsResponse productsResponse=response.body();
                sellerProductAdapter.setProducts(productsResponse.getProducts());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });

    }
    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}