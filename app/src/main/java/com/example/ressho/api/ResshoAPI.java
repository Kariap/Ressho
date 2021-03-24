package com.example.ressho.api;

import com.example.ressho.responses.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResshoAPI {

    @GET("/products")
    Call<ProductsResponse> getProducts();

    @GET("/seller/products")
    Call<ProductsResponse> getSellerProducts(@Query("seller") String seller);
}
