package com.example.ressho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.ressho.adapters.ProductsResellerAdapters;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.responses.ProductsResponse;

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
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Hi Pallav!");
        actionBar.setDisplayHomeAsUpEnabled(true);
        rvProducts=findViewById(R.id.rv_products_all);
        productsAdapter=new ProductsResellerAdapters(this);
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ResshoAPI apiClient= RetrofitService.getRetrofitInstance().create(ResshoAPI.class);
        apiClient.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                findViewById(R.id.progress).setVisibility(View.GONE);
                rvProducts.setVisibility(View.VISIBLE);
                ProductsResponse productsResponse=response.body();
                productsAdapter.setProducts(productsResponse.getProducts());
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reseller_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }else if(item.getItemId()==R.id.orders){
            startActivity(new Intent(ResellerActivity.this,StatusOfOrders.class));
        }else if(item.getItemId()==R.id.logout){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ResellerActivity.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean("loggedInReseller",false);
            editor.apply();
            startActivity(new Intent(ResellerActivity.this,UserLogin.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}