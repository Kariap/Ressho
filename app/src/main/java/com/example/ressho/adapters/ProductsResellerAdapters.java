package com.example.ressho.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ressho.R;
import com.example.ressho.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsResellerAdapters extends RecyclerView.Adapter<ProductsResellerAdapters.ProductViewHolder> {
    private List<Product> products=new ArrayList<>();
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View productView=layoutInflater.inflate(R.layout.item_product,parent,false);

        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=products.get(position);
        holder.productPrice.setText("\u20B9 "+product.getPrice());
        holder.sellerName.setText(product.getSeller());
    }
    public void setProducts(List<Product> products) {
        this.products=products;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView sellerName;
        private TextView productPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerName=itemView.findViewById(R.id.product_seller);
            productPrice=itemView.findViewById(R.id.product_price);
        }
    }
}
