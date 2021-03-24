package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewOrder extends AppCompatActivity {
    private EditText etPrice;
    private EditText etCustomerName;
    private EditText etCustomerAddress;
    private RadioGroup rgPaymentMethod;
    private Button placeOrder;
    private Button cancel;
    private Boolean cashOnDelivery=false;
    //TODO:add validation for details
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etPrice=findViewById(R.id.et_customer_price);
        etCustomerAddress=findViewById(R.id.et_customer_address);
        etCustomerName=findViewById(R.id.et_customer_name);
        rgPaymentMethod=findViewById(R.id.payment_options);
        placeOrder=findViewById(R.id.place_order);
        cancel=findViewById(R.id.cancel_order);
        rgPaymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.online_payment){
                    cashOnDelivery=false;
                }else{
                    cashOnDelivery=true;
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price=etPrice.getText().toString();
                String customerName=etCustomerName.getText().toString();
                String customerAddress=etCustomerAddress.getText().toString();
                if(isDetailsValid(customerName,customerAddress,price,cashOnDelivery)){
                    Toast.makeText(NewOrder.this,"Order placed",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    private Boolean isDetailsValid(String customerName, String customerAddress, String price, Boolean cashOnDelivery) {
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}