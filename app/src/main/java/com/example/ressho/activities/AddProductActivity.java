package com.example.ressho.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ressho.R;
import com.example.ressho.viewmodels.AddProductViewModel;

public class AddProductActivity extends AppCompatActivity {
    private EditText productName;
    private EditText productPrice;
    private EditText productMrp;
    private Button cancel;
    private Button add;
    private AddProductViewModel addProductViewModel;
    //This activity handles addition of new Product by the seller.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productName=findViewById(R.id.et_product_name);
        productMrp=findViewById(R.id.et_product_mrp);
        productPrice=findViewById(R.id.et_product_price);

        add=findViewById(R.id.add_product_by_seller);
        cancel=findViewById(R.id.cancel_button);
        addProductViewModel=new ViewModelProvider(this).get(AddProductViewModel.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_name=productName.getText().toString();
                String p_price=productPrice.getText().toString();
                String p_mrp=productMrp.getText().toString();
                //This validates the details and then sends a post request to api for registering/adding the product.
                addProductViewModel.validateAndPostProduct(p_name,p_price,p_mrp);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addProductViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                if(message!=null){
                    Toast.makeText(AddProductActivity.this,message,Toast.LENGTH_LONG).show();
                    //This is done to prevent message from popping again when orientation change happens.
                    //setMessageAsNull sets message to null once after the message is shown,so the above condition is not met.
                    addProductViewModel.setMessageAsNull();
                }
            }
        });
    }
}