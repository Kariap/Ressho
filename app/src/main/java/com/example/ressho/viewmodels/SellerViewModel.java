package com.example.ressho.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ressho.models.Product;
import com.example.ressho.repository.ResshoRepository;

import java.util.List;

public class SellerViewModel extends ViewModel {
    private LiveData<List<Product>> products;
    private ResshoRepository repo=ResshoRepository.getInstance();
    public SellerViewModel() {
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<Product>> getSellerProducts(String sellerID){
        products=repo.getProductsForSeller(sellerID);
        return products;
    }

}
