package com.example.ressho.responses;

import com.example.ressho.models.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("products")
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
