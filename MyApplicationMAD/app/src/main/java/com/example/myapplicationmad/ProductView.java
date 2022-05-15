package com.example.myapplicationmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductView extends AppCompatActivity {
    private Button Addproduct,EditProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Addproduct= (Button)findViewById(R.id.addproduct);
        EditProductDetails=(Button)findViewById(R.id.editProductDetails);

        Addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductView.this,ProductAdd.class);
                startActivity(intent);
                finish();
            }
        });

        EditProductDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductView.this,EditProductDetails.class);
                startActivity(intent);
                finish();
            }
        });
    }
}