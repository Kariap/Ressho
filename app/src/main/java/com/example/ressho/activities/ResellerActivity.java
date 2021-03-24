package com.example.ressho.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import com.example.ressho.R;
import com.example.ressho.adapters.ProductsResellerAdapters;
import com.example.ressho.models.Product;
import com.example.ressho.viewmodels.ResellerViewModel;

import java.util.List;

public class ResellerActivity extends AppCompatActivity {
private RecyclerView rvProducts;
private ProductsResellerAdapters productsAdapter;
private ResellerViewModel resellerViewModel;

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
        resellerViewModel=new ViewModelProvider(this).get(ResellerViewModel.class);
        resellerViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                findViewById(R.id.progress).setVisibility(View.GONE);
                rvProducts.setVisibility(View.VISIBLE);
                productsAdapter.setProducts(products);
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
            startActivity(new Intent(ResellerActivity.this, StatusOfOrdersActivity.class));
        }else if(item.getItemId()==R.id.logout){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ResellerActivity.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean("loggedInReseller",false);
            editor.apply();
            startActivity(new Intent(ResellerActivity.this, UserLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}