package com.example.ressho.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ressho.models.Order;
import com.example.ressho.repository.ResshoRepository;

import java.util.List;

public class StatusOfOrdersViewModel extends ViewModel {
    private ResshoRepository repo=ResshoRepository.getInstance();
    private LiveData<List<Order>> orders;
    public StatusOfOrdersViewModel() {
        super();
    }
    public LiveData<List<Order>> getOrders(String reseller){
        orders=repo.getOrders(reseller);
        return orders;
    }
}
