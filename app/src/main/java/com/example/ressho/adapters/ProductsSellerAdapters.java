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

public class ProductsSellerAdapters extends RecyclerView.Adapter<ProductsSellerAdapters.ProductViewHolder> {
    private List<Product> products=new ArrayList<>();
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View productView=layoutInflater.inflate(R.layout.item_product_seller,parent,false);

        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=products.get(position);
        holder.productPrice.setText("\u20B9 "+product.getPrice());
        holder.sellerName.setText(product.getSeller());
        holder.productRatings.setText(product.getRating().toString());
        holder.productName.setText(product.getProductName());
        holder.productMRP.setText("\u20B9 "+product.getProductMRP());
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
        private TextView productName;
        private TextView productRatings;
        private TextView productMRP;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerName=itemView.findViewById(R.id.product_seller);
            productPrice=itemView.findViewById(R.id.product_price);
            productName=itemView.findViewById(R.id.product_name);
            productRatings=itemView.findViewById(R.id.product_ratings);
            productMRP=itemView.findViewById(R.id.product_mrp);
        }
    }
}
