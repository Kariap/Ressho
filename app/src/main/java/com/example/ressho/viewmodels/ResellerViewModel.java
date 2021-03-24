package com.example.ressho.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ressho.models.Product;
import com.example.ressho.repository.ResshoRepository;

import java.util.List;

import retrofit2.Response;

public class ResellerViewModel extends ViewModel {
    private ResshoRepository repo=ResshoRepository.getInstance();
    private LiveData<List<Product>> products;
    public ResellerViewModel() {
        super();
        products=repo.getProductsReseller();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<Product>> getProducts(){
        return products;
    }
}
