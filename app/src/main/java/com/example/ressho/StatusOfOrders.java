package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StatusOfOrders extends AppCompatActivity {
    private RecyclerView rvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_orders);
        rvOrders=findViewById(R.id.rv_orders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
    }
}