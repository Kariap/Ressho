package com.example.ressho.models;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("product_name")
    private String productName;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("photo_id")
    private String photoID;
    @SerializedName("order_status")
    private String status;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
