package com.example.ressho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ressho.adapters.ProductsSellerAdapters;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.responses.ProductsResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerActivity extends AppCompatActivity {
    private RecyclerView rvProducts;
    private ProductsSellerAdapters sellerProductAdapter;
    private FloatingActionButton btnAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Hi Pallav!");

        rvProducts=findViewById(R.id.rv_products_by_seller);
        sellerProductAdapter=new ProductsSellerAdapters(this);
        rvProducts.setVisibility(View.VISIBLE);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(sellerProductAdapter);
        rvProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        btnAddProduct=findViewById(R.id.add_product);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivity.this,AddProduct.class));
            }
        });

        ResshoAPI resshoAPI=RetrofitInstance.getRetrofitInstance().create(ResshoAPI.class);
        resshoAPI.getSellerProducts("pallav").enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                rvProducts.setVisibility(View.VISIBLE);
                findViewById(R.id.progress_circular).setVisibility(View.GONE);
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