package com.example.ressho.models;


import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("seller")
    private String seller;
    @SerializedName("price")
    private Float price;
    @SerializedName("rating")
    private Float rating;
    @SerializedName("photo_id")
    private String photoID;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }
}
