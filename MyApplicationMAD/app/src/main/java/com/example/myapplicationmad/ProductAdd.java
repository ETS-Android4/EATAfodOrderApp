package com.example.myapplicationmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductAdd extends AppCompatActivity {
    private Button SubmitAddproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        SubmitAddproduct= (Button)findViewById(R.id.submit_addResturant);

        SubmitAddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductAdd.this,ProductView.class);
                startActivity(intent);
                finish();
            }
        });
    }
}