package com.example.ressho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
import android.widget.Toast;

import com.example.ressho.adapters.ProductsSellerAdapters;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.models.Product;
import com.example.ressho.responses.ProductsResponse;
import com.example.ressho.viewmodels.SellerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerActivity extends AppCompatActivity {
    private RecyclerView rvProducts;
    private ProductsSellerAdapters sellerProductAdapter;
    private FloatingActionButton btnAddProduct;
    private SellerViewModel sellerViewModel;
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
        sellerViewModel=new SellerViewModel();
        sellerViewModel.getSellerProducts("pallav").observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if(products!=null) {
                    rvProducts.setVisibility(View.VISIBLE);
                    findViewById(R.id.progress_circular).setVisibility(View.GONE);
                    sellerProductAdapter.setProducts(products);
                }else{
                    Toast.makeText(SellerActivity.this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivity.this,AddProduct.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.seller_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }else if(item.getItemId()==R.id.logout){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SellerActivity.this);
            SharedPreferences.Editor editor=prefs.edit();
            editor.putBoolean("loggedInSeller",false);
            editor.apply();
            startActivity(new Intent(SellerActivity.this,UserLogin.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}