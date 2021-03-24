package com.example.ressho.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddProductViewModel extends ViewModel {
    private MutableLiveData<String> message=new MutableLiveData<>();
    public AddProductViewModel() {
        super();
        message.setValue(null);
    }
    public LiveData<String> getMessage(){
        return message;
    }
    public void validateAndPostProduct(String p_name,String p_price,String p_mrp){
        if((!p_name.equals("") && !p_mrp.equals("") && !p_price.equals(""))) {
            boolean isMRPGreaterThanPrice=Float.parseFloat(p_price)<Float.parseFloat(p_mrp);
            if(isMRPGreaterThanPrice) {
                message.setValue("Product added:" + p_name + " with mrp " + p_mrp + " and selling price " + p_price + ".");
                //Will make post call to add the product with seller name and id.
            }else{
                message.setValue("Please enter a price less than MRP.");

            }
        }else{
            message.setValue("Please enter all required details.");
        }
    }

    public void setMessageAsNull() {
        message.setValue(null);
    }
}
