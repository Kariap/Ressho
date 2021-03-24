package com.example.ressho.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ressho.R;
import com.example.ressho.adapters.OrdersAdapter;
import com.example.ressho.models.Order;
import com.example.ressho.viewmodels.StatusOfOrdersViewModel;

import java.util.List;

public class StatusOfOrdersActivity extends AppCompatActivity {
    private RecyclerView rvOrders;
    private OrdersAdapter ordersAdapter;
    private StatusOfOrdersViewModel statusOfOrdersViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvOrders=findViewById(R.id.rv_orders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        rvOrders.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ordersAdapter=new OrdersAdapter(this);
        rvOrders.setAdapter(ordersAdapter);
        statusOfOrdersViewModel=new ViewModelProvider(this).get(StatusOfOrdersViewModel.class);
        statusOfOrdersViewModel.getOrders("pallav").observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                ordersAdapter.setOrders(orders);
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