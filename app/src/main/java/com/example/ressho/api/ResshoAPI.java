package com.example.ressho.api;

import com.example.ressho.responses.OrdersResponse;
import com.example.ressho.responses.ProductsResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResshoAPI {

    @GET("/products")
    Call<ProductsResponse> getProducts();

    @GET("/seller/products")
    Call<ProductsResponse> getSellerProducts(@Query("seller") String seller);

    @POST("/neworder")
    Call<ResponseBody> postNewOrder();

    @GET("/reseller/orders")
    Call<OrdersResponse> getResellerOrders(@Query("reseller") String reseller);
}
