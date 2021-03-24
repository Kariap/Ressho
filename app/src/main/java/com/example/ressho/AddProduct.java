package com.example.ressho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {
    private EditText productName;
    private EditText productPrice;
    private EditText productMrp;
    private Button cancel;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productName=findViewById(R.id.et_product_name);
        productMrp=findViewById(R.id.et_product_mrp);
        productPrice=findViewById(R.id.et_product_price);

        add=findViewById(R.id.add_product_by_seller);
        cancel=findViewById(R.id.cancel_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_name=productName.getText().toString();
                String p_price=productPrice.getText().toString();
                String p_mrp=productMrp.getText().toString();
                if((!p_name.equals("") && !p_mrp.equals("") && !p_price.equals(""))) {
                    Boolean isMRPGreaterThanPrice=Float.parseFloat(p_price)<Float.parseFloat(p_mrp);
                    if(isMRPGreaterThanPrice) {
                        Toast.makeText(AddProduct.this, "Product added:" + p_name + " with mrp " + p_mrp + " and selling price " + p_price + ".", Toast.LENGTH_LONG).show();
                        //Will make post call to add the product with seller name and id.
                        finish();
                    }else{
                        Toast.makeText(AddProduct.this,"Please enter a price less than MRP.",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(AddProduct.this,"Please enter all required details.",Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}