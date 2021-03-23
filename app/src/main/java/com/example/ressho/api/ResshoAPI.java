package com.example.ressho.api;

import com.example.ressho.responses.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResshoAPI {

    @GET("/products")
    Call<ProductsResponse> getProducts();
}
