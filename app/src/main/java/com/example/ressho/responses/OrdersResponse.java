package com.example.ressho.responses;

import com.example.ressho.models.Order;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersResponse {
    @SerializedName("orders")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
