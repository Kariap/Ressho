package com.example.ressho.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ressho.R;
import com.example.ressho.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    private List<Order> orders=new ArrayList<>();
    private final Context context;
    public OrdersAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View orderView=layoutInflater.inflate(R.layout.item_order,parent,false);

        return new OrderViewHolder(orderView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order=orders.get(position);
        holder.orderName.setText(order.getProductName());
        holder.customerName.setText(order.getCustomerName());
        String orderStatus=order.getStatus();
        switch(orderStatus){
            case "delivered":{
                holder.orderStatus.setText("Delivered");
                holder.orderStatus.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                return;
            }
            case "dispatched":{
                holder.orderStatus.setText("Dispatched");
                holder.orderStatus.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                return;
            }
            case "order placed":{
                holder.orderStatus.setText("Order received");
                holder.orderStatus.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }

        }

    }
    public void setOrders(List<Order> orders){
        this.orders=orders;
        notifyDataSetChanged();

    }    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView orderName;
        private TextView customerName;
        private TextView orderStatus;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderName=itemView.findViewById(R.id.order_name);
            orderStatus=itemView.findViewById(R.id.order_status);
            customerName=itemView.findViewById(R.id.order_customer_name);
        }
    }
}
