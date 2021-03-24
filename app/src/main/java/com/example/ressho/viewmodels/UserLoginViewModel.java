package com.example.ressho.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ressho.repository.ResshoRepository;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class UserLoginViewModel extends ViewModel {
    private ResshoRepository repo=ResshoRepository.getInstance();
    private LiveData<Response> response;
    public UserLoginViewModel() {
        super();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public LiveData<Response> login(String encodedString){
        response=repo.login(encodedString);
        return response;
    }

}
