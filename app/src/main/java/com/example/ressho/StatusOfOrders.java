package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.ressho.adapters.OrdersAdapter;
import com.example.ressho.api.ResshoAPI;
import com.example.ressho.responses.OrdersResponse;
import com.example.ressho.responses.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusOfOrders extends AppCompatActivity {
    private RecyclerView rvOrders;
    private OrdersAdapter ordersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_orders);
        rvOrders=findViewById(R.id.rv_orders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        rvOrders.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ordersAdapter=new OrdersAdapter(this);
        rvOrders.setAdapter(ordersAdapter);
        ResshoAPI resshoAPI=RetrofitService.getRetrofitInstance().create(ResshoAPI.class);
        resshoAPI.getResellerOrders("pallav").enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                OrdersResponse ordersResponse=response.body();
                Log.d("Response",response.body().toString());
                ordersAdapter.setOrders(ordersResponse.getOrders());
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                Log.d("Response",t.toString());
            }
        });
    }
}