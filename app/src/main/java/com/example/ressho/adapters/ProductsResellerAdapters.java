package com.example.ressho.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ressho.NewOrder;
import com.example.ressho.R;
import com.example.ressho.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsResellerAdapters extends RecyclerView.Adapter<ProductsResellerAdapters.ProductViewHolder> {
    private final Context context;
    private List<Product> products=new ArrayList<>();

    public ProductsResellerAdapters(Context context) {
        this.context=context;
    }

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
        holder.productRatings.setText(product.getRating().toString());
        holder.productName.setText(product.getProductName());
        holder.productMRP.setText("\u20B9 "+product.getProductMRP());
        holder.shareNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = "Hey there!Check out "+product.getProductName()+" by "+product.getSeller()+" @ \u20B9 "+product.getPrice();
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(intent,"Share"));
            }
        });
        holder.orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NewOrder.class));
            }
        });
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
        private Button shareNow;
        private Button orderNow;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            sellerName=itemView.findViewById(R.id.product_seller);
            productPrice=itemView.findViewById(R.id.product_price);
            productName=itemView.findViewById(R.id.product_name);
            productRatings=itemView.findViewById(R.id.product_ratings);
            productMRP=itemView.findViewById(R.id.product_mrp);
            shareNow=itemView.findViewById(R.id.share_product);
            orderNow=itemView.findViewById(R.id.add_to_cart);
        }
    }
}
